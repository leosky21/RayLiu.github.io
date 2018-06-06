package com.weibo.jg.collector;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;




/**
 * 字符串操作类
 * 该类提供字符串基本操作：截取字符，提取html中url、时间、作者、内容等信息，
 * 特殊字符过滤等操作
 * @author lj
 * @time 2016-1-19
 * */
public class StringUtilsTool {

	
	/**
	 * 读取数据
	 * 
	 * */
	public static String readDataStream(InputStream is,String encode){
		String result="";
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=-1;
		try{
			while((len=is.read(buffer))!=-1){
				outStream.write(buffer,0,len);
			}
			result=new String(outStream.toByteArray(),encode);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(outStream!=null)
					outStream.close();
				if(is!=null){
					is.close();
				}
			}catch(Exception e){
				
			}
		}
		return result;
	}
	public static String urlEncoder(String url,String encoding){
		try{
			Pattern p = Pattern.compile("[^~`!@#$%^&*()_\"\"'':;?//<>,.=]");
			Matcher m = p.matcher(url.trim());
			while (m.find()) {
			  url = url.replace(m.group(m.groupCount()), URLEncoder.encode(m.group(m.groupCount()), encoding));
			}
		}catch(UnsupportedEncodingException e){
			return url;
		}
		return url;
	}
	/**
	 * 
	 * 获取URLEncoder.encode编码
	 * **/
	public static String getUrlEncode(String keyword,String encoding){
		String encode="";
		try {
			encode=URLEncoder.encode(keyword, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encode;
	}
	/**
	 *	
	 * @param text  字符串
	 * @param start     开始时字符
	 * @param end   结束的字符
	 * @param isGroupCount  true表示不包含开始和结束的字符串，反之包含
	 * if true no include String start and end ,false include start and end string
	 */
	public static List<String> getBetween(String text, String start, String end,boolean isGroupCount) {
		List<String> results = new ArrayList<String>();
		String rexexp = start + "([\\s\\S]*?)" + end;
		Pattern p = Pattern.compile(rexexp);
		Matcher m = p.matcher(text);
		while (m.find()){
			if(isGroupCount)
				results.add(m.group(m.groupCount()));
			else
				results.add(m.group());
		}
		return results;
	}
	/**
	 *	
	 * @param text  字符串
	 * @param start     开始时字符
	 * @param end   结束的字符
	 * @param isGroupCount ,if true no include String start and end ,false include start and end string
	 */
	public static List<String> getBetweenUrl(String text, String start, String end,boolean isGroupCount) {
		List<String> results = new ArrayList<String>();
		String rexexp = start + "[a-zA-z]+://[^\\s]*" + end;
		Pattern p = Pattern.compile(rexexp);
		Matcher m = p.matcher(text);
		while (m.find()){
			if(isGroupCount){
				
				String url=m.group(m.groupCount()).replace(start, "").trim();
				url=url.replace(end, "").trim();
				results.add(url);
			}else{
				String url=m.group().replace(start, "").trim();
				url=url.replace(end, "").trim();
				results.add(url);
			}
		}
		return results;
	}
	//get page url
	public static List<String> getpageurl(String text) {
		List<String> results = new ArrayList<String>();
		String rexexp ="[a-zA-z]+://[^\\s]*";
		Pattern p = Pattern.compile(rexexp);
		Matcher m = p.matcher(text);
		while (m.find()){
			results.add(m.group());
		}
		return results;
	}
	//get page chinese 
	public static String gettextchinese(String text){
		String chinese="";
		//"[\u4e00-\u9fa5]";//
		String regex="[^\\x00-\\xff]";
		Pattern pat=Pattern.compile(regex);
		Matcher mat=pat.matcher(text);
		while(mat.find()){
			chinese+=mat.group();
		}
	    return chinese;
	}
	//get page time
	public static String getpagetime(String text) {
		String time="";
		//time format yyyy-mm-dd hh:mm
		//String regex="";
		String regex="(19[0-9]{2}|20[0-9]{2})(-|年|/)[01][0-9](-|月|/)[0-3][0-9]日?"
				+ "[\\s　]{0,9}[012][0-9]:[0-9]{2}";
		Pattern pat=Pattern.compile(regex);
		Matcher mat=pat.matcher(text);
		while(mat.find()){
			time=mat.group();
			time=time.replace("年", "-").replace("月", "-").replace("日", "");
			time=time.replace("/", "-").replace("/", "-");
		}
		return time;
	}
/**
 * 去除字符中html标签
 * 
 * **/
	public static String trimHtmlTag(String html) {
		if (html == null) {
			return "";
		} else {
			//去除<script>代码
			html = html.replaceAll("<script.+?>[\\s\\S]*?</script>", "").trim();
			html = html.replaceAll("</p>", "<br>");
			html = html.replaceAll("<br[\\s]*?[/]*?>", "==br==");
			html = html.replaceAll("<[^>]*>", "").trim();
			html = html.replaceAll("==br==", "<br>").trim();
			html = html.replace("<br>","").trim();
			return html.replaceAll("&nbsp;", " ");
		}
	}

	/**
	 * 最大程度获取正则匹配，
	 * */
	public static List<String> getMask(String boundInfo, String exp) {
		List<String> infos = new ArrayList<String>();
		Pattern pattern = Pattern.compile(exp);
		Matcher m=pattern.matcher(boundInfo);
		while(m.find()){
			infos.add(m.group(m.groupCount()));
		}
//		for (Matcher m = pattern.matcher(boundInfo); m.find(); infos.add(m
//				.group(m.groupCount())))
//			;
		return infos;
		
	}
	/**
	 *	获取特定的字符串,同时过滤掉html标签
	 * @param boundInfo  字符串
	 * @param start     开始时字符
	 * @param end   结束的字符
	 * 
	 */
	public static String extract(String boundInfo, String start, String end) {
		StringBuilder exp = (new StringBuilder().append(start).append(
				"([\\s\\S]*?)").append(end));
		Pattern pattern = Pattern.compile(exp.toString());
		Matcher m = pattern.matcher(boundInfo);
		if(m.find())
			return trimHtmlTag(m.group(m.groupCount()));
		return "";
	}
	
	/**
	 *	保存文件到磁盘中
	 * @param in  文件流
	 * @param dirPath	磁盘路径
	 * @param fileNane	文件名
	 */
	public static void saveFileToDisk(InputStream in, String dirPath, String fileNane) {

		try {
			File dir = new File(dirPath);
			if (dir == null || !dir.exists()) {
				dir.mkdirs();
			}

			// 文件真实路径
			String realPath = dirPath.concat(fileNane);
			File file = new File(realPath);
			if (file == null || !file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
/***
 * 
 * 获取特定的字符串,不过滤掉html标签
	 * @param boundInfo  字符串
	 * @param start     开始时字符
	 * @param end   结束的字符
 * **/
	public static String extractNotTrimHtmlTag(String boundInfo, String start,
			String end) {
		List<String> infos = new ArrayList<String>();
		StringBuilder exp = (new StringBuilder().append(start).append(
				"([\\s\\S]*?)").append(end));
		Pattern pattern = Pattern.compile(exp.toString());
		for (Matcher m = pattern.matcher(boundInfo); m.find(); infos.add(m
				.group(m.groupCount())))
			;
		return infos.size() > 0 ? infos.get(0) : "";
	}

	/**
	 * 将字符串保存到本地文件
	 * */
	public static void saveFile(String body, String fileName) {
        FileWriter writer;
        try {
            writer = new FileWriter("D:/"+fileName+".txt");
            writer.write(body);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * 读取文本文件中数据
	 * @param filePath 文件路径
	 * */
	 public static String readTxtFile(String filePath,String encoding){
		 StringBuffer stringBuffer=new StringBuffer();
	      try {
	           //  String encoding="GBK";
	            File file=new File(filePath);
	            if(file.isFile() && file.exists()){ //判断文件是否存在
	            	//考虑到编码格式
	                 InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);
	                 BufferedReader bufferedReader = new BufferedReader(read);
	                 String lineTxt = null;
	                 while((lineTxt = bufferedReader.readLine()) != null){
	                	 stringBuffer.append(lineTxt);
	                     //System.out.println(lineTxt);
	                  }
	                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return stringBuffer.toString();
	    }
	 /**
	  * 判断字符串是否为空
	  * */
	public static boolean isNotEmpty(String s) {
		return s == null ? false : s.length() > 0;
	}

	public static String getJsonValue(String bodyStr, String key) {
		bodyStr = bodyStr.substring(0, bodyStr.length() - 1) + ",}";
		Pattern pattern = Pattern.compile("\"[\\s]*" + key
				+ "[\\s]*\"[\\s]*:[\\s]*\"[\\s]*[\\s\\S]*?\"\\,");
		Matcher matcher = pattern.matcher(bodyStr);
		String result = null;
		if (matcher.find()) {
			result = matcher.group().replaceAll("\"" + key + "\":\"", "")
					.replaceAll("\"", "");
		} else {
			pattern = Pattern.compile("\"" + key + "\":[\\s\\S]*?,");
			matcher = pattern.matcher(bodyStr);
			if (matcher.find()) {
				result = matcher.group().replaceAll("\"" + key + "\":", "")
						.replaceAll(",", "");
			}
		}
		if (result == null)
			return result;
		int sign = result.lastIndexOf(",");
		if (sign == result.length() - 1)
			result = result.substring(0, sign);
		return result;
	}

	public static List<String> getJsonValues(String bodyStr, String key) {
		Pattern pattern = Pattern.compile("\"" + key + "\":\"[\\s\\S]*?\"");
		Matcher matcher = pattern.matcher(bodyStr);
		List<String> result = new ArrayList<String>();
		if (matcher.find()) {
			result.add(matcher.group().replaceAll("\"" + key + "\":\"", "")
					.replaceAll("\"", ""));
			while (matcher.find()) {
				result.add(matcher.group().replaceAll("\"" + key + "\":\"", "")
						.replaceAll("\"", ""));
			}
		} else {
			pattern = Pattern.compile("\"" + key + "\":[\\s\\S]*?,");
			matcher = pattern.matcher(bodyStr);
			if (matcher.find()) {
				result.add(matcher.group().replaceAll("\"" + key + "\":", "")
						.replaceAll(",", ""));
				while (matcher.find()) {
					result.add(matcher.group()
							.replaceAll("\"" + key + "\":", "")
							.replaceAll(",", ""));
				}
			}
		}
		return result;
	}
	
	public static Invocable getScriptEngine_Invocable(String javascript){
		try {
			javascript =  javascript.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("javascript");
			engine.eval(javascript);
			if (engine instanceof Invocable) {
				Invocable invoke = (Invocable) engine;
				return invoke;
			}else{
				return null;
			}
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}
	}
	//unicode convert chinese
	//example:\u9053\u743c\u65af
	public static  String unicodeconvertchinese(String utfString){     
		    char aChar;        
		    int len = utfString.length();      
		    StringBuffer outBuffer = new StringBuffer(len);      
		    for (int x = 0; x < len;) {      
		    	aChar = utfString.charAt(x++);      		   
		    	if (aChar == '\\') {      		    
		    		aChar = utfString.charAt(x++);      		  
		    		if (aChar == 'u') {      		   
		    			// Read the xxxx      		   
		    			int value = 0;      	   
		    			for (int i = 0; i < 4; i++) {      	   
		    				aChar = utfString.charAt(x++);      		   
		    				switch (aChar) {      		 
		    				case '0':      		   
		    				case '1':      		   
		    				case '2':      		  
		    				case '3':      
		  		    		case '4':      		   
		    				case '5':      		  
		    				case '6':      
		    				case '7':      
		    				case '8':      
		    				case '9':      
		    					value = (value << 4) + aChar - '0';      
		    					break;      
		    				case 'a':      
		    				case 'b':      
		    				case 'c':      
		    				case 'd':      
		    				case 'e':      
		    				case 'f':      
		    					value = (value << 4) + 10 + aChar - 'a';      
		    					break;      
		    				case 'A':      
		    				case 'B':      
		    				case 'C':      
		    				case 'D':      
		    				case 'E':      
		    				case 'F':      
		    					value = (value << 4) + 10 + aChar - 'A';      
		    					break;      
		    				default:      
		    					throw new IllegalArgumentException("Malformed \\uxxxx  encoding.");      
		         }  //switch END    		   
		       } //for i END     
		         outBuffer.append((char) value);      
		       } else {      
		    	   if (aChar == 't')      
		    		   aChar = '\t';      
		    	   else if (aChar == 'r')      
		    		   aChar = '\r';      	   
		    	   else if (aChar == 'n')      		  
		    		   aChar = '\n';      	   
		    	   else if (aChar == 'f')      		   
		    		   aChar = '\f';      		   
		         outBuffer.append(aChar);      		   
		        }      		   
		       } else     		   
		       outBuffer.append(aChar);      		   
		      } //for x END     		   
		   return outBuffer.toString();      
	}
////chinese convert to unicode
	 public static String chinaToUnicode(String str){  
		        String result="";  
		        for (int i = 0; i < str.length(); i++){  
		             int chr1 = (char) str.charAt(i); 
		           //汉字范围 \u4e00-\u9fa5 (中文)
		             if(chr1>=19968&&chr1<=171941){  
		                 result+="\\u" + Integer.toHexString(chr1);  
		             }else{  
		                 result+=str.charAt(i);  
		             }  
		         }  
		        return result;  
		     }  
	 
	 /**
	  * 将汉字转换为unicode
	  * */
	 public static String chineseToUnicode(String str){
		 String result="";
		// int i;
		 char j;
		 StringBuffer tmp=new StringBuffer();
		 tmp.ensureCapacity(str.length()*6);
		 for(int i=0;i<str.length();i++){
			 j=str.charAt(i);
			 if(Character.isDigit(j)|| Character.isLowerCase(j)
					 ||Character.isUpperCase(j)){
				 tmp.append(j);
			 }else if(j<256){
				 tmp.append("%");
				 if(j<16)
					 tmp.append("0");
				 tmp.append(Integer.toString(j,16));
			 }else{
				 tmp.append("%u");
				 tmp.append(Integer.toString(j,16));
			 }
		 }
		 result=tmp.toString();
		 return result;
	 }

	 public static String toStringHex1(String s) { 
		 byte[] baKeyword = new byte[s.length() / 2]; 
		 String[] word=s.split("x");
		 for(String str:word){
			 System.out.println(str.replace("\\", "").trim()+"===str");
		 }
		 for (int i = 0; i < baKeyword.length; i++) { 
			 try {     
				 baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2),16));  
			} catch (Exception e) { 
				e.printStackTrace();     
				}    
			 }    
		 try {   
			 for(int i=0;i<baKeyword.length;i++){
				 System.out.println(baKeyword[i]+"===");
			 }
			 s = new String(baKeyword, "ASCII"); 
			 } catch (Exception e1) {    
				 e1.printStackTrace();   
			}     return s;  
	}
	
}
