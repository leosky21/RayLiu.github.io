package TestInnerClass;


class Outtter{
	private int size = 5;
	public Object makeTheInner(int localVar){
		final int finalLocalVar = 99;
		/**方法中 的 匿名类*/
		
		return new  Object(){
			public String toString(){
				return ("InnerSize:" +size +
						//"localVar :"+ localVar + //Error!!
						"finalLocalVar : "+ finalLocalVar
						);
			}
		};
	}
}

public class TestInnerAnonymous {

}
