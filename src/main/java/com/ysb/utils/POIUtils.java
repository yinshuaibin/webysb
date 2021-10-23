package com.ysb.utils;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yinshuaibin
 * @date 2021/9/18 10:52
 * @description
 */
public class POIUtils {

    public static String soc2String(FileInputStream fileInputStream) throws IOException {
        WordExtractor wordExtractor = new WordExtractor(fileInputStream);
        wordExtractor.close();
        return String.valueOf(wordExtractor.getText());
    }

    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
        String s = "D:\\doc\\123.doc";
        String s2 = "D:\\doc\\12345.docx";
        System.out.println(checkDoc(s));
        System.out.println(checkDocs(s2));
    }



    private static boolean checkDocs(String s) {
        try{
            OPCPackage opcPackage = POIXMLDocument.openPackage(s);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            extractor.getText();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkDoc(String s) throws IOException {
        try (WordExtractor wordExtractor = new WordExtractor(new FileInputStream(s));){
            wordExtractor.getText();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
