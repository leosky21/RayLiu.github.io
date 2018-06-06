package TextOperater;

public class textopreator {
	int a = 6,b = 5;
	byte c=23,d=24;
	boolean e=true,f=false;
	private void calcuteOpreater(){//算术运算符应用
		int diviteA = a/b;
		//根据基本类型自动转换类型，除数被除数、返回值结果都是整数
		float diviteB = a/(b*2.0f);//对于声明为float类型的数字数字后加f
		double diviteC = a/(b*2.0d);//double类型的声明后面加d
		System.out.println(diviteA+";"+diviteB+";"+diviteC);
	}
	private void compareOperater(){//比较运算符
		System.out.println("a==b:"+(a==b));
		System.out.println("a>b"+(a>b));
		
	}
	public void valuteOPerater(){//复制运算符应用
		System.out.println("a=a+1=="+(a+=1)
				+";"+	"a=a-1=="+(a-=1)
				+";"+	"a=a/2=="+(a/=2)
				+"同理^、*、&、|"
				);
	}
	public void bitOperater(){
		int bitA = a&b;//按位与、两个运算符数都为1 的时候，结果为1 。否则为0
		int bitB = a|b;//按位或、同为0，结果为-0，否则为1；
		int bitC = a^b;//异或、运算数相同为0，不同1；
		int bitD = ~a;//按位非、0变1，1变0
		int a = 6;//重新赋值
		int bitE = a>>1;//右移，左边空出位以0填充----二进制
		int bitF = a>>>1;//右移，左边空出位以0填充----二进制
		int bitG = a<<1;//左移,右边空出位以0填充----二进制
		System.out.println("bitOperater:"+bitA
				+";"+bitB+";"+bitC+";"+bitD+";"+bitE+";"+bitF+";"+bitG
				);
	}
	public void booleanoperater(){
		boolean booA = e&&f;//与一错全错
		boolean booB = e||f;//或，一对全对
		boolean booC = e&&(a==b);
		System.out.println("booleanoperater:"+
					booA+";"+booB+";"+booC
				);
		
		if(e&&(a=b)==10){//&& 是短路与 ；当操作数从左到右的表达式进行从左到右的运算时---若遇到所有操作数的值是false，则结束运算
			System.out.println("a="+a);
		}
		if(f||(a=b)==10){// || 所有预算数为false结果false，否则为true
			System.out.println("a="+a);
		}
		if(f||(b==a-1))
			System.out.println("a="+a);
		if(e||((b--)==++b)){
			System.out.println("b="+b);
		//对操作数值进行反运算。操作数是true，取反为false
		if(!f)
			System.out.println("false取反后="+!f);
		else
			System.out.println("true取反后="+!f);
		//  |和||一样，对操作数进行操作，但他不短路
		if(e|(a=b)==10){
			System.out.println("a="+a);
		}
		if(f&(a=b)==10)
			System.out.println("a="+a);
		//运算符^ 对操作数取 异 操作，相同为false ，不同为true
		if(e^(b==a-1))
			System.out.println("e与b==a-1不同");
		else
			System.out.println("e与b==a-1同");
		}
		
	}
	
}
