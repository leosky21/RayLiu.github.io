package CS;


import java.net.*;


public class Server{
	@SuppressWarnings("resource")
	public static  void main(String[] argv){
		ServerSocket ss= null;
		Socket sc=null;
		while(true){
			System.out.println("服务器等待连接。。。。");
			try{
				ss  =new ServerSocket(9688);
				}catch(Exception e){}
			try{
				sc = ss.accept();
				new CSServerThread(sc).start();
			}catch(Exception e){
				System.out.println("client error！");}
		}
	}
}