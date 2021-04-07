package com.ysb.config.spring.websocket;

import com.ysb.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinshuaibin
 * @date 2021/4/6 15:51
 * @description
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{sid}/{rid}")
public class WebSocketServer {

    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 用户ID
     */
    private String sid="";

    /**
     * 角色Id
     */
    private String rid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("rid") String rid) {
        this.session = session;
        webSocketMap.put(sid, this);
        this.sid=sid;
        this.rid=rid;
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.sid);
    }

    /**
     * 收到客户端消息后调用的方法
     * 此方法还有第二个参数Session session
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("接收到了用户 {} 发送的消息, {}", this.sid, message);
        // 接受到心跳消息后, 将心跳消息返回
        String ping = "ping";
        if (ping.equals(message)){
            sendInfo(message, "ping", sid);
        }else {
            Map map = JacksonUtils.jsonStrToBean(message, Map.class);
            Object sid = map.get("sid");
            if (sid != null){
                sendInfo(map, "message", sid);
            }
        }


    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误: {}", error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        synchronized (WebSocketServer.class){
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 群发自定义消息
     * @param message 消息内容
     * @param type 消息类型
     * @param sid 推送窗口
     */
    public static void sendInfo(Object message, Object type, Object sid){
        Map<String, Object> result = new HashMap<>(2);
        result.put("type", type);
        result.put("webSocketMessage", message);
        for (WebSocketServer item : webSocketMap.values()) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(item.sid.equals(sid) || sid == null){
                    item.sendMessage(JacksonUtils.beanToJsonStr(result));
                }
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        WebSocketServer that = (WebSocketServer) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(sid, that.sid) &&
                Objects.equals(rid, that.rid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, sid, rid);
    }
}
