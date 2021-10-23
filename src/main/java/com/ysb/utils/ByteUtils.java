package com.ysb.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author yinshuaibin
 * @date 2021/9/17 16:11
 * @description
 */
public class ByteUtils {

    public static void main(String[] args) {
        String s = "wms_123_456_789_张三";
        String toHexString2 = ToHexString2(s);
        System.out.println(toHexString2);
        System.out.println(hexStr2Str(toHexString2.toUpperCase()));
        System.out.println("------------");
        String str2HexStr = str2HexStr(s);
        System.out.println(hexStr2Str(str2HexStr));
        byte[] bytes = HexString2Bytes(str2HexStr(s));
        System.out.println(bytes.length);
        byte[] bytes1 = s.getBytes();
        System.out.println(s.getBytes().length);
        System.out.println(Bytes2HexString(bytes, bytes.length));
    }


    private static void test(){
        //        System.out.println(0xF);
//
//        //System.out.println("0x" + Integer.toHexString(254).toUpperCase());
//        System.out.println((byte)0xFE);
//        System.out.println( (byte)(254 & 0xff));
//        ByteBuffer allocate = ByteBuffer.allocate(4);
//        byte head = (byte)0xEA;
//        short len = (short)188;
//        allocate.put(3, head);
//        System.out.println("1234".getBytes().length);
//        byte[] bytes = "WMS_叨叨_M000001_000000202".getBytes(StandardCharsets.UTF_8);
//        System.out.println(bytes.length);
        String trim = "wms_9999999999_00000000000000";
        byte[] newEPCByte = trim.getBytes(StandardCharsets.UTF_8);
        byte[] pcByte = new byte[] { 0x00, 0x00 };
        pcByte[0] = (byte) (newEPCByte.length * 4);
        System.out.println(pcByte[0]);
        String pc = ByteUtils.Bytes2HexString(pcByte, 2);
        byte[] bytes = ByteUtils.HexString2Bytes(pc);
        System.out.println("WMS_叨叨叨_M000001_0".getBytes().length);
        System.out.println(bytes.length);

        String s="123_bf";
        String regex="^[A-Fa-f0-9]+$";
        if(s.matches(regex)){
            System.out.println(s.toUpperCase()+"是16进制数");
        }else{
            System.out.println(s.toUpperCase()+"不是16进制数");

        }
    }

    private static String hexString = "0123456789ABCDEF";

    public static String str2HexStr(String str){
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        // 将字节数组中每个字节拆解成2位16进制数
        for (int i = 0; i < bytes.length; i++){
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f)));
        }
        return sb.toString();
    }

    public static String hexStr2Str(String hexStr){
        int length = hexStr.length();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length / 2);
        for (int i = 0; i < length; i += 2){
            byteArrayOutputStream.write(hexString.indexOf(hexStr.charAt(i)) << 4 |
                    hexString.indexOf(hexStr.charAt(i + 1)));
        }
        return new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
    }

    public static String Bytes2HexString(byte[] b, int size) {
        String ret = "";
        for(int i = 0; i < size; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }

            ret = ret + hex.toUpperCase();
        }

        return ret;
    }

    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[]{src0}));
        _b0 = (byte)(_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1}));
        byte ret = (byte)(_b0 ^ _b1);
        return ret;
    }

    public static byte[] HexString2Bytes(String src) {
        int len = src.length() / 2;
        byte[] ret = new byte[len];
        byte[] tmp = src.getBytes();
        for(int i = 0; i < len; ++i) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    public static String ToHexString2(String str){
        StringBuilder sb = new StringBuilder();
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        for (byte b : bytes){
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }
}
