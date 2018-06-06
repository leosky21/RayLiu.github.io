package com.blue;

import java.math.BigDecimal;
import java.util.Scanner;

public class HighBigDecimal {
	
	public static void main(String[] args) {
		BigDecimal a;
		BigDecimal b;
		BigDecimal c;
		Scanner s = new Scanner(System.in);
		a = s.nextBigDecimal();
		b = s.nextBigDecimal();
		c = a.add(b);	
		System.out.println(c);
		s.close();
	}
}
