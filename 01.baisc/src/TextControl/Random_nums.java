package TextControl;

import java.util.Random;

public class Random_nums {
		static int[] nums = new int[20];
	public static void main(String[] args) {
		//生成随机数的类 Random
		Random random = new Random();
		for(int i=0;i<nums.length;i++) {
			//随机生成100以内的值，且给nums【】数组中的元素赋值
			nums[i] = random.nextInt(100);
		}
		int max =0,min=100,sum=0;
		for(int i=0;i<nums.length;i++) {
			// 只要max 比 nums中的一个元素值小，就将更大值给 max
			if(max <= nums[i]) {
				max = nums[i];
			}
			// 只要nums 比 min中的一个元素值小，就将更小值给 min
			if(min > nums[i]) {
				min = nums[i];
			}
			// 讲每一个数组中的值相加 求和求平均
			sum += nums[i];
		}
		System.out.println("max="+max+";   min ="+min+"  ; average="+sum/(nums.length));
	}

}
