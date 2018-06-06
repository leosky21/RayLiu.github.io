package cons.varis;
//内部类
	class sub{
		String subName="Hello World!";//声明常量并赋值
		final double pi=3.14;//声明常量并赋值
	}


public class TestConstant {//操作常量变量的类
	int number = 0;
	public static void run(Object j) {//传入对象参数的方法
		System.out.println(j+"是Object");	
	}
	public void run(sub sub){//传入对象参数的方法
		System.out.println(sub+"是sub类");
	}
	public void showObject(){//显示实例化的信息
		sub sub=new sub();//实例化对象
		System.out.println(sub.subName="sub.subName");//获得对象的属性
		System.out.println("sub.fpi="+sub.pi);	
	}
	public	static void main(String[] arg){//程序主入口
		TestConstant contant = new TestConstant();
		contant.number = 5;//常量赋值
		System.out.println("t.i"+contant.number);
		contant.showObject();//调用方法
		contant.run(null);
	}
}
