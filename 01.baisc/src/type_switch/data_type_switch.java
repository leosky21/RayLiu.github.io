package type_switch;
/*
 * int(4),short(2),long(8),byte(1),flaot(4),double(8),char(2)
 *   本例子主两种方法： 自动提升、强制转换 
 *   1、自动提升条件：两种类型兼容---或者目标类型的范围比原类型范围大
 *   2、在数据前放一对包括新的类型名的括号。eg:  int i = (int) 55L;
 */
public class data_type_switch {//修饰基本数据类型转换的类
	public void typeAutoChange(){//基本类型的自动提升---result类型与运算结果兼容，否则报错
		byte b = 44;
		char c = 'b';
		int i = 40000;
		short s = 1024;
		long l = 123456l;
		float f = 35.67f;
		double d = 1.234d;
		
		double result = (f*b)+(l*f)+(i/c)-(d*s);
		System.out.println("结果："+result);
	}
	
	private void autoChanged(){//数据类型的自动转换---目标类型比原类型范围大
		char c = 'a';
		byte b = 44;
		short s0 = b;
		int i0 = s0;
		int i1 = c;
		long l = i0;
		float f = l;
		double d = f;
		float f1 = 1.7f;
		double dou = f1;
		System.out.println("fl="+f1+";dou="+dou);
		//一数从一个类型转换其他类型再转换回来，值不变
		f1 = (float)dou;
		System.out.println("fl="+f1+";dou="+dou);
	}
	private void foreChanged(){
		double d = 123.456d;
		float f = (float)d;
		long l = (long)d;
		System.out.println("d="+d+";f="+f+";l="+l);
		
		d = 567.89d;
		//下面作为转换首先进行阶段操作，将d值变为567，因为567比byte的范围还要大；
		//于是进行取模操作。 值为567对256 取模 值为 55
		byte b=(byte)d;
		System.out.println("d="+d+";b="+b);
		}

	public static void main(String[] args){//主程序入口
		data_type_switch dts = new data_type_switch();//实例化对象
		dts.typeAutoChange();//调用基本类型提升方法
		dts.autoChanged();//调用基本类型自动转换
		dts.foreChanged();//强制转换
	}	
}
