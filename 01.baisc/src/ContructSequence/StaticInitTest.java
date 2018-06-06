package ContructSequence;

public  class StaticInitTest{
	/*  每次运行该程序的时候，都会对 StaticInitTest类执行初始化：*
	*		先为所有的类变量分配内存空间，
			再按源代码中的排列顺序执行静态初始化块中所有指定的初始值
			和定义变量时 指定的初始值
	*/


	//定义static 类变量，定义时指定初始值
	static int count = 2;
	// 通过静态初始化块 为name 类变量 指定初始值
	static{
		System.out.println("StaticInitTest 的静态初始化块");
		name = "java类变量初始化时机";
	}
	//定义那么累变量的时候 指定初始值
	static String  name = "刘骏";
	public static void main(String[] argv){
		//访问类的这两个变量
		 System.out.println("count："+StaticInitTest.count);
		 System.out.println("name:"+StaticInitTest.name);
	}
}