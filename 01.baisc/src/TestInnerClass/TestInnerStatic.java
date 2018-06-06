package TestInnerClass;


class Outer{
	 static class Inner{
		 
	 }
}

class A {
	private int x;
	void m(){
		new B();
	}
	static void sm(){
		//new B(); // error！！！
	}
	
	class B {
		B(){ x= 5; }
	}
}

public class TestInnerStatic {
	public static void main(String[] args){
		
		A.B a_b = new A().new B();//OK
		A a = new A();
		A.B ab = a.new B();
		
		/** static 类名前面需要加上前缀 */
		Outer.Inner oi = new Outer.Inner();
		//Outer.Inner oi2 = Outer.new Inner(); //!!!!error
		// Outer.Inner oi3 = new Outer().new Inner;  //!!!error
	}
}

