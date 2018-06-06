// package aboutSuper;

class teacher{
	 teacher(){				/** 默认构造方法是 系统自动帮我们产生的  不带参数、不带方法体的 方法  */
		System.out.println("teacher");
	}
}
/**
 * 如果想用super继承父类构造的方法，但是没有放在第一行的话，那么在super之前的语句，肯定是为了满足自己想要完成某些行为的语句，
 * 但是又用了super继承父类的构造方法。那么以前所做的修改就都回到以前了，就是说又成了父类的构造方法了。
 * 在Eclipse中，如果不放在首位，报错：Constructor call must be the first statement in a constructor。
 * 不用显示调用，也可以。我估计是，如果构造函数有多个的时候，可以显示指明哪一个。
 * */

public class aboutSuper extends teacher {
		 aboutSuper(){
			super();
			System.out.println("child");
		}
	public static  void main(String[] args){
		aboutSuper a = new aboutSuper();
		 }
}


