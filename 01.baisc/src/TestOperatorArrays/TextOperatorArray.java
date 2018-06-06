package TestOperatorArrays;

import java.util.Arrays;
import java.util.Scanner;

public class TextOperatorArray {

	/*数组的复制*/
	protected static void ArrayCopy(){
		int Array[] = new int[]{1,2,3,4};//声明数组并且初始化
		int temp1[] = new int[Array.length];
		int temp2[] = new int[Array.length];
		/**通过 System.Arraycopy 复制*/
		System.arraycopy(Array, 0, temp1, 0, Array.length);// srcArray,arcPos,desArray,desPos
		temp2 = Array;
		
		System.out.println("复制后的结果：");
		
		for(int i:temp1){
			System.out.println(i);
		}
		for(int i = 0;i<Array.length;i++){
			System.out.println(i+"temp1:"+temp1[i]+","+"temp2:"+temp2[i]);
		}
		/***
		 * for 循环每次循环会调用nums.length来比较长度. 而 foreach 不考虑长度,只调用一次nums.length
			结论.
				在固定长度或长度不需要计算的时候for循环效率高于foreach.
				在不确定长度,或计算长度有性能损耗的时候,用foreach比较方便.
				并且foreach的时候会锁定集合中的对象.期间不能修改。
		 */
		/** ---关于 for  和 foreach性能
		 * 需要循环数组结构的数据时，建议使用普通for循环，因为for循环采用下标访问，对于数组结构的数据来说，采用下标访问比较好。
			需要循环链表结构的数据时，一定不要使用普通for循环，这种做法很糟糕，数据量大的时候有可能会导致系统崩溃。
		 */
	}
	/*数组插入*/
	protected static void Arrayinsert(){
		int i,j;
		int n=5;
		//int num[] = new int[n+1];
		int num[] = new int[n];
		for(i=0;i<num.length-1;i++){
			num[i] = (i+1)*6;
		}
		int length = num.length;
		System.out.println(" 插入前的数组为：");
		for(j = 0;j<length;j++){
			if(num[j] == 0)  System.out.println("存数字空间");
			else System.out.println(num[j]+"");
		}
		System.out.println("输入要插入的数：");
		Scanner scanner = new Scanner(System.in);
		int readInt = scanner.nextInt();
		
		
		for( i=0;i<length-1;i++){//循环查找 大于要插入数的位置
			if(num[i]>readInt) break;
		}
		for(j = length-1;j>i;j--){// 将 位置之后的数组 往后排
			num[j] = num[j-1];
		}
		
		num[i] = readInt;
		for(i = 0;i<length;i++){
			System.out.println(num[i]+"");
		}
	}
	protected static int[] Arraycombine(int[] a,int[] b){
		int alength = a.length;
		int blength = b.length;
		int cl = alength+blength;
		int[] c = new int[cl];
		int len = 0,i,j;
		for( i = 0,j=0;i<alength&&j<blength;){
			if(a[i]<=b[j]){
				c[len]=a[i];
				len++;
				i++;
			}else{
				c[len] = b[j];
				len++;
				j++;
				}
		}
		if(i==alength){
			System.arraycopy(b, j, c, len, blength-j);
		}else if(j==blength){
			System.arraycopy(a, i, c, len, alength-i);
		}
		for(int cc:c){
			System.out.print(cc+" ");
			
		}
		return c;
	}
	
	public static void main(String[] argv){
		int[] a={2,1,6,4,2,2},b={1,2,4,5,8,0};
		TextOperatorArray toa = new TextOperatorArray();
		toa.ArrayCopy();
		toa.Arrayinsert();
		toa.Arraycombine(a, b);
	}
}
