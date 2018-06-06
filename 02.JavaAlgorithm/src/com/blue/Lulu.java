package com.blue;

import java.util.*;
public class Lulu
{
    public static void main(String[] args)
    {
        int[] arr = new int[11];
        arr[1] = 1;
        for(int i = 2;i < 11;i++)
        {
            arr[i] = arr[i-1]*i;
           // System.out.println("arr["+i+"]::"+arr[i]);
        }
        Scanner yina = new Scanner(System.in);
        int t = yina.nextInt();
        while(t-- > 0)
        {
            int n = yina.nextInt();
            int k = n*2;
            if(n%2 == 1)
                k--;
            StringBuffer s = new StringBuffer("");
            for(int i = 10;i > 0;i--)
            {
                if(k / arr[i] > 0)
                {
                    for(int j = k / arr[i];j > 0;j--)
                    {
                        s.append(i-1);
                    }
                    k %= arr[i];
                    System.out.println("k::"+k);
                }
            }
            System.out.println(s.toString());
        }
    }
}