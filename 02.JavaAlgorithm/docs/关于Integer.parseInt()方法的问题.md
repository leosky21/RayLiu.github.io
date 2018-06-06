> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 http://blog.csdn.net/qq_31283465/article/details/54863828

在我解决蓝桥练习的十六进制转八进制的练习中，我想要使用 Integer 的 parseInt 方法来实现将 16 进制的数封装到 Integer 对象中再输出为 8 进制。
类似这样

```
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            String s=sc.nextLine();
            a[i] = Integer.parseInt(s, 16);
        }
        for (long b : a) {
            System.out.println(Long.toOctalString(b));
        }
        sc.close();
```

运行小例子没问题，但是送到系统出了错。
我便开始下载官网数据 debug，最后发现运行时抛出了 NumberFormatException

然后去查找文档

> 如果发生以下任意一种情况，则抛出一个 NumberFormatException 类型的异常：
> 第一个参数为 null 或一个长度为零的字符串。
> 基数小于 Character.MIN_RADIX 或者大于 Character.MAX_RADIX。
> 假如字符串的长度超过 1，那么除了第一个字符可以是减号 ‘-’ (‘u002D’) 外，字符串中存在任意不是由指定基数的数字表示的字符。
> 字符串表示的值不是 int 类型的值。
> 示例：
> parseInt(“0”, 10) 返回 0
> parseInt(“473”, 10) 返回 473
> parseInt(“-0”, 10) 返回 0
> parseInt(“-FF”, 16) 返回 -255
> parseInt(“1100110”, 2) 返回 102
> parseInt(“2147483647”, 10) 返回 2147483647
> parseInt(“-2147483648”, 10) 返回 -2147483648
> parseInt(“2147483648”, 10) 抛出 NumberFormatException
> parseInt(“99”, 8) 抛出 NumberFormatException
> parseInt(“Kona”, 10) 抛出 NumberFormatException
> parseInt(“Kona”, 27) 返回 411787

简单理解下就是要**封装的数值超出了 int 的范围**。
蓝桥的系统会给你小于 10000 长度的字符串。
所以想要使用 Interger 实现，着实是聪明反被聪明误。

<link rel="stylesheet" href="http://csdnimg.cn/release/phoenix/production/markdown_views-10f5517761.css">