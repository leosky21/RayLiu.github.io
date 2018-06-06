><!-- 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 http://blog.csdn.net/qq_31283465/article/details/53135833-->

# <a></a>IO 流

## <a></a>按照功能分为：

### <a></a>1\. 输入流

*   从外界获取数据，读取数据

### <a></a>2\. 输出流

*   向外界发送数据，写入数据

## <a></a>按照处理字节分为：

### <a></a>1\. 节点流

*   又称为低级流
*   可以从或向一个特定的地方（节点）读写数据

### <a></a>2\. 处理流

*   又称为高级流

    1.  不能独立存在，构造方法需要传入另一个流
    2.  需要处理另一个流 // 这个流可能是低级流，也可以是高级流。
    3.  简化读写操作

# <a></a>IS 和 OS 常用方法

* * *

**InputStream 和 OutputStream 是抽象类，他们是所有字节输入流和输出流的父类。**

## <a></a>OutputStream

### <a></a>1\. void write (int d)

*   写处一个字节，int 的低八位

### <a></a>2\. void write(byte [] d)

*   将给定的字节数组中所有字节全部写出

    * * *

## <a></a>低级流实现类：FOS&FIS

### <a></a>1.FileOutputStream

*   节点流，用于向文件中写出字节的流

```
    public static void main(String[] args) throws IOException {
        FileOutputStream fos=new FileOutputStream("fos.dat");
        fos.write(97);
        String str ="天安门上太阳升";
        byte [] buf=str.getBytes("UTF-8");
        fos.write(buf);
        fos.close();
    }
```

### <a></a>1.FileInputStream

*   节点流，用于从文件中读取字节的流

```
public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("fos.dat");
        int i=fis.read();
        System.out.println(i);
        byte [] buf=new byte[30];
        int len =fis.read(buf);
        String str=new String(buf,"UTF-8");
        System.out.println(len);
        System.out.println(str);
        fis.close();
    }
```

#### 使用文件字节输出流对已有文件进行写操作的注意事项

*   FileOutputStream 支持一个重载的构造方法
    **FileOutputStream(String str,boolean append)**
    第二个参数决定是否进行追加操作，
    若不追加，当前文件所有内容都会被**清除**，然后重写数据。
    要注意这一点和 RandomAccessFile 不同

### <a></a>使用 FIS 和 FOS 复制文件

```
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src.jpg");
        FileOutputStream fos=new FileOutputStream("copy.jpg");
        byte [] buf=new byte[10240];
        int len=-1;
        while((len=fis.read(buf))!=-1){
            fos.write(buf,0,len);
        }
        System.out.println("复制完毕");
        fis.close();
        fos.close();
    }
```

## <a></a>简单高级流实现类：BOS&BIS

*   **BufferedOutputStream 和 BufferedInputStream 缓冲流是高级流**
*   使用缓冲流可以加快读写效率
*   **关闭流时，仅关闭最外层高级流即可**

```
    public static void main(String[] args) throws IOException {
        FileOutputStream fos =new FileOutputStream("src.jpg");
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        FileInputStream fis=new FileInputStream("copy.jpg");
        BufferedInputStream bis =new BufferedInputStream(fis);
        int d=-1;
        while((d=bis.read())!=-1){
            bos.write(d);
        }
        bos.close();
        bis.close();
    }
```

### <a></a>flush()

*   强制将当前缓冲流的缓冲区的数据全部写出，无论缓冲区是否装满
*   缓冲流的 close()；方法默认调用一次 flush()，再关闭缓冲流处理的流。
*   虽然关闭高级流会自动 flush(); 但在注意时效性时候也需要自己 flush();

## <a></a>ObjectOutputStream

### <a></a>写字符串到硬盘中要经历两件事

2.  将字符串转换成一组字节
3.  将以组字节写入硬盘长久保留

> 1.  将一个特定的数据结构转换成一组字节的过程，称之为序列化
> 2.  将数据写入硬盘做长久保存的过程，称为持久化
>     **序列化与反序列化一般用于：**
>     
>     1.  传输
>     2.  保存

### <a></a>void wreiteObject(Object o)

1.  将给定的对象转换为一组字节后写出
2.  想要被拆的对象的类需要实现接口 Serializable，实现该接口不需要写任何方法

## <a></a>ObjectInputStream

*   高级流，可以将以组字节变为对应的对象
*   用于对象的反序列化

### <a></a>readObject()

*   返回值类型为 Object
*   可以强制转换为需要的类型

### <a></a>transient 关键字

*   java 语言的关键字，变量修饰符，如果用 transient 声明一个实例变量，当对象存储时，它的值不需要维持。换句话来说就是，用 transient 关键字标记的成员变量不参与序列化过程。
*   反序列化回来是该变量类型的默认值

### <a></a>long 型常量 serialVersionUID

*   static final long
*   名字不可改
*   代表版本号默认值 1L
*   如果版本号相同，在删除成员变量或增加成员变量后，反序列化仍能进行

    *   删除成员变量则直接不显示，增加的成员变量，反序列化付给该类型的初始值
*   若版本号不同，就不能反序列化。
*   **总结**
    当累的属性增加或修改了，若版本号不变那么反序列化时会尽可能兼容现有版本
    若版本号变化那么反序列化会抛出异常。

<link rel="stylesheet" href="http://csdnimg.cn/release/phoenix/production/markdown_views-10f5517761.css">

