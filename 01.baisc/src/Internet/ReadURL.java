package Internet;

import java.net.URL;
import java.util.Scanner;

public class ReadURL {
	static Scanner scanner;
	static URL url;
	static Thread readURL;
	static ReadUrlThread look = new ReadUrlThread();
	
	public static void main(String[] argv){
		System.out.println("print inet:");
		scanner = new Scanner(System.in);
		 String inet = scanner.nextLine();
		 try {
			 url = new URL(inet);
			 look.setURL(url);
			 readURL = new Thread(look);
			 readURL.start();
		 }catch(Exception e){
			 
		 }
	}
}
