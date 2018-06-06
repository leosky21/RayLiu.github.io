package TestOperatorArrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 数组常常用于保存线性数组·
 * 数组的索引 类似于指针--> 酒店房间的房间号
 * @author liujun
 */

/**
 * 当通过 索引使用数组元素的时候，将 该元素当成普通的变量使用即可
 *  包括访问该数组元素的值，为数组元素赋值等等；
 *  下列 程序示范了 将数组元素和普通变量 相互赋值的 情形；
 * @author liujun
 *
 */
public class TestArray {
	
	
	@SuppressWarnings("null")
	protected int[]	 getRandom(int n){
		int[] s = new int[n];
		Random rd = new Random();
		for(int i = 0;i<n;i++){
			s[i] = rd.nextInt(n)+n;
		}
		return s;
	}
	
	protected void  getMax(int[] a ){
		int Max = 0;
		for(int max:a){
			System.out.println("数组："+max);
		}
		for(int i = 0;i<a.length;i++){
			if(Max<a[i]){
				Max = a[i];
			}
		}
		System.out.println("Max="+Max);
	}
	protected void  getMin(int[] a){
		int Min = a[0];
		for(int min:a){
			System.out.println("数组："+min);
		}
		for(int i = 1;i<a.length;i++){
			if(Min>a[i])
				Min = a[i];
		}
		System.out.println("Min="+Min);
	}
	
	protected void catArray(double weight,int age){
		//	动态定义int【】 数组
		int[] pos = new int[5];
		// 通过循环为每一个赋值
		for(int i = 0;i<pos.length;i++){
			pos[i] = i*2;
		}
		// 对于 pos 数组的元素来说，用起来 完全是 普通变量
		// 下面既可将数组元素的值 赋给int变量。也可以将int 变量的值给数组元素
		int a = pos[1];
		int b = 20;
		pos[2] = b;
		
		//定义并动态初始化一个Cat【】数组
		Cat[] cats = new Cat[2];
		// 构造方法 。！！！
		cats[0] = new Cat(3.34,2);
		/**
		 * 执行完这部分的代码，内存再次增加一个长度为2 的Cat[] 数组，而栈内存中则增加了c1,c2
		 * 两个引用类型。
		 * 从 
		 */
	}
	
	// 普通数组排序 CommonArraySequence  by Arrays.sort
	protected void commonSequence() throws IOException {
		String m = new String("");
		int[] a = {0,0,0}; // create a Array a
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("input three numbers(separated by spaces): ");// separated by sapces
		m = stdin.readLine();
		Scanner scanner = new Scanner(m);
		for(int i = 0;i<3;i++){
			// compare numbers inputed by for-circulate
			a[i] = scanner.nextInt();
		}
		Arrays.sort(a);// Arrays . sort
		System.out.println("The descending order of the three numbers is:");
			print(a);
	}
	// 简单选择排序法 selectSort
	protected void selectSort(int [] nums,String str) {
		if(str.equalsIgnoreCase("desc")){
			//descending  order of nums[]
			for(int i =0;i<nums.length;i++){
				for (int j=i+1;j<nums.length;j++){
					if(nums[i]<nums[j]){
						swap(i,j,nums);
					}
				}
			}
		}else if (str.equalsIgnoreCase("asc")){
			// asrisding ordedr of nums[];
			for(int i = 0;i<nums.length;i++){
				for(int j= i+1;j<nums.length;j++){
					if(nums[i]>nums[j]){
						swap(i,j,nums);
					}
				}
			}
		}
	}
	// 简单选择排序的改进------二元选择排序
	protected void twoSelectSort(){
		int [] nums = {58,12,11,3,57,3,4,5};
		for(int i = 1;i<nums.length/2;i++){
			int max = i;  int min = i;
			for(int j = i+1;j<nums.length-i;j++){
				if(nums[j]>nums[max]) max = j;
				if(nums[j]<nums[min]) min = j;
			}
			swap(i-1,min,nums);
			swap(nums.length-i,max,nums);
		}
		print(nums);
	}
	
	// BubbleSort 交换：冒泡排序------从小到大排序
	protected void bubbleSort(){
		int arr[] = {2,1,5,8,21,14};
		// define a Array
		System.out.println( "before Bubble sort:");
		for(int a:arr){
			System.out.print(a+" ");
			System.out.println("");
			
		}
		for(int i=0;i<arr.length-1;i++){    //----------大的往后移
			for(int j =0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}	
			}
		}
//		 for (int i = 0; i < n-1; i++) {		//----------小的往前移
//	            for (int j = i + 1; j < n; j++) {
//	                if (array[j] < array[i]) {
//	                    int temp = array[i];
//	                    array[i] = array[j];
//	                    array[j] = temp;
//	                }
//	            }
//	        }
		System.out.println("after bubble sort:");
		for(int b : arr){
			System.out.print(b+" ");
		}
	}
	
	// quickSort 快速排序
	protected void swap(int x,int y,int[] arr){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	protected void quickSortMainText(){
		int[] arr = {12,11,3,57,3,4,5};
		System.out.println("before :");
		for(int a :arr){
			System.out.print(a+" ");
		}
		System.out.println("");
		quick_sort_recursive(0,arr.length-1,arr);
		
		for(int ar:arr){
			System.out.print(ar+" ");
		}
	}
	private void quick_sort_recursive(int start, int end,int arr[]) {
		if(start> end) return;
		int mid = arr[end];
		
		int left = start,right = end-1;
		while(left<right){
			while(left<right&&arr[left]<=mid) left++;
			while(left<right&&arr[right]>=mid) right--;
			swap(left,right,arr);
		}
		if(arr[left]>=arr[end]) swap(left,end,arr);
		else left++;
		if(start > end){
		quick_sort_recursive(start,left-1,arr);
		quick_sort_recursive(right+1,end,arr);}
	}

//	protected  void quickSortTextMain() {
//		int[] arr= {12,11,3,57,3,4,5};
//		int [] b;
//		System.out.println("before quickSort:");
//		for(int a:arr){
//			System.out.print(a+" ");
//			
//		}	
//		System.out.println("");
//		 b = quickSort(arr,0,arr.length-1);
//		 System.out.println("after quickSort:");
//		 for(int s : b){
//			 System.out.print(s+" ");
//		 }
//	}
//		protected int getMiddle(int[] arr,int left,int right){
//			// 进行一趟中心排序，返回中心点位置
//			 int mid = arr[left];
//			 int temp;
//			 System.out.println("ing:");
//			 	while (left<right){
//			 		while (left<right&&arr[right]>=mid)
//				 			right--;
//			 		if(left<right){
//			 			temp= arr[right];
//				 		// 将比中心点小的数据移动到左边
//				 		arr [right] = arr[left];
//				 		arr[left] = temp;
//				 		left++;}
//			 		while(left<right && arr[left]<=mid)
//					 		left++;
//			 		if(left<right){
//					 	temp= arr[right];
//				 		// 将比中心点大的数据移动到右边
//				 		arr [right] = arr[left];
//				 		arr[left] = temp;
//				 		right--;
//			 		}
//			 		for(int s : arr){
//			 			System.out.print(s+"  ");
//			 			
//			 		}
//			 		System.out.println("");
//			 	}
//			 	System.out.println("");
//			 		arr [left] = mid;
//			 		//中心移到 正确位置
//			 	return left;//返回 中心点
//		}
//			protected  int[] quickSort(int[] arr,int left,int right){
//			// 快速排序算法部分    如果开始点和结束点没有重叠的时候，也就是指针没有执行到结尾
//			if(left<right -1){
//				int mid = getMiddle(arr,left,right);//重新获取中间点
//				quickSort(arr,left,mid-1);
//				quickSort(arr,mid+1,right);
//			}
//			return arr;
//		}
	
		// 直接插入排序
		protected void directInsertSort(){
			double[]  dI = {1,2.3,1.22,6.7,9.5,6.3};
			for(int i = 2;i<dI.length;i++){
				dI[0] = dI[i];
				for(int j = i-1;j>0&&dI[j]>dI[0];j--){
					dI[j+1] = dI[j];
					dI[j] = dI[0];
				}
			}
			for(double s : dI){
				System.out.print(s+"  ");
			}
		}
		//希尔插入排序
		protected void shellInsertSort(){
			int s[] = {12,11,3,57,3,4,5};
			for(int increment =s.length/2;increment>=1;increment/=2){
				//循环进行分组
				for(int i= increment;i<s.length;i+=increment){//组内排序
					int temp = s[i];
					int j =0;
					for( j= i;j>=increment;j-= increment){
						if(temp<s[j-increment]){ // 元素进行判断、交换
							s[j] = s [j-increment];
						}else break;
					}
					s[j] = temp;
				}
			}
			for(int ss :s){
				System.out.print(ss+"  ");
			}
			System.out.println("");
		}
		
		// 使用 sort 方法对数组排序
		protected void SortSequence(){
			int []  ss = getRandom(10);
			System.out.println("before:"+" ");
			for(int s :ss){
				System.out.print(s+" ");
			}
			System.out.println("");
			Arrays.sort(ss);			// 升序排序
			System.out.println("after:");
			for(int a : ss){
				System.out.print(a+" ");
			}
		}
		
	public static void main(String[] argv){
		/**  数组
		 * 静态初始化：
			初始化时，程序员 显式的指定每一个数组元素的初始值、且 由系统决定数组长度；
		   动态初始化：
			程序员指定长度、系统分配初始值；
		 */
		//静态初始化
		String[] books = new String[]{"java","android","精神分析引论"};
		String[]  names = {"leo","jun","leeo"};
		//动态初始化
		int[] readTimes = new  int[5]; 
		TestArray ta = new TestArray();
		
		
			//ta.commonSequence();
			//ta.bubbleSort();
			//ta.quickSortMainText();
			//ta.directInsertSort();
			//ta.SortSequence();
			//ta.shellInsertSort();
			ta.twoSelectSort();
		
	}
	protected void print(int[] ss){
		for(int s :ss){
			System.out.println(s+" ");
		}
	}
}


class Cat{
	double weight;
	int age;
	// 构造方法
		public  Cat(double weight,int age){
			this.weight = weight;
			this.age = age;
		}
}
