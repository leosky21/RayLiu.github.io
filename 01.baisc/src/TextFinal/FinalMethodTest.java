package TextFinal;

class Base{
	private final void info() {
		System.out.println("Base的info 方法");
	}
	protected  void info2(){
		System.out.println("base info 2");
	}
}

public class FinalMethodTest extends Base{
	/**
	 * 当final修饰某个方法时，限制 该方法不可以被重写
	 * @param args
	 */
	//@Override   ----------- 被该注释修饰的方法必须重写父类方法；每当希望重写一个父类的方法的时候总应该添加该注释。------------------
	public void info() {
		// 这个方法没有覆盖
		/**
		 * 虽然 FinalMethodTest 是从Base派生的，
		 * 但是 base中的info是 private ，无法被访问。且final无法被重写
		 * 所以这里的info 只是一个普通方法
		 */
		System.out.println("FinalMethodTest 的方法");
	}
	@Override
	public void info2() {
		super.info2();
		System.out.println("FinalMethodTest info2");
	}
	public static void main(String[] args) {
		FinalMethodTest fm = new FinalMethodTest();
		fm.info();
		fm.info2();
	}
}
