package com.ysb.utils;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.lang.NonNull;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


/**
 * @author abin
 */
@Slf4j
public class ImageBase64Utils {

    /**
     * 图片转化成base64字符串
     * @param url 图片物理路径
     * @return "" 出现异常时, 正常返回base64字符串
     */
	public static String getImageStr(String url) {
        return  Base64.getEncoder().encodeToString(image2byte(url));
	}

    /**
     * base64字符串转化成图片
     * @param imgStr 图片base64字符串
     * @param imgPath 需要保存的图片路径
     * @return  true 成功, false 失败
     */
	public static boolean generateImage(@NonNull String imgStr, @NonNull String imgPath) {
        byte[] b = Base64.getDecoder().decode(imgStr);
        for(int i=0;i<b.length;++i) {
            if(b[i]<0) {
                //调整异常数据
                b[i]+=256;
            }
        }
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(imgPath))){
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

	/**
	 *
	 * 图片url地址转base64String编码
	 * @author y
	 * @param imgUrl 图片地址
	 * @return base64 字符串
	 */
	public static String generateBase64ImageUrl(@NonNull String imgUrl) throws IOException{
		byte[] data;
        URL url = new URL(imgUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        InputStream inStream = conn.getInputStream();
        data = readInputStream(inStream);
		return  Base64.getEncoder().encodeToString(data);
	}

    /**
     * 从输入流中读取字节数组
     * @param inStream 输入流
     * @return 字节数组
     * @throws IOException IO异常
     */
	private static byte[] readInputStream(InputStream inStream) throws IOException{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static byte[] image2byte2(@NonNull String path){
        byte[] data;
	    try (FileImageInputStream input = new FileImageInputStream(new File(path)); ByteArrayOutputStream output = new ByteArrayOutputStream()){
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
        }catch (IOException e){
           e.printStackTrace();
            data = new byte[]{};
        }
        return data;
    }


    /**
     * 图片转byte数组
     * @param path 图片物理路径
     * @return byte数组
     */
    public static byte[] image2byte(@NonNull String path){
        byte[] data = null;
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path))){
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
            data = new byte[]{};
        }
        return data;
    }

    /**
     * 16进制图片base64转正常图片base64
     * @param data 16进制图片字符串
     * @return 正常图片base64
     */
    public static String base64(@NonNull String data){
        byte[] bytes = getBytesByHexString(data);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 16进图图片保存到本地
     * @param data 16进制图片字符串
     * @param filePath 保存的本地路径
     * @return true 成功  false 失败
     */
    public static boolean base64(@NonNull String data, @NonNull String filePath){
        byte[] bytes = getBytesByHexString(data);
        try(BufferedOutputStream bufferedOutputStream  = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            bufferedOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 16进制图片数据转byte数组
     * @param data 16进制图片数据
     * @return  byte数组
     */
    private static byte[] getBytesByHexString(@NonNull String data) {
        int length = data.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) (Character.digit(data.charAt(i * 2), 16) << 4 | Character.digit(data.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    public static boolean checkImgUrl(@NotNull String imgUrl) throws IOException {
        URL url = new URL(imgUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        return conn.getResponseCode() == HttpStatus.SC_OK;
    }



}
