package CS2;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server1{
	public static void main(String args[]){
		int x,y,rs=-101;
		SNet net = new SNet();
		do{
			net.Wait();
			while(net.socket!=null){
				try{
					String ss = net.in.readUTF();
					String s  = net.in.readUTF();
					System.out.print("接受到算式："+ss);
					String p[] = ss.split("\\D");
					String r[] = ss.split("\\d");
					String rr=null;
					for(int i=0;i<=100;i++){
						if(r[i].compareTo("+")==0||r[i].compareTo("-")==0||r[i].compareTo("*")==0||r[i].compareTo("/")==0)
						{	rr=r[i];break;}
					}
					//System.out.print("|"+p[0]+rr+p[1]+"|");
					x=Integer.parseInt(p[0]);
					y=Integer.parseInt(p[1]);

					if(rr.compareTo("+")==0)
						rs=x+y;
					if(rr.compareTo("-")==0)
						rs=x-y;
					if(rr.compareTo("*")==0)
						rs=x*y;
					if(rr.compareTo("/")==0)
						rs=x/y;
					System.out.println("返回答案："+rs);
					net.out.writeInt(rs);
					if(s.compareTo("#*#")==0){net.socket=null; break;}

				}
				catch(Exception e){net.socket=null; System.out.println("错误："+e);}
				try{Thread.sleep(50);}
				catch(Exception e){net.socket=null; System.out.println("2错误："+e);}
			}
			try{Thread.sleep(1000);}
			catch(Exception e){net.socket=null; System.out.println("2错误："+e);}
		}while(true);
	}
}

class SNet{
	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out =null;
	ServerSocket server = null;
	SNet(){
		try{
			server = new ServerSocket(8080);
		}
		catch(Exception e){
			System.out.println("断开："+e);
		}
	}
	void Wait(){
		try{
			System.out.println("\n服务器等待连接...");
			socket = server.accept();
			System.out.println(">>>客户"+socket.getInetAddress()+"连接成功！");
			in = new DataInputStream(socket.getInputStream());
			out= new DataOutputStream(socket.getOutputStream());
		}
		catch(Exception e){
			System.out.println("断开："+e);
		}
	}
}