package cn.ray.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IEDemo03 {

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
		URL url = new URL(
				" http://api.avatardata.cn/Drug/name?key=0c34f0fd599e4a9f884193e1b57658e9&name=氨酚巴妥片");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println(conn.getResponseCode()); // 得到http响应状态码
        System.out.println(conn.getHeaderField("Content-Length")); // 得到http响应某个头字段
       // conn.setRequestMethod("POST");
	        InputStream in = conn.getInputStream();
	        byte[] buffer = new byte[1024];
	        int len=0;
	        while((len=in.read(buffer))>0){
	        	System.out.println(new String(buffer,0,len));
	        }
		}
	

}
