package CS4;

import java.util.*;
import java.io.*;
import java.net.*;

public class clientSocketDuo implements Runnable{
	Socket cs  = null;
	 String hostName = "localhost";
	int port = 8000;
	DataInputStream in = null;
	DataOutputStream out = null;
	DataInputStream stdin = null;

	clientSocketDuo(String hostName,int port){
		this.hostName = hostName;
		this.port = port;
	}
	@Override
	public void run(){
		try{
		cs = new Socket(hostName,port);
		in = new DataInputStream(cs.getInputStream());
		out = new DataOutputStream(cs.getOutputStream());
		stdin = new DataInputStream(System.in);

		System.out.println("--------------------客户端----------------------");
		out.writeUTF("hello,server!");
		out.flush();
		//启动接受线程
		new rcThread(cs).start();
		new scThread(cs).start();
		//启动发送线程
		}catch (IOException e) {
			System.err.println(e.toString());
		}catch (Exception e1) {
			System.err.println(e1.toString());
		}
	}

	class   rcThread extends Thread {
		
		Socket client=null;
		DataInputStream is = null;
		

		rcThread(Socket client) throws Exception{
			this.client = client;
			is = new DataInputStream(client.getInputStream());
		}
		@Override
		public void run(){
			String recStr="";
			stdin = new DataInputStream(System.in);
			try{
				while(true){
					recStr = in.readUTF();
					System.out.println(recStr);
				}
			}catch (Exception e) {
					System.err.println(e.toString());
				}
			}
		}
	
	
	class scThread extends Thread{
		Socket client = null;
		DataOutputStream os = null;
		DataInputStream stdin = null;

		scThread(Socket client) throws Exception{
			this.client = client;
			os = new DataOutputStream(client.getOutputStream());
		}
		@Override
		public void run(){
			String scStr = "";
			try{
				while(true){
					scStr = stdin.readLine();
					os.writeUTF(client.getInetAddress().toString()+":"
						+client.getLocalPort()+":"+scStr);
					os.flush();
				}
			}catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	public static void main(String[] args) {
		clientSocketDuo csd = new clientSocketDuo("localhost",8000);
		Thread tcsd = new Thread(csd);
		try{
			tcsd.start();
			tcsd.join();
		}catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}