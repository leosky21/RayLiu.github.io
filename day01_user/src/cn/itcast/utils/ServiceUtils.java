package cn.itcast.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class ServiceUtils {
	public static String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(message.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            // 抛一个运行时异常就得了，没必要给外层造成麻烦
            throw new RuntimeException(e);
        }
    } 
}
