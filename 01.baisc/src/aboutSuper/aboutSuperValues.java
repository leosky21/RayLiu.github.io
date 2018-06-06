package aboutSuper;


class country{
	String name = "China";
	// private String name = "China" ;//不能定义为私有变量，否则 在子类中不能玩耍了；
	String value(String  name){
		this.name = name;
		return name;
	}
}
public class aboutSuperValues extends country{
	String name = "LianYunGang";
	String value(String name){
		System.out.println("super.name :"+super.name);
		super.value("Fail");
		System.out.println("name :"+name);
		System.out.println("super.name:"+super.name);
		
		return name;
	}
	
	public static  void main(String[] args){
		aboutSuperValues a = new aboutSuperValues();
		a.value("Sucess");
	}
}
