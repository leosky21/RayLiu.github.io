package TestInnerClass;


class Outter{
	private int size = 5;
	public Object makeTheInner(int localVar){
		final int finalLocalVar = 99;
		/**方法中 的 局部类*/
		class Inner{
			public String toString(){
				return ("InnerSize:" +size +
						//"localVar :"+ localVar + //Error!!
						"finalLocalVar : "+ finalLocalVar
						);
			}
		}
		return new Inner();
	}
}

public class TestInnerMethod {

}
