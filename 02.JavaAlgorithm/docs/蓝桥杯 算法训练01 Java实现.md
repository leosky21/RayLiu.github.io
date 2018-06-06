> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 http://blog.csdn.net/qq_31283465/article/details/55001208

*   区间 k 大数查询
    java 自带的 sort 超出了我的想象，代码是度来的。因为我自己写了一个快速排序，速度跟不上。我觉得用堆排可能会快一点。但是 java 自带的方法，太强大了。

```
/*  
算法训练 区间k大数查询  

问题描述  
给定一个序列，每次询问序列中第l个数到第r个数中第K大的数是哪个。  

输入格式  
第一行包含一个数n，表示序列长度。  

第二行包含n个正整数，表示给定的序列。  

第三个包含一个正整数m，表示询问个数。  

接下来m行，每行三个数l,r,K，表示询问序列从左往右第l个数到第r个数中，从大往小第K大的数是哪个。序列元素从1开始标号。  

输出格式  
总共输出m行，每行一个数，表示询问的答案。  
样例输入  
5  
1 2 3 4 5  
2  
1 5 2  
2 3 2  
样例输出  
4  
2  
数据规模与约定  
对于30%的数据，n,m<=100；  

对于100%的数据，n,m<=1000；  

保证k<=(r-l+1)，序列中的数<=10de6次方。  
*/  
import java.util.Arrays;  
import java.util.Scanner;  
public class Main {  
    public static void main(String[] args) {  
        Scanner input=new Scanner(System.in);  
        int l=input.nextInt();  
        int[] a=new int[l];  
        for (int i = 0; i < a.length; i++) {  
            a[i]=input.nextInt();  
        }  
        int n=input.nextInt();  
        int[][]tag=new int[n][3];  
        for (int i = 0; i < n; i++) {  
            tag[i][0]=input.nextInt();  
            tag[i][1]=input.nextInt();  
            tag[i][2]=input.nextInt();  
        }  
        for (int i = 0; i < n; i++) {  
            System.out.println(getMaxK(a, tag[i][0], tag[i][1], tag[i][2]));  
        }  
        input.close();  
    }  
    public static int getMaxK(int[] a,int begin,int end,int k){  
        int[] aT=new int[end-begin+1];  
        for (int i =0 ; i < aT.length; i++) {  
            aT[i]=a[begin-1+i];  
        }  
        Arrays.sort(aT);  
        return aT[aT.length-k];  
    }  

} 
```

*   最大最小公倍数
    [原文链接](http://blog.csdn.net/qq_34594236/article/details/51178164)

```
/*  
算法训练 最大最小公倍数   

问题描述  
已知一个正整数N，问从1~N中任选出三个数，他们的最小公倍数最大可以为多少  

。  

输入格式  
输入一个正整数N。  

输出格式  
输出一个整数，表示你找到的最小公倍数。  
样例输入  
9  
样例输出  
504  
数据规模与约定  
1 <= N <= 10的6次方。  

*/  
import java.util.Scanner;  
public class Main {  

    public static void main(String[] args) {  
        Scanner sc = new Scanner(System.in);  
        long n = sc.nextInt();  

        long result1 = n*(n-1)*(n-2);  
        long result2 = n*(n-1)*(n-3);  
        long result3 = (n-3)*(n-1)*(n-2);  
        if(n==1){System.out.println(1);}  
        else{  
            if(n==2){System.out.println(2);}  
            else{  
                if(n%2 != 0){  
                    System.out.println(result1);  
                }  
                else{  
                    if(n%3 != 0){  
                        System.out.println(result2);  
                    }else{  
                        System.out.println(result3);  
                    }  
                }  
            }  
        }  
    }  

}  
```

*   K 好数
    思路：这个数组 a 是用来存放数量的，这一题不需要写出进制数然后去判断，只需要知道数量就可以了。
    我说一个能想通的方向，a[1][i] 表示 1 位 k 进制数最后一位是 i 的数量，a[2][i] 表示 2 位 k 进制数且在最高位是 i 的数量，然后 a[3][i] 表示 3 位 k 进制数且在最高位是 i 的数量。最后就像树一样，直到 a[l][i]，最高位是 l，l 后面可以跟的数是他的分叉，分叉，分叉。结果就是所有分叉个数的和。

```
/*  
问题描述  K好数
如果一个自然数N的K进制表示中任意的相邻的两位都不是相邻的数字，那么我们就说这个数是K好数。求L位K进制数中K好数的数目。例如K = 4，L = 2的时候，所有K好数为11、13、20、22、30、31、33 共7个。由于这个数目很大，请你输出它对1000000007取模后的值。  

输入格式  
输入包含两个正整数，K和L。  

输出格式  
输出一个整数，表示答案对1000000007取模后的值。   
样例输入  
4 2   
样例输出  
7   
数据规模与约定  
对于30%的数据，KL <= 106；  

对于50%的数据，K <= 16， L <= 10；  

对于100%的数据，1 <= K,L <= 100。  

*/  
import java.util.Scanner;
public class Main2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();
        int l=sc.nextInt();
        int [][]a=new int[l+1][k];
        for(int j=0;j<k;j++){
            a[1][j]=1;
        }
        for(int i=2;i<=l;i++){
            for(int j=0;j<k;j++){
                for(int p=0;p<k;p++){
                    if(p!=(j-1)&&p!=(j+1)){
                        a[i][j]=(a[i][j]+a[i-1][p])%1000000007;
                    }
                }
            }
        }

        int sum=0;
        for(int i=1;i<k;i++){
            sum+=a[l][i];
            sum%=1000000007;
        }
        System.out.println(sum);
        sc.close();
    }

}
```

<link rel="stylesheet" href="http://csdnimg.cn/release/phoenix/production/markdown_views-10f5517761.css">