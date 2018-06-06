package CS4;

import java.io.*;
import java.net.*;
import java.util.*;
class ServerSocketDuo implements Runnable{
	ServerSocket ss = null;
	Socket s=null;
	Vector<Socket> userList = new Vector();//存储Socket线性表

	public ServerSocketDuo(int port){
		try{
		ss = new ServerSocket(port);//port是唯一标识
		System.out.println("启动本地"+port+"端口");
		}catch (IOException e) {
			System.out.println("启动本地"+port+"端口失败");
			System.exit(1);
		}
	}
	public void run(){
		try{
			while(true){
				s = ss.accept();
				userList.add(s);
				ClientThread ct = new ClientThread(s);
				Thread tct = new Thread(ct);
				tct.start();
			}
		}catch (IOException e) {
			System.err.println(e.toString());
		}
	}


class ClientThread implements Runnable{
	Socket s = null;
	ClientThread(Socket s){
		this.s = s;
	}
	public void run(){
		String inStr="",OutStr="";
		DataInputStream is = null;
		DataOutputStream os = null;
		DataInputStream stdin = null;

		try{
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
			os.writeUTF("welcom to Ray's test...");
			//立即将缓存数据移交上网络传输,要刷新缓存了!
			os.flush();
			// stdin = new DataInputStream(System.in);
			inStr = is.readUTF();
				while(inStr != null){
					System.out.println("customer: "+ inStr);
					// System.out.print("Server: ");
					for (Socket cs : userList) {//遍历客户机端Socket
						os = new DataOutputStream(s.getOutputStream());
						os.writeUTF("client"+OutStr);
					}
					// OutStr = stdin.readLine();
					// os.writeUTF(OutStr);
					// os.flush();
					if(inStr.equals("bye"))break;
				}
				os.close();
				is.close();
				s.close();
			}catch (IOException e) {
				System.err.println(e.toString());
			}

		}
	}
	public static void main(String[] args) {
		ServerSocketDuo ssd = new ServerSocketDuo(8000);
		Thread tssd = new Thread(ssd);
		tssd.start();

	}
}