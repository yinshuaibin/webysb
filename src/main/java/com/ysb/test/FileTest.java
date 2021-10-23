package com.ysb.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author yinshuaibin
 * @date 2021/6/8 11:25
 * @description
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        createTemeFile();
        test();
        pathsTest();
    }

    private static void createTemeFile() throws IOException {
        File tempFile = File.createTempFile("111", ".txt");
        System.out.println(tempFile.getAbsolutePath());
        System.out.println(tempFile.getName());
        System.out.println(tempFile.isFile());
        tempFile.deleteOnExit();
    }

    private static void test(){
        File f = new File("D:\\");
        File[] files = f.listFiles();
        printlnFile(files);

        File[] files1 = f.listFiles(pathname -> pathname.getName().endsWith(".exe"));

        File[] files2 = f.listFiles((dir, name) -> name.endsWith(".zip"));
        printlnFile(files1);

        printlnFile(files2);
    }


    private static void printlnFile(File[] file){
        System.out.println("------");
        if (file != null){
            for (File f : file){
                System.out.println(f.getName());
            }
        }
        System.out.println("------");
    }

    private static void pathsTest(){
        Path path = Paths.get(".", "project", "study");
        System.out.println(path);
        // 转换为绝对路径
        Path path1 = path.toAbsolutePath();
        System.out.println(path1);
        // 转换为规范路径
        Path normalize = path.normalize();
        System.out.println(normalize);
        File file = path.toFile();
        System.out.println(file);
        for (Path p : Paths.get("..").toAbsolutePath()){
            System.out.println("      " + p);
        }
    }
}
