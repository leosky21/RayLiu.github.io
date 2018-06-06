package TestInnerClass;

class Parcel{//包裹
	private Contents c;
	private Destination d;
	
	class Contents {// 包裹的路程
		private int i ;
		Contents ( int i ){this .i = i;}
		int value() {return i;}
	}
	class Destination {// 包裹的目的地
		private String label ;
		Destination(String whereTo){label = whereTo;}
		String readlabel(){return label;}
	}
	
	void setProperty ( Contents c ,Destination d){
		this.c = c ;
		this.d = d ;
	}
	void ship(){
		System.out.println("move "+c.value() + "km to "+ d.readlabel());
	}
	public void testShip(){
		c = new Contents(22);
		d = new Destination("taizhou");
		ship();
	}
}

public class TestInnerClass {
		public static void main(String[] args){
			Parcel p = new Parcel();
			p.testShip();
			
			/* 内部类 使用方法*/
			Parcel.Contents c = p.new Contents(33);
			Parcel.Destination d = p.new Destination("beijing");
			p.setProperty(c, d);
			p.ship();
		}
}
