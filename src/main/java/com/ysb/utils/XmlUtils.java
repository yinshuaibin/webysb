package com.ysb.utils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinshuaibin
 * @date 2020/6/12 9:42
 */
public class XmlUtils {


    /**
     * 不生成xml声明的javaBean转xml(默认无格式, utf8)
     * https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/Marshaller.html
     *
     * @param obj
     * @param load
     * @return
     * @throws JAXBException
     */
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 不生成xml声明
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }

    /**
     * https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/Marshaller.html
     * @param xml xmlString
     * @param c
     * @param <T> 返回值类型
     * @return
     */
    public static <T> T xmlToJavaBean(String xml, Class<T> c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T)unmarshaller.unmarshal(new StringReader(xml));
    }
}
