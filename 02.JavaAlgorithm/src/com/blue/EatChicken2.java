package com.blue;
import java.text.DecimalFormat;
import java.util.Scanner;
  
public class EatChicken2 {
  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
          
        while(input.hasNext()){
            int [] acce_int = new int [1001]; // 记录配件种类
            double [] acce_double = new double[1001]; // 记录配件加成
  
            int [][] rate = new int [1001][1005];    // 记录枪械的信息
            double P [] = new double[1001];
              
            int n  = input.nextInt(), m = input.nextInt(); // 枪类的个数与配件的个个数
  
            for(int i = 0; i < n; i++){
                for(int j = 0; j < 2; j++){
                    rate[i][j] = input.nextInt(); // 记录枪的初始伤害和配件个数，
                }
                  
                P[i] = rate[i][0];
                  
                for(int j = 2; j < rate[i][1] + 2; j++){
                    rate[i][j] = input.nextInt(); // 记录枪的配件种类
                }
            }
  
            for(int i = 0; i < m; i++){
                acce_int[i] = input.nextInt();  // 配件种类
                acce_double[i] = input.nextDouble(); // 配件加成
            }
  
            /*
                尝试输出，看看输入的数据是否正确，
              
            System.out.println("");
            for(int i = 0; i <= n; i++){
                for(int j = 0; rate[i][j] != 0; j++){
                    System.out.print(rate[i][j] + " ");
                }
                System.out.println("");
            }
              
            System.out.println("所拥有的配件数：");
            for(int i = 0; i < m; i++){
                System.out.println("配件类型：" + acce_int[i] + "  配件加成：" + acce_double[i]);
            }*/
              
  
            double pp = 0.0;
            for(int i = 0; i < n; i++){ // 枪支的循环
                pp =  rate[i][0]; // 获取初始伤害
                for(int j = 0; j < rate[i][1]; j++){ //  为枪的每一个配件安装 可能的配件
                    double tmp = 0.0;
                    for(int k = 0; k < m; k++){ // 所有拥有的配件的循环
                        if(acce_int[k] == rate[i][j + 2]){ // 配件相匹配，换上去
                            tmp = tmp > acce_double[k] ? tmp : acce_double[k]; // 获取伤害加成最大的配件
                        }
                    }
                    P[i] += tmp * pp;
                }
            }
              
  
            double max = 0.0;
            for(int i = 0; i < n; i++){
                if(max < P[i]){
                    max = P[i];
                }
            }
              
//            System.out.printf("%.4f",max);  这个 精度有问题 ，不能通过测试 ，
            DecimalFormat decimalFormat = new DecimalFormat(".0000");
            String str = decimalFormat.format(max);
            System.out.println(str);
        }
              
          
          
    }
      
}
