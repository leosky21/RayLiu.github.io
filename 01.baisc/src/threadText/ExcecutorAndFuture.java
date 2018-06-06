package threadText;

import java.util.concurrent.*;
class ExcecutorAndFuture
{
	public static void main(String[] args) 
	{
		ExecutorService executor 
			= Executors.newCachedThreadPool();
		System.out.println("׼������");
		Future<Long> future = executor.submit(
			new Callable<Long>(){
				public Long call(){
					return fibonacci(20);
				}
		});
		//����дΪ
		//Future<Long> future = 
		//	executor.submit(()->fibonacci(20));

		System.out.println("���߳̿���ִ�б����");

		try{
			Thread.sleep(2000);
			System.out.println("�첽ȡ�ý��:");
			System.out.println(future.get());
			executor.shutdown();//����executor
		}catch( InterruptedException | ExecutionException ex){
			ex.printStackTrace();
		}
	}
	
	static long fibonacci( int n ){
		if(n==0||n==1)return 1;
		return fibonacci(n-1)+fibonacci(n-2);
	}
}