class Base{
    public Base(){
        System.out.print("父类1  ");
        test();
        System.out.print("父类2  ");
    }
    public void test(){
        System.out.print("父类3  ");
    }
}

class Child extends Base{
    private int aaa = 123;
    public Child(){}

    public void test(){
        System.out.println(aaa);
    }
}
public class Construstortest{
 
    public static void main(String[] args){
        new Child().test();
    }
}