package threadText;

public class TestThread1 {	
	public static void main(String args[]){
		Thread t = new MyThread2(100);
		t.start();
	}
}

class MyThread2 extends Thread {
	private int n;;
	public MyThread2( int n ){
		super();
		this.n=n;
	}
	public void run() {
		for(int i=0;i<n;i++) {
			System.out.println ("------ " + i);
		}
	}
	
}