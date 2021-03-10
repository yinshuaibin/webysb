package com.ysb.test;

import java.io.*;

/**
 * @author yinshuaibin
 * @date 2021/3/10 14:54
 */
public class BufferReaderTest {
    public static void main(String[] args) {
        String s = txt2String(new File("D://111.txt"));
        System.out.println(s);
    }


    private static String txt2String(File file){
        // 线程安全
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line = null;
            // 实际字符串相加使用的是StringBuilder, 但是在for循环中使用+的话, 每次都会创建一个新的StringBuilder对象
            while ((line = bufferedReader.readLine()) != null){
                // System.lineSeparator() 换行
                stringBuffer.append(System.lineSeparator()).append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

}
