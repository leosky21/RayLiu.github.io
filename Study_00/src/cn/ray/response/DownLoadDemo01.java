package cn.ray.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownLoadDemo01")
public class DownLoadDemo01 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取下载文件的绝对路径   File.pathSeparator----- :  /:     ========    File.separator  -- : /
		String path = this.getServletContext().getRealPath("/1.html");
		System.out.println("path::"+path);
		//2. 获取要下载文件名
		String filename = path.substring(path.lastIndexOf(File.separator)+1);
		System.out.println("filename::"+filename);
		//3.  设置浏览器响应头控制浏览器以下载形式打开文件
		response.setHeader("content-position", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));

		InputStream in = null;
		OutputStream out = null;
		
		try{
			//4. 获取下载的文件流
			in = new FileInputStream(path);
			int len = 0;
			//5. 设置缓冲区
			byte[] buffer = new byte[1024];
			//6. 获取输出流
			out = response.getOutputStream();
			//7. 将输入流写入到缓冲区 然后输出
			while((len = in.read())!=-1){
				// 8. 通过OutputStream 将缓冲区的数据输出到客户端浏览器
				out.write(buffer, 0, len);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(in != null){
				try{
					in.close();
				}catch (Exception e) {
				}
			}
			if(out !=null){
				try{
					out.close();
				}catch (Exception e) {
				}
			}
			
		}
	}

}
