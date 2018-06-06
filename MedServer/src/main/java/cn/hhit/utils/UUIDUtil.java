package cn.hhit.utils;

import java.util.UUID;

public class UUIDUtil {
	  
	public static String generateID(){        
          return UUID.randomUUID().toString();
      }
}
