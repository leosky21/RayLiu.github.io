> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 http://blog.csdn.net/qq_31283465/article/details/54838246

> 蓝桥杯的基础练习，别处的答案有的写得很冗余，没有充分利用 java 简洁的方法，在这里有我自己整合写的代码，仅提供 java 实现，如有问题，欢迎探讨。

[蓝桥杯 基础练习 02 Java 实现](http://blog.csdn.net/qq_31283465/article/details/54882415)
已解决进制转换问题

1\. 闰年判断

```
/*  
基础练习 闰年判断  

问题描述  
给定一个年份，判断这一年是不是闰年。  

当以下情况之一满足时，这一年是闰年：  

1\. 年份是4的倍数而不是100的倍数；  

2\. 年份是400的倍数。  

其他的年份都不是闰年。  

输入格式  
输入包含一个整数y，表示当前的年份。  
输出格式  
输出一行，如果给定的年份是闰年，则输出yes，否则输出no。  
说明：当试题指定你输出一个字符串作为结果（比如本题的yes或者no，你需要严格按照试题中给定的大小写，写错大小写将不得分。  

样例输入  
2013  
样例输出  
no  
样例输入  
2016  
样例输出  
yes  
数据规模与约定  
1990 <= y <= 2050。  
*/  

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int y;
        Scanner s=new Scanner(System.in);
        y=s.nextInt();
        if(y>=1990  &&y <= 2050){
            if(y%4==0&&y%100!=0||y%400==0){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        }else{
            System.out.println("no");
        }

    }

}
```

2.01 字串
注：
1.System.out 流有提供 printf 方法，与 C 语言一样可以使用 %d 来控制输出格式
2.Integer.toBinaryString() 方法是将传参转换成二进制，返回字符串
3.Integer.parseInt() 方法将传入字符串封装成 Interger
此处只是打印 5 个字符，实际上我采用的方法是将**二进制数**转化成其**字面值表示的十进制数**存入 int 变量，通过 **printf** 格式输出补足前面的 0。

```
/*  
基础练习 01字串  

问题描述  
对于长度为5位的一个01串，每一位都可能是0或1，一共有32种可能。它们的前几个是：  

00000  

00001  

00010  

00011  

00100  

请按从小到大的顺序输出这32种01串。  

输入格式  
本试题没有输入。  
输出格式  
输出32行，按从小到大的顺序每行一个长度为5的01串。  
样例输出  
00000  
00001  
00010  
00011  
<以下部分省略>  
*/  
public class Main {
    public static void main(String[] args) {
        for(int i=0;i<32;i++){
            int c=Integer.parseInt(Integer.toBinaryString(i));
            System.out.printf("%05d\n",c);
        }
    }
}
```

3\. 字母图形

```
/*  
基础练习 字母图形  

问题描述  
利用字母可以组成一些美丽的图形，下面给出了一个例子：  

ABCDEFG  

BABCDEF  

CBABCDE  

DCBABCD  

EDCBABC  

这是一个5行7列的图形，请找出这个图形的规律，并输出一个n行m列的图形。  

输入格式  
输入一行，包含两个整数n和m，分别表示你要输出的图形的行数的列数。  
输出格式  
输出n行，每个m个字符，为你的图形。  
样例输入  
5 7  
样例输出  
ABCDEFG  
BABCDEF  
CBABCDE  
DCBABCD  
EDCBABC  
数据规模与约定  
1 <= n, m <= 26。  
*/ 
import java.util.Scanner;

public class Main {
    static char []a=new char[26];
    static int m;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        m=sc.nextInt();

        for(int i=0;i<26;i++){
            a[i]=(char)('A'+i);
        }
        for(int i=0;i<n;i++){
            print(i);
        }

    }
    public static void print(int i){
        int t;
        for(t=1;i>=0&t<=m;i--,t++){
            System.out.print(a[i]);
        }
        for(int j=1;j<m&j<=m-t+1;j++){
            System.out.print(a[j]);
        }
        System.out.println();
    }
} 
```

4\. 数列特征

```
/*  
基础练习 数列特征  

问题描述  
给出n个数，找出这n个数的最大值，最小值，和。  

输入格式  
第一行为整数n，表示数的个数。  

第二行有n个数，为给定的n个数，每个数的绝对值都小于10000。  

输出格式  
输出三行，每行一个整数。第一行表示这些数中的最大值，第二行表示这些数中的最小值，第三行表示这些数的和。  
样例输入  
5  
1 3 -2 4 5  
样例输出  
5  
-2  
11  
数据规模与约定  
1 <= n <= 10000。  
*/  
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int [] a=new int[n];
        a[0]=sc.nextInt();
        int max=a[0];
        int min=a[0];
        int sum=a[0];
        for(int i=1;i<n;i++){
            a[i]=sc.nextInt();
            sum+=a[i];
            if(max<a[i]){
                max=a[i];
            }
            if(min>a[i]){
                min=a[i];
            }
        }
        sc.close();
        System.out.println(max);
        System.out.println(min);
        System.out.println(sum);
    }
}
```

5\. 查找整数

```
/*  
基础练习 查找整数  

问题描述  
给出一个包含n个整数的数列，问整数a在数列中的第一次出现是第几个。  

输入格式  
第一行包含一个整数n。  

第二行包含n个非负整数，为给定的数列，数列中的每个数都不大于10000。  

第三行包含一个整数a，为待查找的数。  

输出格式  
如果a在数列中出现了，输出它第一次出现的位置(位置从1开始编号)，否则输出-1。  
样例输入  
6  
1 9 4 8 3 9  
9  
样例输出  
2  
数据规模与约定  
1 <= n <= 1000。  
*/  
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();

        int [] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int find=sc.nextInt();
        int location=-1;
        sc.close();
        for(int i =0;i<n;i++){
            if(a[i]==find){
                location=i+1;
                break;
            }
        }
        System.out.println(location);
    }
}
```

6\. 杨辉三角形

```
/*  
基础练习 杨辉三角形  

问题描述  
杨辉三角形又称Pascal三角形，它的第i+1行是(a+b)i的展开式的系数。  

它的一个重要性质是：三角形中的每个数字等于它两肩上的数字相加。  

下面给出了杨辉三角形的前4行：  

   1  

  1 1  

 1 2 1  

1 3 3 1  

给出n，输出它的前n行。  

输入格式  
输入包含一个数n。  

输出格式  
输出杨辉三角形的前n行。每一行从这一行的第一个数开始依次输出，中间使用一个空格分隔。请不要在前面输出多余的空格。  
样例输入  
4  
样例输出  
1  
1 1  
1 2 1  
1 3 3 1  
数据规模与约定  
1 <= n <= 34。  
*/  
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int [][] a=new int[n][n];
        for(int i=0;i<n;i++){
            a[i][i]=1;
            a[i][0]=1;
        }
        for(int i=2;i<n;i++){
            for(int j=1;j<i;j++){
                a[i][j]=a[i-1][j]+a[i-1][j-1];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }

    }
}
```

7.

```
/*  
基础练习 特殊的数字  

问题描述  
　　153是一个非常特殊的数，它等于它的每位数字的立方和，即153=1*1*1+5*5*5+3*3*3。编程求所有满足这种条件的三位十进制数。  
输出格式  
　　按从小到大的顺序输出满足条件的三位十进制数，每个数占一行。  
*/  
public class Main {
    public static void main(String[] args) {
        int a,b,c;
        for(a=1;a<=9;a++){
            for(b=0;b<=9;b++){
                for(c=0;c<=9;c++){
                    if(a*100+b*10+c==a*a*a+b*b*b+c*c*c){
                        System.out.println(a+""+b+""+c);
                    }
                }
            }
        }
    }
}
```

8.

```
/*  
基础练习 回文数  

问题描述  
　　1221是一个非常特殊的数，它从左边读和从右边读是一样的，编程求所有这样的四位十进制数。  
输出格式  
　　按从小到大的顺序输出满足条件的四位十进制数。  
*/  
public class Main {
    public static void main(String[] args) {
        int a,b;
        for(a=1;a<=9;a++){
            for(b=0;b<=9;b++){
                System.out.println(a+""+b+""+b+""+a);
            }
        }

    }
}
```

9.

```
/*  
基础练习 特殊回文数  

问题描述  
　　123321是一个非常特殊的数，它从左边读和从右边读是一样的。  
　　输入一个正整数n， 编程求所有这样的五位和六位十进制数，满足各位数字之和等于n 。  
输入格式  
　　输入一行，包含一个正整数n。  
输出格式  
　　按从小到大的顺序输出满足条件的整数，每个整数占一行。  
样例输入  
52  
样例输出  
899998  
989989  
998899  
数据规模和约定  
　　1<=n<=54。  
*/  
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if(n>54){
            System.exit(0);
        }
        sc.close();
        int a,b,c;
        for(a=1;a<=9;a++){
            for(b=0;b<=9;b++){
                for(c=0;c<=9;c++){
                    if(2*(a+b)+c==n){
                        System.out.println(a+""+b+""+c+""+b+""+a);
                    }
                }
            }
        }
        for(a=1;a<=9;a++){
            for(b=0;b<=9;b++){
                for(c=0;c<=9;c++){
                    if(2*(a+b+c)==n){
                        System.out.println(a+""+b+""+c+""+c+""+b+""+a);
                    }
                }
            }
        }
    }
}
```

*   十进制转十六进制
    tip：蓝桥系统的匹配文件是大写字母，而调用自动装箱的方法输出的 16 进制是小写字母，toUpperCase 方法是转换成大写字母的。

```
/*  
基础练习 十进制转十六进制  

问题描述  
　　十六进制数是在程序设计时经常要使用到的一种整数的表示方式。它有0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F共16个符号，分别表示十进制数的0至15。十六进制的计数方法是满16进1，所以十进制数16在十六进制中是10，而十进制的17在十六进制中是11，以此类推，十进制的30在十六进制中是1E。  
　　给出一个非负整数，将它表示成十六进制的形式。  
输入格式  
　　输入包含一个非负整数a，表示要转换的数。0<=a<=2147483647  
输出格式  
　　输出这个整数的16进制表示  
样例输入  
30  
样例输出  
1E  
*/  
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(Integer.toHexString(n).toUpperCase());

    }

}
```

*   十六进制转十进制
    tip：其中系统的一个检测文件转化成 int 会溢出，所以使用 Long

```
/*  
基础练习 十六进制转十进制  

问题描述  
　　从键盘输入一个不超过8位的正的十六进制数字符串，将它转换为正的十进制数后输出。  
　　注：十六进制数中的10~15分别用大写的英文字母A、B、C、D、E、F表示。  
样例输入  
FFFF  
样例输出  
65535  
*/  
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(Long.parseLong(s,16));

    }

}
```

*   十六进制转八进制
    tips：关于此题使用包装类方法错误的原因：[关于 Integer.parseInt() 方法的问题](http://blog.csdn.net/qq_31283465/article/details/54863828)
    此代码来源为百度

```
/*  
基础练习 十六进制转八进制  

问题描述  
　　给定n个十六进制正整数，输出它们对应的八进制数。  
输入格式  
　　输入的第一行为一个正整数n （1<=n<=10）。  
　　接下来n行，每行一个由0~9、大写字母A~F组成的字符串，表示要转换的十六进制正整数，每个十六进制数长度不超过100000。  
输出格式  
　　输出n行，每行为输入对应的八进制正整数。  
注意  
　　输入的十六进制数不会有前导0，比如012A。  
　　输出的八进制数也不能有前导0。  
样例输入  
2  
39  
123ABC  
样例输出  
71  
4435274  
提示  
　　先将十六进制数转换成某进制数，再由某进制数转换成八进制。  
*/  
import java.util.Scanner;  
public class Main {  

    public static void main(String[] args)  
    {  
             Scanner sc=new Scanner(System.in);  
             int n=sc.nextInt();  
             String[] st=new String[n];  
             for(int i=0;i<n;i++)  
             {  
             st[i] =sc.next();  
             }  
             sc.close();  
             for(int i=0;i<n;i++)  
             {  
                       String str1=ttos(st[i]);  

                 int len_str1=str1.length();  
                 if(len_str1%3==1)str1="00"+str1;   
                 else if(len_str1%3==2)str1="0"+str1;  
                 ttoe(str1);  
                 System.out.println();  
             }  

    }  

         public static String ttos(String  str)  
    {  
             int len_str=str.length();  
             StringBuilder str2=new StringBuilder();  
             for(int i=0;i<len_str;++i)   
    {   
             switch(str.charAt(i))   
             {   
             case '0':str2.append("0000");break;   
             case '1':str2.append("0001");break;   
             case '2':str2.append("0010");break;   
             case '3':str2.append("0011");break;   
             case '4':str2.append("0100");break;   
             case '5':str2.append("0101");break;   
             case '6':str2.append("0110");break;   
             case '7':str2.append("0111");break;   
             case '8':str2.append("1000");break;   
             case '9':str2.append("1001");break;   
             case 'A':str2.append("1010");break;   
             case 'B':str2.append("1011");break;   
             case 'C':str2.append("1100");break;   
             case 'D':str2.append("1101");break;   
             case 'E':str2.append("1110");break;   
             case 'F':str2.append("1111");break;   
             default:break;   
             }   
    } return str2.toString();  
    }  
  public static void ttoe(String str2)  
  {  
           int len=str2.length();  
           int a;  
           a=(str2.charAt(0)-'0')*4+(str2.charAt(1)-'0')*2+(str2.charAt(2)-'0');  
           if(a!=0)System.out.print(a);  
           for(int i=3;i<=len-2;i+=3)   
           {   
           a=(str2.charAt(i)-'0')*4+(str2.charAt(i+1)-'0')*2+(str2.charAt(i+2)-'0');   
           System.out.print(a);  
           }   

  }  
} 
```

*   数列排序

```
/*  
基础练习 数列排序  

问题描述  
　　给定一个长度为n的数列，将这个数列按从小到大的顺序排列。1<=n<=200  
输入格式  
　　第一行为一个整数n。  
　　第二行包含n个整数，为待排序的数，每个整数的绝对值小于10000。  
输出格式  
　　输出一行，按从小到大的顺序输出排序后的数列。  
样例输入  
5  
8 3 6 4 9  
样例输出  
3 4 6 8 9  
*/ 
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int [] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        Arrays.sort(a);
        for(int b:a){
            System.out.print(b+" ");
        }
    }
}
```

<link rel="stylesheet" href="http://csdnimg.cn/release/phoenix/production/markdown_views-10f5517761.css">