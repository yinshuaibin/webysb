package com.ysb.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


/**
 * 如使用RestTemplate 可以使用@Autowired注入方式, 也可使用RestTemplateUtils.restTemplate
 * postForEntity方法为设置了spplication/json头的utf8编码的http请求
 * @author abin
 */
@Configuration
@Order(1)
public class RestTemplateXmlUtils {

    private static RestTemplate staticRestTemplate;

    private static RestTemplate staticRestTemplateHttps;

    private static HttpHeaders httpHeadersXml;

    @Autowired
    private RestTemplate restTemplate;

    public static HttpHeaders httpHeadersXml(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return headers;
    }

    public static ResponseEntity postForEntityXml(String url, String xml){
        HttpEntity entity = new HttpEntity<>(xml, httpHeadersXml);
        return staticRestTemplate.postForEntity(url, entity, String.class);
    }

    public static ResponseEntity postForEntityXmlHttps(String url, String xml){
        HttpEntity entity = new HttpEntity<>(xml, httpHeadersXml);
        return staticRestTemplateHttps.postForEntity(url, entity, String.class);
    }

    @PostConstruct
    public void init(){
        staticRestTemplate = this.restTemplate;
        httpHeadersXml = httpHeadersXml();
        staticRestTemplateHttps = new RestTemplate(new HttpsClientRequestFactory());
    }

}
