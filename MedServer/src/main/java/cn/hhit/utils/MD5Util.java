package cn.hhit.utils;

import java.security.MessageDigest;

import com.sun.mail.util.BASE64EncoderStream;

import sun.misc.BASE64Encoder;

public class MD5Util {
	
	public static String md5Login(String username,String pass ){
		try{
			//密码+id  加密,增加破解难度,同时也为了方便寻找
			String value = pass+":"+username;
			
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5 = md.digest(value.getBytes());
			
			BASE64Encoder bs = new BASE64Encoder();
			return bs.encode(md5);
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
