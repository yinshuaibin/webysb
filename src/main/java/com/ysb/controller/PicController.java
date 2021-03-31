package com.ysb.controller;

import com.ysb.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yinshuaibin
 * @date 2021/3/31 10:24
 * @description
 */
@RestController
public class PicController extends BaseController {

    @Value("${picPath}")
    private String filePath;


    @PostMapping("/savePic")
    public String savePic(MultipartFile file){
        if (file.isEmpty()){
            return "file is empty";
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath + File.separator + file.getOriginalFilename())) {
            // file.transferTo(new File(filePath + File.separator + file.getOriginalFilename()));
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "save pic error: " + e.getMessage();
        }
        return "success";
    }

    @GetMapping("/delPic")
    public String delPic(String fileName){
        File f = new File(filePath + File.separator + fileName);
        if (f.exists()){
            boolean delete = f.delete();
            if (delete){
                return "success";
            }
        }
        return "error";
    }

    @GetMapping("/getAllPic")
    public String[] getAllPic(){
        File file = new File(filePath);
        if (file.exists()){
            return file.list();
        }
        return new String[]{};
    }


    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream(new File(""));
        FileInputStream in = new FileInputStream(new File(""));
        int byteCount = 0;
        int bytesRead;
        for(byte[] buffer = new byte[4096]; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
            out.write(buffer, 0, bytesRead);
        }
        System.out.println(byteCount);
    }
}
