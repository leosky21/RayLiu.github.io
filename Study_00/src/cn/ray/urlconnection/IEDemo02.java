package cn.ray.urlconnection;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IEDemo02 {
	public static void main(String[] args) throws Exception {
		request();
	}
	
	// 向服务器发送http请求
    public static void request() throws Exception {
        URL url = new URL("http://localhost:8080/Study_00/DoBackServlet2");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        if(conn.getResponseCode()==200){
	        
        	conn.setRequestMethod("POST"); // 设置请求方式
	        conn.setRequestProperty("Referer", "http://www.sina.com"); // 设置请求头
	       
	       conn.setDoOutput(true); // 默认是无法向服务器写数据的
	       
	       //如果调用了conn.setDoOutput(true)这个方法的话，默认的 GET 请求会改变为 POST 请求，因为 GET 请求是没有 request body 的。这就解释了之前为什么没有设置请求方法默认应该是 GET 请求，抓包缺看到的是 POST 请求了。
	       // 向服务器提交请求参数
	        OutputStream out = conn.getOutputStream(); 
	        out.write("name=aaaa".getBytes());
	        
	       	//System.out.println(conn.getResponseCode()); // 处理响应
	       }
       // System.out.println(conn.getResponseCode()); // 处理响应
      
    }
   /**
    *  这样修改好之后是不是万事大吉了呢？
    *  		不是，运行以上程序，服务器在控制台并没有任何输出。
    *  		这又是什么原因引起来的呢？ 
    *  
    *  
    *   答：一个完整的http请求是由请求和响应两部分组成的，
    *   	以上程序只有向服务器发送的请求，而没有服务器回送回来的响应，
    *   	所以这不是一个完整的http请求，才导致这样的问题发生。
    */
    
}
