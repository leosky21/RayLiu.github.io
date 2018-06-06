package cn.ray.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller  
@RequestMapping("/file")  
public class FileUploadController {
	 @RequestMapping("/toFile")  
	    public String toFileUpload() {  
	        return "jsp/fileUpload";  
	    }  
	  
	    @RequestMapping("/toFile2")  
	    public String toFileUpload2() {  
	        return "jsp/fileUpload2";  
	    }  
	  
	    /** 
	     * 方法一上传文件 
	     */  
	    @RequestMapping("/onefile")  
	    public String oneFileUpload(  
	            @RequestParam("file") CommonsMultipartFile file,  
	            HttpServletRequest request, ModelMap model) {  
	  
	        // 获得原始文件名  
	        String fileName = file.getOriginalFilename();  
	        System.out.println("原始文件名:" + fileName);  
	  
	        // 新文件名  
	        String newFileName = UUID.randomUUID() + fileName;  
	  
	        // 获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("/src/main/resources") + "/"; // 设定文件保存的目录  
	  
	        File f = new File(path);  
	        if (!f.exists())  
	            f.mkdirs();  
	        if (!file.isEmpty()) {  
	            try {  
	                FileOutputStream fos = new FileOutputStream(path + newFileName);  
	                InputStream in = file.getInputStream();  
	                int b = 0;  
	                while ((b = in.read()) != -1) {  
	                    fos.write(b);  
	                }  
	                fos.close();  
	                in.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	  
	        System.out.println("上传图片到:" + path + newFileName);  
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileUrl", path + newFileName);  
	        return "jsp/fileUpload";  
	    }  
	  
	    /** 
	     * 方法二上传文件，一次一张 
	     */  
	    @RequestMapping("/onefile2")  
	    public String oneFileUpload2(HttpServletRequest request,  
	            HttpServletResponse response) throws Exception {  
	        CommonsMultipartResolver cmr = new CommonsMultipartResolver(  
	                request.getServletContext());  
	        if (cmr.isMultipart(request)) {  
	            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);  
	            Iterator<String> files = mRequest.getFileNames();  
	            while (files.hasNext()) {  
	                MultipartFile mFile = mRequest.getFile(files.next());  
	                if (mFile != null) {  
	                    String fileName = UUID.randomUUID()  
	                            + mFile.getOriginalFilename();  
	                    String path = "" + fileName;  
	  
	                    File localFile = new File(path);  
	                    //mFile.transferTo(localFile);  
	                    request.setAttribute("fileUrl", path);  
	                }  
	            }  
	        }  
	        return "jsp/fileUpload";  
	    }  
	    /** 
	     * 一次上传多张图片 
	     */  
	    @RequestMapping("/threeFile")  
	    public String threeFileUpload(  
	            @RequestParam("file") CommonsMultipartFile files[],  
	            HttpServletRequest request, ModelMap model) {  
	      
	        List<String> list = new ArrayList<String>();  
	        // 获得项目的路径  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String path = sc.getRealPath("/src/main/resources") + "/"; // 设定文件保存的目录  
	        File f = new File(path);  
	        if (!f.exists())  
	            f.mkdirs();  
	      
	        for (int i = 0; i < files.length; i++) {  
	            // 获得原始文件名  
	            String fileName = files[i].getOriginalFilename();  
	            System.out.println("原始文件名:" + fileName);  
	            // 新文件名  
	            String newFileName = UUID.randomUUID() + fileName;  
	            if (!files[i].isEmpty()) {  
	                try {  
	                    FileOutputStream fos = new FileOutputStream(path  
	                            + newFileName);  
	                    InputStream in = files[i].getInputStream();  
	                    int b = 0;  
	                    while ((b = in.read()) != -1) {  
	                        fos.write(b);  
	                    }  
	                    fos.close();  
	                    in.close();  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            System.out.println("上传图片到:" + path + newFileName);  
	            list.add(path + newFileName);  
	      
	        }  
	        // 保存文件地址，用于JSP页面回显  
	        model.addAttribute("fileList", list);  
	        return "fileUpload2";  
	      }
	    
	    /** 
	     * 列出所有的图片 
	     */  
	    @RequestMapping("/listFile")  
	    public String listFile(HttpServletRequest request,  
	            HttpServletResponse response) {  
	        // 获取上传文件的目录  
	        ServletContext sc = request.getSession().getServletContext();  
	        // 上传位置  
	        String uploadFilePath = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
	        // 存储要下载的文件名  
	        Map<String, String> fileNameMap = new HashMap<String, String>();  
	        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中  
	     //   listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录  
	        // 将Map集合发送到listfile.jsp页面进行显示  
	        request.setAttribute("fileNameMap", fileNameMap);  
	        return "listFile";  
	    }  
	    
	    @RequestMapping("/downFile")  
	    public void downFile(HttpServletRequest request,  
	            HttpServletResponse response) {  
	        System.out.println("1");  
	        // 得到要下载的文件名  
	        String fileName = request.getParameter("filename");  
	        System.out.println("2");  
	        try {  
	            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");  
	            System.out.println("3");  
	            // 获取上传文件的目录  
	            ServletContext sc = request.getSession().getServletContext();  
	            System.out.println("4");  
	            // 上传位置  
	            String fileSaveRootPath = sc.getRealPath("/img");   
	              
	            System.out.println(fileSaveRootPath + "\\" + fileName);  
	            // 得到要下载的文件  
	            File file = new File(fileSaveRootPath + "\\" + fileName);  
	              
	            // 如果文件不存在  
	            if (!file.exists()) {  
	                request.setAttribute("message", "您要下载的资源已被删除！！");  
	                System.out.println("您要下载的资源已被删除！！");  
	                return;  
	            }  
	            // 处理文件名  
	            String realname = fileName.substring(fileName.indexOf("_") + 1);  
	            // 设置响应头，控制浏览器下载该文件  
	            response.setHeader("content-disposition", "attachment;filename="  
	                    + URLEncoder.encode(realname, "UTF-8"));  
	            // 读取要下载的文件，保存到文件输入流  
	            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);  
	            // 创建输出流  
	            OutputStream out = response.getOutputStream();  
	            // 创建缓冲区  
	            byte buffer[] = new byte[1024];  
	            int len = 0;  
	            // 循环将输入流中的内容读取到缓冲区当中  
	            while ((len = in.read(buffer)) > 0) {  
	                // 输出缓冲区的内容到浏览器，实现文件下载  
	                out.write(buffer, 0, len);  
	            }  
	            // 关闭文件输入流  
	            in.close();  
	            // 关闭输出流  
	            out.close();  
	        } catch (Exception e) {  
	      
	        }  
	    }  
}
