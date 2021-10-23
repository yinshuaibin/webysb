package com.ysb.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author yinshuaibin
 * @date 2021/5/10 17:41
 * @description
 */
public class FileWriteTest {

    public static void main(String[] args) {
//        bufferWriterTest();
        System.out.println(0xf == 15);
        System.out.println(15 == 0b1111);
        String[] names = {"ABC", "XYZ", "zoo"};
        String s = names[1];
        names[1] = "cat";
        // s是"XYZ"还是"cat"?   XYZ
        System.out.println(s);

        double dd = 14000;
        double d2 = dd / 12;
        double result = 0;
        for (int x = 0; x < 12; x ++){
           result += (dd * 0.028 * 30 / 100);
           dd = dd - d2;
        }
        System.out.println(result);


    }

    private static void bufferWriterTest(){
        List<byte[]> a = new ArrayList<>();
        byte[] bytes = ImageBase64Utils.image2byte("D:\\facedata\\111.jpg");
        System.out.println(a.size());
        System.out.println(Clock.systemDefaultZone().millis());
        IntStream.range(0, 1000).forEach((x)->{
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\facedata\\test\\" + x + ".jpg"))){
                bufferedOutputStream.write(bytes, 0, bytes.length);
            }catch (IOException ignored){
            }
        });
        System.out.println(Clock.systemDefaultZone().millis());
    }
}
