package com.ysb.utils;


import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipUtils {

    public static void main(String[] args) {
//        String s = "upload/111.jpg";
////        String[] split = s.split("/");
////        String s1 = split[split.length - 1];
////        System.out.println(s1);
////        long start = Clock.systemDefaultZone().millis();
////        fileToZipBufferInput("d://ddd.jpg", "d://ddd.zip");
////        long end = Clock.systemDefaultZone().millis();
////        System.out.println("bufferInputStream用时:" + (end - start));
////        // fileTOZipInput("d://ddd.jpg", "d:/ddd2.zip");
////        long end2 = Clock.systemDefaultZone().millis();
////        System.out.println("intpuStream用时: " + (end2 - end));
////        fileToZipChannel("d://ddd.jpg", "d:/ddd3.zip");
////        long end3 = Clock.systemDefaultZone().millis();
////        System.out.println("fileChannel用时: " + (end3 - end2));

        write("D:\\doc", "D:\\doc\\success", "123.doc");
    }

    private static void fileToZipBufferInput(String filePath, String zipPath){
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream)){
            for (int x = 0; x < 80000; x++){
                try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
                    int byts = 0;
                    zipOutputStream.putNextEntry(new ZipEntry(x + "ddd.jpg"));
                    while ((byts = bufferedInputStream.read()) != -1){
                        bufferedOutputStream.write(byts);
                    }
                }catch (IOException e){
                    System.out.println("读取文件出现错误, 请确认要压缩的问题件路径");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void fileToZipInput(String filePath, String zipPath){
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipPath)))){
            for (int x = 0; x < 80; x++){
                try(FileInputStream f = new FileInputStream(filePath)) {
                    int byts = 0;
                    zipOutputStream.putNextEntry(new ZipEntry(x + "ddd.jpg"));
                    // 一次只读取一个字节, 读取到末尾或出现-1
                    while ((byts = f.read()) != -1){
                        zipOutputStream.write(byts);
                    }
                }catch (IOException ignored){
                }
            }
        }catch (Exception ignored){

        }
    }

    private static void fileToZipChannel(String filePath, String zipPath){
        File file = new File(filePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipPath)));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream)
        ){
            for (int x = 0; x < 80; x++){
                zipOutputStream.putNextEntry(new ZipEntry(x +"_ddd.jpg"));
                try (FileChannel fileChannel = new FileInputStream(filePath).getChannel()){
                    fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                }catch (IOException ignored){

                }
            }
        }catch (Exception ignored){

        }
    }

    private static void write(String srcDir, String descDir, String fileName){
        File srcFile = new File(srcDir + File.separator + fileName);
        File descFile = new File(descDir + File.separator + fileName);
        try (WritableByteChannel writableByteChannel = Channels.newChannel(new FileOutputStream(descFile));
             FileChannel fileChannel = new FileInputStream(srcFile).getChannel()){
            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
        }catch (Exception ignored){

        }
    }

    private static void write2(String srcDir, String descDir, String fileName){
        File srcFile = new File(srcDir + File.separator + fileName);
        File descFile = new File(descDir + File.separator + fileName);
        try (FileChannel out =new FileOutputStream(descFile).getChannel();
             FileChannel in = new FileInputStream(srcFile).getChannel()){
            out.transferTo(0, out.size(), in);
        }catch (Exception ignored){

        }
    }
}
