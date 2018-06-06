> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 http://blog.csdn.net/qq_31283465/article/details/53039213

# <a></a>RandomAccessFile

*   Java 提供了一个可以对文件随机访问的操作，包括读和写。该类是基于指针的操作。RandomAccessFile 总是在当前指针的位置读写字节，每次读写后，指针自动后移。

## <a></a>1.void write(int n)

```
public static void main(String args[]){
    //对项目根目录下一个名为demo.dat的文件内容进行读写
   RandomAccessFile raf =new RandomAccessFile("demo.dat","rw");
   File file=new File();//地址栏也可以传入file对象
    //写入给定int值的低八位，一个int型4个字节，32位，只存最后八位的01值
   int num =1;
   raf.write(num);
    //使用完IO后，要记得关闭
    raf.close();
}
```

## <a></a>2\. int read()

*   从当前文件读取一个字节存入 int 值中，同样是低八位，范围在 0-255 之间
*   若返回值为 - 1，则读到文件末尾

```
public static void main(String args[]){
    //只读模式即可
    RandomAccessFile raf=new RandomAcessFile("demo.dat","r");
    int i=raf.read();
}
```

### <a></a>使用 RandomAccessFile 类实现复制操作

1.  创建一个该类的对象，用于读取文件
2.  创建另一个对象用于写入文件
3.  循环从原文件读取每一个字节并写入目标文件中
4.  关闭两个 RandomAccessFile

```
public static void main(String [] args){
    long start =System.currentTimeMillis();//开始时间
     RandomAccessFile src=new RandomAccessFile("src.jpg","r");
    RandomAccessFile des=new RandomAccessFile("copy.jpg","wr");
    int d = -1;//此处赋初值什么值都可以
    while(d=src.read()!=-1){
        des.write(d);
    }
    System.out.println("拷贝完毕");
    src.close();
    des.close();
    long end =System.currentTimeMills();//结束时间
    System.out.println("耗时"+(end-start)+"ms");//计算复制时间
}
```

*   tips：在读写文件时，若想提高读写效率，就必须减少读写次数
    在此处的 read 和 write 方法一次只操作一个字节，所以读写速度慢。

## <a></a>3\. void write (byte [] d)

*   会根据当前指针所在位置处连续的写出给定数组中的所有字节。
*   与该方法类似的还有一个方法
    **void write(byte[] d,int offset,int len)**
    该方法会根据指针所在位置出连续写如给定数组中的部分字节，这个部分是从数组的 offset 开始连续 len 个字节

```
public class FileTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        File file=new File("test.txt");
        RandomAccessFile raf=new RandomAccessFile(file, "rw");
        String str="我爱北京天安门";
        byte[] buf=str.getBytes("gbk");
        raf.write(buf);
        raf.close();

    }

}

```

## <a></a>4\. int read(Byte [] buf)

*   一次尝试从文件中读取 buf 数组 length 个字节并从该数组的第一个位置处起存放实际读取到的字节。
*   返回值为实际读取到的字节书
*   此处需注意 String 的构造方法，可以传入一个 byte 数组，即可形成默认字符集转换的字符串，该构造方法还有一种形式 String(byte [] buf,String str)str 传入字符集类型。
*   若没读取到文件返回值也是 - 1

```
public class FileTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        File file=new File("test.txt");
        RandomAccessFile raf=new RandomAccessFile(file, "rw");

        byte [] buf=new byte[50];
        int len =raf.read(buf);
        System.out.println(len);
        System.out.println(Arrays.toString(buf));
        String str=new String(buf);
        System.out.println(str.trim());
        raf.close();
    }

}
```

### <a></a>案例：读写的加快

```
public class FileTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        RandomAccessFile src=new RandomAccessFile("src.jpg","r");
        RandomAccessFile des=new RandomAccessFile("copy.jpg","wr");
        byte [] buf=new byte[1024*10];//10k 1024byte=1k
        int len=-1;
        while((len=src.read(buf))!=-1){
            /*
             *使用该方法是考虑到，读取文件时可能数组不会每个元素都存放了内容。
             *根据len的值准确的写入正确的字符数。
             */
            des.write(buf, 0, len);       
        }
        src.close();
        des.close();
    }

}
```

## <a></a>5.writeInt(num)

*   将一个 int 值写入，连续写入四个字节

```
//内部实现,通过位运算，使int4个字节分别写入文件中。
raf.write(num>>>24);
raf.write(num>>>16);
raf.write(num>>>8);
raf.write(num);
```

## <a></a>6.long getFilePointer()

*   获取指针位置

## <a></a>7.void seek(long position)

*   可以将文件指针移动到这个位置

```
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        RandomAccessFile raf=new RandomAccessFile("raf.dat","rw");
        long p=raf.getFilePointer();
        System.out.println(p);
        raf.write(98);
        p=raf.getFilePointer();
        System.out.println(p);
        raf.writeInt(5);
        System.out.println(raf.getFilePointer());
        raf.seek(0);
        int n=raf.read();
        System.out.println(n);
        //System.out.println(raf.readInt());
        System.out.println(raf.read());
        System.out.println(raf.getFilePointer());
        System.out.println(raf.read());
        System.out.println(raf.read());
        System.out.println(raf.read());
    }  
```

## <a></a>8.int skipBytes(int n)

*   该方法会尝试跳过那个字节，指针往后移动 n 个
*   返回值为实际跳过的字节数
*   只能往后不能返回

```
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        RandomAccessFile raf=new RandomAccessFile("raf.dat","r");
        raf.skipBytes(1);
        System.out.println(raf.readInt());
    }
```

<link rel="stylesheet" href="http://csdnimg.cn/release/phoenix/production/markdown_views-10f5517761.css">