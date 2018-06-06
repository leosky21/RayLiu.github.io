package CS;


import java.io.*;
import java.net.*;

public class Cilent{
	public static Socket s = null;
	public static DataInputStream in = null;
	public static DataOutputStream out = null;
	
	public static void main(String[] argv){
		String sss="";
		String st="";
		//ssa = new String[2];
		try{
		s = new Socket("127.0.0.1",9688);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());

	

		System.out.println("输入两个不为0的数字，用“,”隔开 如： 1,2 ：");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		st = br.readLine();
		}catch(IOException e){e.printStackTrace();
		}//catch(UnknowHostException ee){ee.printStackTrace();}
		try{
			out.writeUTF(st);
	  }catch(Exception ex){}

	  try{
		 // System.out.println("--------");
		  sss = in.readUTF();
		  System.out.println("--->>"+sss);
		  in.close();
		  out.close();
		  }catch(Exception el){

			  }
	 
}
}