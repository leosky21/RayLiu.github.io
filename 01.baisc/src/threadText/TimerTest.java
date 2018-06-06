package threadText;

import java.util.*;
/**
 * Timer 每隔一定时间 做一件事----重复做
 * @author liujun
 *
 */
class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer("display");
		TimerTask task = new MyTask1();
		timer.schedule( task, 1000, 1000 );
	}
}
class MyTask1 extends TimerTask{
	int n = 0;
	public void run(){
		n++;
		System.out.print( new Date() );
		System.out.println( "---" + n );
	}
}
