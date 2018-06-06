package threadText;


/**
 * lamada 。...，匿名实现 run（）方法的线程
 * @author liujun
 *
 */
public class TestThread4Anonymous {
	public static void main(String args[]) {

		new Thread(){
			public void run() {
				for(int i=0; i<10; i++)	
					System.out.println(i);
			}
		}.start();

		new Thread( ( ) -> { 
			for(int i=0; i<10; i++) 
				System.out.println(" "+ i); 
		} ).start();
	}
}

