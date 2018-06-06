package threadText;


public class ThreadJoin {
	public static void main(String args[]) {
		Runnera r = new Runnera();
		Thread thread = new Thread( r );
		thread.start();
		try{
			thread.join();
		}catch(InterruptedException e){}
		for( int i=0; i<10; i++ ){
			System.out.println("\t" + i );
			try{
				Thread.sleep(100);
			}catch( InterruptedException e ){}
		}
	}
}

class Runnera implements Runnable {
	public void run() {
		for( int i=0; i<10; i++ ){
			System.out.println( i );
			try{
				Thread.sleep(100);
			}catch( InterruptedException e ){}
		}
	}
}
