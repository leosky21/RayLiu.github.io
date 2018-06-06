package TestInnerClass;

/**
 * 定义在一个方法（包括了类的构造块和static构造块）内部的类，叫局部内部类。它不能有任何访问权限修饰符，因为它只能被包装它的方法使用，离开方法后就不可用了。

局部内部类可以和成员内部类一样，访问外部类的实例成员。
同时，它还能直接使用包含它的方法的局部final常量,final参数。javac会复制使用了的外部方法的局部final量保存在局部内部类中作为私有的备份。

因此,当这个外部方法执行完毕后，虽然方法中的局部变量的 lifetime结束了，
但是如果局部类的实例作为返回值,它会带着外部方法的局部final量离开这个局部作用域,也就是说,局部变量的生命延长到了和局部内部类的对象的生命周期一致。
并不会随着方法执行完立刻被清理掉。我们可以以此来形成闭包。

同样,局部内部类不是top-level类，不能有static成员，除非是static final 字段。
 * @author liujun
 *
 *
 *
 *局部内部类之所以能访问外部类的实例成员，
 *其原因和成员内部类是一样的：内部类中有保存了外部类对象的引用。
 *除此之外，局部内部类还能访问包装方法的final字段，
 *javac会将内部类使用了的final 局部常量拷贝到局部内部类中保存，
 *并在局部内部类对象实例化时，初始化这些final常量。因此，局部内部类使用的final常量是自己的拷贝分。
 */
public class LocalInnerClass
{
    public static void main(String[] args) 
    {
        
        MsgGenerator g5 = fac(5);
        System.out.println(g5.generatorMsg());
        
        MsgGenerator g2 = fac(6);
        System.out.println(g2.generatorMsg());
        
    }
   
    public static MsgGenerator fac(final int times)
    {
        class Generator implements MsgGenerator
        {
            @Override
            public StringBuffer generatorMsg()
            {
                
                StringBuffer s=  new StringBuffer() ;
                
                for(int i=0;i<times;++i)
                {
                    s.append("hello  "+i+"-----");
                }
                
                return s;
            }
            
            
        }  //end of class
		     
        return new Generator();    //向外发出闭包
    }
   
}


interface MsgGenerator
{
    StringBuffer generatorMsg();
}

