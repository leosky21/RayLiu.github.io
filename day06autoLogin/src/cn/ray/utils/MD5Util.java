package cn.ray.utils;

import java.security.MessageDigest;

import com.sun.mail.util.BASE64EncoderStream;

import sun.misc.BASE64Encoder;

public class MD5Util {
	
	public static String md5Login(String username,String pass,long time ){
		try{
			String value = pass+":"+time+":"+username;
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(value.getBytes());
			
			BASE64Encoder bs = new BASE64Encoder();
			return bs.encode(md5);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
