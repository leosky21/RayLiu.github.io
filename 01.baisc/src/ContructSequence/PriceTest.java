package ContructSequence;


/**
*	1、系统为Price的两个类变量分配内存空间   ----INSTANCE 值为null，initPrice = 0
*		INSTANCE 中new 的Price（2.8) 中，initPrice还没有被赋值，只是被初始化
*   2、按初始化代码排列顺序 对类变量执行 初始化
*/
class Price{
	// 类成员时Price实例
	final static Price INSTANCE = new Price(2.8);
	// 再定义一个类变量
	static double initPrice = 20;
	// 定义一该Price的CurrentPrice 的实例变量
	double currentPrice ;
	public Price(double discount){
		//根据静态变量计算实例变量
		currentPrice = initPrice - discount;
	}
}
public class PriceTest{
	public static void main(String[] args) {
		// 通过 Price 的INSTANCE访问 currentPrice的实例变量
		System.out.println("--------------Price.INSTANCE.currentPrice"+Price.INSTANCE.currentPrice);
		// 显式的创建Price实例
		Price p = new Price(2.8);
		// 通过显式的创建的Price 实例访问currentPrice实例变量
		System.out.println("--------------p.currentPrice"+p.currentPrice);	
	}
}