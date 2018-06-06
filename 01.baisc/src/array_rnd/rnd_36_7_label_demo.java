package array_rnd;



public class rnd_36_7_label_demo {

	public static void main(String[] args) {
		int[] a = new int[7];
		for(int i = 0;i<a.length;i++){
			one_num:
				while(true){
					a[i] = (int) ((Math.random()*36)+1);
					for(int j = 0;j<i;j++){
						if(a[j]==a[i]) continue one_num;
					}
				break;
				}
		}
		for(int num : a){
			System.out.println(" "+num+" ");
			System.out.println("");
		}
	}

}

