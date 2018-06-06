package com.blue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class EatChicken {
	 static Map<Integer,Float> list  = new HashMap<Integer,Float>();
	 static int[][] pk;
	public static void main(String[] args) throws IOException{
		EatChicken e = new EatChicken();
    	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
       String fir = r.readLine();
       int[] nm; nm = stringToIn(fir);
        pk = new int[nm[0]][];
		for(int i=0;i<nm[0];i++) {
	    	   	pk[i] = stringToIn(r.readLine());
	       }
		for(int i=0;i<nm[1];i++) {
			stringToFl(r.readLine());
		}
		e.maxGun(pk,list);
    }
	public  void maxGun(int[][] pk,Map<Integer,Float> list) {
		float[] maxGun = new float[pk.length];
		float t = 0; 
		int count = 0;
		for(int i=0;i<pk.length;i++) {
			for(int s : pk[i]) {	
				count++;
				if(count<=2) {
					continue;
				}
				System.out.println(s+"::"+list.size());
				if(!list.isEmpty())
					t += list.get(s);
			}
			count=0;
			maxGun[i] = pk[i][0] * (t+1);
			t=0;
		}
		float temp=0;
		for(int i=0;i<maxGun.length;i++) {
			temp = Math.max(maxGun[i], temp);
		}
		System.out.printf("%.4f",temp);
	}
	
	public static int[] stringToIn(String str) {
		String[] nums = str.split(" ");
		int[] nm = new int[nums.length];
	       int i=0;
	       for(String num : nums) {
	    	   		nm[i] = Integer.parseInt(num);
	    	   		i++;
	       }
	       return nm;
	}
	public static void stringToFl(String str) {
		String[] nums = str.split(" ");
		int key = Integer.parseInt(nums[0]);
		float value = Float.parseFloat(nums[1]);
		if(list.containsKey(key))
			list.put (key, value > list.get(key) 
				? value : list.get(key) );
		else
			list.put(key,value);
	}
}
/**
 * 
120 3 1 2 3
100 4 1 2 3 4
110 3 2 3 4
1 0.12
2 0.23
2 0.26
4 0.57
3 0.35
5 0.41
*/
