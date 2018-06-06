package threadText;

/**
* һ���򵥵�������
* @author iStar
* ����Ķ���flag=1ʱ��T1����������O1,˯��500���룬Ȼ������O2��
* ��T1��˯�ߵ�ʱ����һ��flag=0�Ķ���T2���߳�������������O2,˯��500���룬�ȴ�T1�ͷ�O1��
* T1˯�߽�������Ҫ����O2���ܼ���ִ�У�����ʱO2�ѱ�T2������
* T2˯�߽�������Ҫ����O1���ܼ���ִ�У�����ʱO1�ѱ�T1������
* T1��T2�໥�ȴ�������Ҫ�Է���������Դ���ܼ���ִ�У��Ӷ�������
*/
class DeadLock implements Runnable {
    public int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("enter..." + flag);
        if(flag == 1) {
            synchronized(o1) {
				mySleep(500);
                synchronized(o2) {
                    System.out.println("doing...1");
                }
            }
        }
        if(flag == 2) {
            synchronized(o2) {
				mySleep(500);
                synchronized(o1) {
                    System.out.println("doing...2");
                }
            }
        }
    }

	public static void mySleep(long n){
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        DeadLock td1 = new DeadLock();
        DeadLock td2 = new DeadLock();
        td1.flag = 1;
        td2.flag = 2;
        new Thread(td1).start();
        new Thread(td2).start();
		mySleep(2000);
		System.out.println("main end");
    }
}
