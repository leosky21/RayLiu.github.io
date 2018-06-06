package threadText;

class Sum{
	//声明资源不可以被外部使用
	private int ad = 0;
//	 int ad = 0;
	//声明一个时刻该方法只能被一个使用
	synchronized  public  int add() {
//	 public  int add() {
		return ++ad;
	}
}

class ThreadDemo implements Runnable{
	private Sum ad;
	private int sum=0;
	private String name="";
	
	
	public ThreadDemo(Sum ad, String name) {
		super();
		this.ad = ad;
		this.name = name;
	}


	@Override
	public void run() {
		try {
			for(int i =0;i<10;i++) {
				sum += ad.add();
				Thread.sleep(100);
				
			}
			Thread.sleep(1000);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("已经累加了"+sum);
	}
	
	public int getSum() {
		return sum;
	}
}


public class SumDemo {

	public static void main(String[] args) throws Exception{
		Sum sd = new Sum();
		ThreadDemo st1= new ThreadDemo(sd, "aaaaaa");
		ThreadDemo st2 = new ThreadDemo(sd, "bbbbbbbb");
		ThreadDemo st3 = new ThreadDemo(sd, "cccccc");
		Thread  tst1= new Thread(st1);
		Thread  tst2= new Thread(st2);
		Thread  tst3= new Thread(st3);
		tst1.start();
		tst2.start();
		tst3.start();
		tst1.join();tst2.join();tst3.join();
		System.out.println("总和："+(st1.getSum()+st2.getSum()+st3.getSum()));
	}

}






