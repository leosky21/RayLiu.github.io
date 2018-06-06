package ContructSequence;


class Person{
	String name = "未命名"; //step 2
	int age = -1;
	Person(String name , int age){
		super();// 系统会自动添加 step 1
		//step 3
		System.out.println("开始构造Person(),此时this.name = "+this.name+",this.age = "+this.age);
		this.name = name ;
		this.age = age;
		System.out.println("构造Person()已完成,此时this.name = "+this.name+",this.age = "+this.age);
	}
}
class Student extends Person{
	String school = "未定学校";//step 2
	Student(String name, int age , String school) {
		super(name, age);// step 1
		// step 3
		System.out.println("开始构造student(),此时this.name = "+this.name+",this.age = "+this.age+",this.shcool = "+this.school);
		this.school = school;
		System.out.println("student()构造完成,此时this.name = "+this.name+",this.age = "+this.age+",this.school = "+this.school);
		
	}
	
}

public class ConstructSequence {
	public static void main(String[] args){
		Student s = new Student("小明", 19, "家里蹲");
	}
}
