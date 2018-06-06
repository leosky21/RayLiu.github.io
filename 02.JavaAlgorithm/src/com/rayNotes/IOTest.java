package com.rayNotes;

import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;

/**
 * 	- IO流常用基类
 * 		- 字节流的抽象基类： 
			- InputStream
			- OutputStream
		- 字符流的抽象基类：
			- Reader
			- Writer
		- 备注:	
			由这四个类派生出来的子类名称都是以其父类名作为子类名的后缀的。
				例如，InputStream的子类——FileInputStream，Reader的子类——FileReader。
 * @author ray
 *
 */

public class IOTest {
	//\r\n在Windows中表示行终止符，但在其他系统中并不是这样,
	//	使用System类的getProperty(“line.separator”)方法得到系统的行终止符
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public static void main(String[] args) throws IOException{
		IOTest i = new IOTest();
		i.fileOutput();
		i.fileInput();
System.out.println(LINE_SEPARATOR+"");
	}
/**
 *	- 字节流 : 字节流的基本操作与字符流类同，但它不仅可以操作字符，还可以操作其他媒体文件
 *		- FileInputStream
 *		- FileOutputStream
 *		- BufferedInputStream
 *		- BufferedOutputStream
 */
	private void  fileOutput() throws IOException{
		// 1.创建字节输出流对象，用于操作文件，在对象初始化时必须明确数据存储的目的地。
        // 输出流所关联的目的地，如果不存在，会自动创建。如果存在，则覆盖。
		 FileOutputStream stream = new FileOutputStream("IOTestOut.dat");
		// 2.第二个参数决定是否进行追加操作，
//		    若不追加，当前文件所有内容都会被**清除**，然后重写数据。
//		FileOutputStream stream = new FileOutputStream("./IOTestOut.dat",true);
		// 3、调用输出流的写功能输出出来结果是b，98对应的ascii码
		stream.write(98);
//		String str = LINE_SEPARATOR+"\n追加的信息"+System.currentTimeMillis();
		String str = "\n追加的信息"+System.currentTimeMillis();
		byte[] buffer = str.getBytes("utf-8");
		stream.write(buffer);
		// 4、释放资源。
		stream.close();
	}
	private void fileInput() throws IOException{
		FileInputStream stream = new FileInputStream("./IOTestOut.dat");
		int i = stream.read();
		System.out.println(i);
		System.out.println("-----------1-------------");
		/*第二种*/
		byte[] buffer = new byte[37];
		int len = stream.read(buffer);
		String str = new String(buffer,"utf-8");
		System.out.println(stream+"::"+len);
		System.out.println("------------2------------");
		System.out.println(str);
		/*第三种 : 不建议使用..容易溢出*/
		System.out.println("----------3--------------");
		System.out.println(stream.available());// 获取与之关联的文件的字节数。
		byte[] buffer2 = new byte[stream.available()];
		stream.read(buffer2);
		String s = new String(buffer2);
		System.out.println(s);
		System.out.println("------------4------------");
		stream.close();
	}

	/**
	 * File类：
	 * 		- 构造方法
	 * 			- 可以将已有和未出现的文件或文件夹封装成对象
	 * 				- File f1 = new File("a.txt");
	 * 				- File f2 = new File("c:\\abc", "b.txt");
	 * 				- File d = new File("c:\\abc");
					  File f3 = new File(d, "c.txt");
	 * 
	 * 		- 间隔字段	
	 * 				- separator字段
	 * 					- File f4 = new File("."+File.separator+"abc"+File.separator+"zzz"+File.separator+"a.txt");
	 * @throws IOException 
	 */
	@Test
	public void fileTest() throws IOException {
		File f1 = new File("a.txt");
		File f2 = new File("/abc", "b.txt");
		File d = new File("/abc");
		File f3 = new File(d, "c.txt");
		File f4 = new File("abc"+File.separator+"zzz"+File.separator+"a.txt");
		//只是建立这个对象，并没有真的出现这个文件
		System.out.println("path:"+f4.getPath()); 
		System.out.println("abspath:"+f4.getAbsolutePath()); 
		System.out.println("parent:"+f4.getParent()); 
		System.out.println("long length():"+f4.length());
		System.out.println("lastModified():"+f4.lastModified());
		
		//createFile()	执行创建操作
//		boolean bo = f4.createNewFile();//路径文件夹不存在
		boolean bo = f4.mkdirs();//路径所有文件夹都已经存在则返回false,不会创建文件:: abc/zzz/a.txt/  都是文件夹名
		System.out.println(f4.getPath());
		boolean bo1 = f1.createNewFile();//已经存在就return false
		System.out.println(bo+"::"+bo1);
		f1.delete();
		f1.exists();
		f1.isHidden();
		f4.isDirectory();
		f4.isFile();
		d.mkdir();//还有个mkdirs(),更好用：创建多级文件夹
	//删除这个路径下的最后一个文件夹
		f4.delete();//删除失败返回false，删除文件夹时，必须保证该文件夹没有内容，如果有内容，必须先把内容删除后，才可以删除当前文件夹
//		f4.deleteOnExit();//在程序退出时删除指定文件，告诉JVM，一会退出时删除文件(一般是临时文件)，即使发生异常。
	}
	/**
	 * 	file 类中
	 *		-  list()与listFiles()方法
	 *
	 *			- 调用list()方法的File对象必须是封装了一个目录，而且该目录还必须存在
	 */
	@Test
	public void fileListTest() throws IOException {
		File f = new File("./");
		String[] names = f.list();
		if (names != null) {
		    for (String name : names) {
		        System.out.println(name);
		    }
		}else
			 System.out.println("null");
		 System.out.println("-------------------------------------");
		File[] fnames = f.listFiles();
		if (fnames != null) 
		    for (File name : fnames) {
		        System.out.println(name);
		    }
		System.out.println("-------------------------------------");
//		String[] Fileternames = f.list(new Filter());
//		for (String name : Fileternames) {
//	        System.out.println(name);
//	    }
	}
	/**
	 *  - 练习 : 
	 *  		递归实现目录中所有的文件
	 */
	@Test
	public void testGetAllFiles() {
		File f = new File("./");
//		getAllFiles(f);
		
//		toBin(4);
//		System.out.println(	toSum(10));
		List<File> l =  new ArrayList<File>();
		 l = toAllFile(f,l,new Filter());
		
		System.out.println(l.toString());
		
	}
	public void getAllFiles(File dir) {
		File[] names = dir.listFiles();
		for(File name : names) {
			 if(name.isDirectory()) {
				 getAllFiles(name);
			 }else {
				 System.out.println(name);
			 }
		}
	}
	/**
	 * 	- 练习:
	 * 		递归: 将十进制转换成二进制
	 */
	public  void toBin(int num) {
	    if(num > 0) {
	    	   	toBin(num / 2);
	        System.out.println(num % 2);//  '%' 取余 
	    }
	}
	/**
	 * 	- 练习
	 * 		递归 :
	 * 			输入一个数字,求从他开始到1的和
	 */
	public int toSum(int num) {
		if(num == 1)
			return num;
		
		return num+toSum(num-1);
	}
	/**
	 *  - 练习
	 *  		递归:
	 *  			获取某路径目录下,获取该目录及子目录下下所有以.java结尾的文件
	 */
	public List<File> toAllFile(File dir,List<File> list,
					/*FilenameFilter filter*/FileFilter filter) {
		File[] files = dir.listFiles();
		for(File file : files) {
			if(file.isDirectory()) 
				toAllFile(file,list,filter);
			else {
				if(filter.accept(file)
				/*filter.accept(dir, file.getName())*/)
					list.add(file);
			}
		}
		return list;
	}
	
}
/**
 * 	FilenameFilterw ::文件过滤器:	
 * 			返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
 * @author ray
 *
 */
class Filter implements /*FilenameFilter*/FileFilter{
	/**
	 * 
     * @param dir   被过滤的目录。
     * @param name  被遍历目录中的文件夹或者文件的名称。
	 */
//	@Override
//	public boolean accept(File dir, String name) {
//		
//		return name.endsWith(".java");
//	}

	@Override
	public boolean accept(File pathname) {
		
		return pathname.getName().endsWith(".java");
	}
	
}