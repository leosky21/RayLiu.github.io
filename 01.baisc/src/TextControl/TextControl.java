package TextControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.print.attribute.standard.PrinterLocation;

public class TextControl {//打印任何一年日历的类
	static int year, month, week, day;
	
	public static boolean isLeapYear(int year){	//判断是否是闰年
		return ((year%4==0)&&(year%100!=0)||(year%400==0));
	}
	
	public static int  firstday(int y){//计算 该年的第一天是星期几
		/**
		 * 1年1月1日是星期一.
		 * 这个程序从1年1月1日开始算到第Y年共有多少天,
		 * 然后取7的余数即是所求的星期.
		 */
		long n = y*365;
		for(int i=1;i<y;i++)
		if(isLeapYear(i))//判断是否闰年
			n+=1;
		return (int)n%7;
	}
	
	public static void printWeek(){//打印日历表头
		System.out.println("=====================");
		System.out.println("日 一 二 三 四 五 六");
	}

	public static int getMonthDays(int m){
		switch(m){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if(isLeapYear(year))
				return 29;
			else if (!isLeapYear(year))
				return 28;
		default:
			return -1;	
		}
	}
	
	public static void printMonth(){//分别按不同条件逐月 打印
		for(int i=1;i<=12;i++){
			System.out.println(i+"月");//月
			printWeek();
			for(int j=0;j<week;j++){//每个月第一天有几个空就打印几个空
				System.out.print("");
			}
			int month=getMonthDays(i);//获取每个月的天数
			for(int d=1;d<=month;d++){
				if(d<10){//以下四行对输出格式化
					System.out.print(d+" ");
				}else 
					System.out.print(d+" ");
				week = (week+1)%7;//每打印一天就反映第二天是星期几
				if(week==0){
					System.out.println("");//周日就换行
				}
			}
			System.out.println('\n');
		}	
	}
	public static void main(String[] args) throws IOException{
		System.out.print("print in year(such as:2017):");
		InputStreamReader ir ;//                以下接受从 控制台的 输入
		BufferedReader in;
		ir = new InputStreamReader(System.in);
		in = new BufferedReader(ir);
		String s = in.readLine();
		year = Integer.parseInt(s);
		week = firstday(year);//计算第一天是星期几
		System.out.println("\n"+year+"年");
		printMonth();
	}
	/**
	 * 将所输入的 文本从流中 InputStream（字节流） 
	 * 传给 InputStreamReader（字符流） 
	 * 再放到BufferStream（缓冲流）中。
	 * ————————有助于读完数据释放已经分配的内存。
	 */
}
