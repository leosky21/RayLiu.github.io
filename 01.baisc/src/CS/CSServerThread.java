package CS;


import java.io.*;
import java.net.*;
import java.util.*;

public class CSServerThread extends Thread{
	Socket sc = null;
	String str = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	int[] fn={0,0};
	String[] sn={"0","0"};

	public CSServerThread(Socket s){
			this.sc = s;
			try{
				if(s!=null){
						in = new DataInputStream(sc.getInputStream());
						out = new DataOutputStream(sc.getOutputStream());
					}
				}catch(IOException e){}
		}

		public void run(){
			try{
				str = in.readUTF();// 要求两个整数是以逗号间隔
				//System.out.println("1:str=in.readUTF:str=:"+str);
				}catch(IOException e){

					}
			StringTokenizer stk = new StringTokenizer(str,",");
			int i=0;
			String backStr="";
			//System.out.println("countTokens:"+stk.countTokens());
			//循环读取值
			while(stk.hasMoreElements()){
				 sn[i]= stk.nextToken();
				// System.out.println(i+":sn[i]=="+sn[i]);
				 i++;
				// System.out.println("i==:"+i);
				}
				String aa= sn[0];
				String bb = sn[1];
//				System.out.println("------"+aa);
//				System.out.println("------"+bb);
				int a = Integer.parseInt(aa);
				int b = Integer.parseInt(bb);
				int jia = plus(a,b);
				int jian = minus(a,b);
				int cheng = multiply(a,b);
				int chu = divid(a,b);
				backStr +="加："+jia+"  "+"减："+jian+"  "+"乘："
									+cheng+"  "+"取余："+chu;
				try{
					out.writeUTF(backStr);
				}catch(IOException e){}
				}


		protected  int plus(int a,int b){
			return a+b;
			}
		protected int minus(int a,int b){
			return a-b;
			}
		protected int multiply(int a,int b){
			return a*b;
			}
		protected int divid(int a,int b){
			return a%b;
		}

}
