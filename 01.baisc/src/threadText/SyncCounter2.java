package threadText;

class SyncCounter2
{
	public static void main(String[] args){ 
		Num1 num = new Num1();
		Thread counter1 = new Counter1(num);
		Thread counter2 = new Counter1(num);
		for( int i=0; i<10; i++ ){
			num.testEquals();
			try{							           
				Thread.sleep(100);
			}catch(InterruptedException e){
			}
		}
	}
}

class Num1
{
	private int x=0;
	private int y=0;
	synchronized void increase(){ 
		x++; 
		y++; 
	}
	synchronized boolean testEquals(){
		boolean ok = (x==y);
		System.out.println( x + "," + y +" :" + ok);
		return ok;
	}
}

class Counter1 extends Thread
{
	private Num1 num;
	Counter1( Num1 num ){
		this.num = num;
		this.setDaemon(true);
		this.setPriority(MIN_PRIORITY);
		this.start();
	}
	public void run(){
		while(true){
			num.increase();
		}
	}
}
