package CS2;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client1{
	public static void main(String args[]){
		String s=null;
		int rs=0;
		Scanner scan = new Scanner(System.in);
		System.out.print("\n请输入一个二元算式：\n                ");
		s=scan.nextLine();
		Net net = new Net();
		try{
			net.out.writeUTF(s);
			net.out.writeUTF("#*#");
			rs = net.in.readInt();
			net.socket.close();
		}
		catch(IOException e){System.out.println("错误："+e);}
		System.out.println("接受到的结果为："+rs+"\n");

	}
}

class Net{
	Socket socket = null;
	DataInputStream in = null;
	DataOutputStream out =null;
	Net(){
		try{
			socket = new Socket("127.0.0.1",8080);
			in = new DataInputStream(socket.getInputStream());
			out= new DataOutputStream(socket.getOutputStream());
		}
		catch(Exception e){
			System.out.println("断开："+e);
		}

	}

}