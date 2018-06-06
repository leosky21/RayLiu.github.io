package ContructSequence;

class Cat{
	//定义 name、 age 两个变量
	String name ;
	int age;
	// 使用构造器 初始化两个变量
	public Cat(String name,int age){
		System.out.println("执行构造器");
		this.name = name;
		this.age = age;
	}
	{
		System.out.println("执行静态代码块");
		weight = 2.0;
	}
	// 定义时 指定初始值
	double weight = 2.3;
	public String toString(	){
		return "CatName="+name+"; age="+age+"; weight="+weight;
	}
}
public class InitTest{
	/* 每次创建java对象都会为实例变量分配内存空间、并对实力变量初始化 执行先后顺序： *
	* 1、 定义实例变量时指定初始值
	* 2、 非静态代码块中对实例变量指定初始值
	* 3、 构造器中 对实例变量指定初始值

		虽然先执行 weight = 2.3； 但是 
			初始化块中指定初始值，定义weight 指定初始值 ，都属于对该实例变量执行初始化操作
			他们的执行顺序与他们在源程序中的排列顺序相同。
			本程序中 初始化块语句 在 定义 语句之前，因此语句将 先执行初始化块中的初始化操作，
			执行后 weight 实例变量的 值为 2.0； 然后执行定义的 weight时指定的初始值。
			执行完成后 weight值变成2.3.  所以
	
		定义变量时指定初始值 和 初始化块中 指定初始值 的执行顺序，与 他们在源程序中的 
			排列顺序相同；

			double weight = 2.3;
			 等同于 ：  double weight ;创建java对象时，系统根据该语句为对象分配内存
			 			weight = 2.3;	这条语句将会被提取到 java类的构造器中执行；

----------------！所以，先赋值  Cat（。。，age的值），然后赋值 2； 最后赋值 2.3！------------
	* 4、定义实例变量指定初始值、初始化块中的实例变量为实例变量指定的初始值、构造器中为实例变量指定的
		初始值，三者的作用完全相似；都是用于对与实例指定初始值；
			经过编译器处理之后，他们对应的赋值语句都会合并到构造器中，
			在合并过程中定义变量语句转换得到
			的赋值语句、初始化块里的 语句转换的到的赋值语句，总是位于构造器所有语句之前；
			合并之后，
			两种赋值语句的顺序 保持着她们在源代码中的顺序；
	*/
	public static void main(String[] argv){
		Cat cat = new Cat("kitty",2);
		System.out.println("cat:"+cat);
		System.out.println("cat,toString:"+cat.toString());

		Cat c2 = new Cat("Jerfield" ,3);
		System.out.println("c2:"+c2);
	}
}