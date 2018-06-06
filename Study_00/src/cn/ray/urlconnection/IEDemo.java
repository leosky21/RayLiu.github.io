package cn.ray.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IEDemo {
	public static void main(String[] args) {
		try {
			response();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void response() throws IOException {
		URL url = new URL("http://localhost:8080/Study_00/DoBackServlet");
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println(conn.getResponseCode()); // 得到http响应状态码
        System.out.println(conn.getHeaderField("Content-Length")); // 得到http响应某个头字段
        
        InputStream in = conn.getInputStream();
        byte[] buffer = new byte[1024];
        int len=0;
        while((len=in.read(buffer))>0){
        	System.out.println(new String(buffer,0,len));
        }
	}
}
