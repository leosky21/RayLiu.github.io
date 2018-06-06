package cn.hhit.eshop.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    /**
     * Base64 encode
     * */
    public static String base64Encode(String data){
        return Base64.encodeBase64String(data.getBytes());
    }
     
    /**
     * Base64 decode
     * @throws UnsupportedEncodingException 
     * */
    public static String base64Decode(String data) throws UnsupportedEncodingException{
        return new String(Base64.decodeBase64(data.getBytes()),"utf-8");
    }
     
    /**
     * md5
     * */
    public static String md5Hex(String data){
        return DigestUtils.md5Hex(data);
    }
     
    /**
     * shaHex
     * */
    public static String shaHex(String data){
        return DigestUtils.shaHex(data);
    }
     
    /**
     * sha256
     * */
    public static String sha256Hex(String data){
        return DigestUtils.sha256Hex(data);
    }
     
}