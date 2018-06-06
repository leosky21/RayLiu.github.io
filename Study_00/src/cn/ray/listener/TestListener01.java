package cn.ray.listener;

public class TestListener01 {
	public static void main(String[] args) {
		Person p = new Person();
		boy b = new boy();
		
		b.registerListener(new PersonListener() {
			
			@Override
			public void dorun(Even even) {
				System.out.println(even.getPerson()+"一旦本监听器监听到p(事件源)触发一个事件,事件对象就要让这个对象执行监听器中方法,然后再执行自己处理方法");
			}
			
			@Override
			public void doeat(Even even) {
				// TODO Auto-generated method stub
				
			}
		});
		b.run();
	}
}
class boy extends Person{
	
	@Override
	public void run() {
		System.out.println("--------执行boy的方法");
		super.run();
	}
}


// 创建事件源
class Person {
	// 1.1首先定义一个私有的、空的监听器对象，用来接收传递进来的事件监听器(相当于一个全局变量)
	private PersonListener listener;

	// 1.2将传递进来的事件监听器付给1.1中的listener
	public void registerListener(PersonListener personListener) {
		this.listener = personListener;
	}

	// 1.3判断listener是否为null，如果不为空，则执行监听器中的方法,否则照常调用
	public void run() {
		if (listener != null) {
			Even even = new Even(this);
			System.out.println("---------监听的方法先执行");
			this.listener.dorun(even);
			
		}
		System.out.println("--------现在我要执行自己的方法了");
	}

	// 1.4和1.3一个道理
	public void eat() {
		if (listener != null) {
			Even even = new Even(this);
			this.listener.doeat(even);
		}
		System.out.println("人具有吃的方法");
	}
}


//事件监听器
//两个动作可以被监听,再执行自己方法之前可以让你监听器传进来的动作先执行
interface PersonListener{
	public void dorun(Even even);
    public void doeat(Even even);
}

class Even{
	private Person person;
    
    public Even(Person person) {
        super();
        this.person = person;
    }

    public Even() {
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

