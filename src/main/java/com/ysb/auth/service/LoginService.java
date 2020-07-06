//package com.ysb.auth.service;
//
//import com.ferret.auth.LoginController;
//import com.ferret.auth.dao.UserMapper;
//import com.ferret.auth.domain.Role;
//import com.ferret.auth.domain.User;
//import com.ferret.exception.BusinessException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.net.InetAddress;
//import java.util.List;
//
//@Service("loginService")
//public class LoginService {
//
//	@Resource
//	private UserMapper userMapper;
//
//	@Resource
//    private RoleService roleService;
//
//	public void insertLoginMessage(User user, HttpServletRequest request) throws BusinessException{
//		//用户登录成功,存储登录信息表
//        user.setLoginIp(getIpAddr(request));
//        userMapper.userLoginInformationLog(user);
//	}
//
//    public User getUserByUserName(String userName) {
//        //根据用户名查询信息
//        return userMapper.findUserAndRoleByUsername(userName);
//    }
//
//    public  String getIpAddr(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//            if(ip.equals("127.0.0.1")){
//                //根据网卡取本机配置的IP
//                InetAddress inet=null;
//                try {
//                    inet = InetAddress.getLocalHost();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (null != inet) {
//                    ip = inet.getHostAddress();
//                }
//            }
//        }
//        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//        if(ip != null && ip.length() > 15){
//            if(ip.indexOf(",")>0){
//                ip = ip.substring(0,ip.indexOf(","));
//            }
//        }
//        return ip;
//    }
//
//    public void getUserCameraIds () {
//        List<Role> roles = roleService.findAllRoles();
//        if (null == roles || roles.size() < 1) {
//            return;
//        }
//        for (int i = 0; i < roles.size(); i++) {
//            List<Integer> cameraIds = roleService.findCameraIdsByRoleId(roles.get(i).getRoleId());
//            LoginController.userCameraIds.put(roles.get(i).getRoleId(), cameraIds);
//        }
//    }
//}
