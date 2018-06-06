package BankerDijkstra;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Banker_Dijkstra {

	static int available[]={3,3,2};         //可利用资源数  
    static int max[][]={{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};;          //每线程最大需求  
    static int allocation[][]={{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};   //已分配资源  
    static int need[][]={{7,4,3},{1,2,2},{6,0,0},{0,1,1},{4,3,1}};        //需求  
    static int request[]=new int[3];           //存放请求资源  
    static int thread;  //线程号 
    static JOptionPane jpane = new JOptionPane();
   // static boolean m;
    
    public static void main(String[] argv){
    	
    	int n = 0 ;
    	Banker_Dijkstra bd = new Banker_Dijkstra();
    	/**
    	 * 初始测试系统状态是否安全
    	 */
    	for(int i=0;i<5;i++){
    		
    		if(bd.safeState(i)){
    			JOptionPane.showMessageDialog(jpane, "系统状态安全");
    			n=1;break;
    		}else{
    			n=2;continue; 				
    		}
    	}   	
    	
    	if(n==1){
    		bd.getThread();
    		}
    	else if(n==2){
    		JOptionPane.showMessageDialog(jpane, "系统状态不安全");
    	}
    }
    //输入测试线程号且输出结果
	protected void getThread(){
		try{
		String xiancheng = JOptionPane.showInputDialog(jpane,"请输入申请资源的线程:");
		thread = (int) Integer.parseInt(xiancheng);
		}catch(Exception e){
			int response = JOptionPane.showConfirmDialog(jpane, "请输入0～4:",null, JOptionPane.YES_NO_OPTION);  
			// 处理异常
	       if(response==0){
	    	   getThread();
	       }else if(response ==1){
	    	   System.exit(0);
	       }
		}
	    
		if(thread<0||thread>4){  
	       JOptionPane.showMessageDialog(jpane, "请输入0～4:");  
	        getThread();  
	    }else{    
	        for(int i=0;i<3;i++){  
	        	String requestR = JOptionPane.showInputDialog(jpane,"请输入申请的第"+(i+1)+"种资源(若不申请则填0)");
	        	try{
	            request[i]=Integer.parseInt(requestR);}
	        	catch(Exception e){
	        		JOptionPane.showConfirmDialog(jpane, "请输入申请的第"+(i+1)+"种资源(若不申请则填0)",null,JOptionPane.YES_NO_OPTION);
	        	}
	        }  
	        if(request[0]>need[thread][0]||request[1]>need[thread][1]||request[2]>need[thread][2]){  
	         JOptionPane.showMessageDialog(jpane,thread+"线程申请的资源超出其需要的资源，请重新输入");  
	            getThread();  
	        }else{  
	            if(request[0]> available[0]||request[1]> available[1]||request[2]> available[2]){  
	         JOptionPane.showMessageDialog(jpane,thread+"线程申请的资源大于系统资源，请重新输入");  
	                getThread();  
	            }  
	        }  
	        // 分配资源
	        allocateData(thread); 
	        //  判断 继续模拟选择与处理
	        int tag=0;
	        if(check(thread)){ 
	        	try{
	             String str = JOptionPane.showInputDialog(jpane,"是／否 继续模拟？( 1/0 ):");
	              tag = Integer.parseInt(str);
	             }catch(Exception e){
	            	 JOptionPane.showMessageDialog(jpane, "继续 输入(数值) 1，不继续 输入(数值) 0 ！");
	             }
	             if(tag==1){ 
	            	 String str = JOptionPane.showInputDialog(jpane,"是／否 重置所有资源？( 1/0 ):");
		              tag = Integer.parseInt(str);
		              if(tag==0){
		              	getThread();}else{
		              		 recoverData(thread);  
		              		getThread();
		              	}
	            	 }
	             else{
	            	if( (JOptionPane.YES_NO_OPTION)==JOptionPane.CANCEL_OPTION)System.exit(0);
	            	if((JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) recoverData(thread);
	             }
	        }else{  
	        	try{
		             String str = JOptionPane.showInputDialog(jpane,"是／否 继续模拟？( 1/0 ):");
		              tag = Integer.parseInt(str);
		             }catch(Exception e){
		            	 JOptionPane.showMessageDialog(jpane, "继续 输入(数值) 1，不继续 输入(数值) 0 ！");
		             }
		             if(tag==1){ 
		            	 String str = JOptionPane.showInputDialog(jpane,"是／否 重置所有资源？( 1/0 ):");
			              tag = Integer.parseInt(str);
			              if(tag==0){
			              	getThread();}else{
			              		 recoverData(thread);  
			              		getThread();
			              	}
	            } else{
			            	if( (JOptionPane.YES_NO_OPTION)==JOptionPane.CANCEL_OPTION)System.exit(0);
			            	if((JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) recoverData(thread);
			             }
	        	}
	       }
	}
	// 安全算法
	private boolean check(int thread2) {
		boolean[] finish = new boolean[5];
			Arrays.fill(finish, false);
		int[] work = new int[3];
		int[] queue = new int[5];
		int q=0;//安全序列下标
		for(int i = 0;i<3;i++){
			work[i] = available[i];
		}
		int tT = thread2;
		while(tT<5){
			for(int R=0;R<3;R++){
				if((!(finish[tT]==false))||(!(work[R]>=need[tT][R]))){
					tT++;
					break;
				}else{
					if(R==2){
						for(int m =0;m<3;m++){
							work[m] += allocation[tT][m];
						}
						for(int s:work){
							System.out.print(s+" ");
						}
						System.out.println("");
						finish[tT] = true;
						queue[q] = tT;
						q++;
						tT =0;
					}
				}
			}	
		}
		
		for(int p =0;p<5;p++){
			if(finish[p]==false){
				JOptionPane.showMessageDialog(jpane, "避免死锁，序列生成失败。");
				return false;
				}
		}
		
			JOptionPane.showMessageDialog(jpane, "安全序列："+queue[0]+","+queue[1]+","
		+queue[2]+","+queue[3]+","+queue[4]);
			return true;
		
	}
	private boolean safeState(int thread3){
		boolean[] finish = new boolean[5];
			Arrays.fill(finish, false);
			int[] work = new int[3];
			int[] queue = new int[5];
			int q=0;//安全序列下标
			for(int i = 0;i<3;i++){
				work[i] = available[i];
			}
			int tT = thread3;
			while(tT<5){
				for(int R=0;R<3;R++){
					if((!(finish[tT]==false))||(!(work[R]>=need[tT][R]))){
						tT++;
						break;
					}
					else{
						if(R==2){
							for(int m =0;m<3;m++){
								work[m] += allocation[tT][m];
							}
							finish[tT] = true;
							queue[q] = tT;
							q++;
							tT =0;
						}}}	}
			for(int p =0;p<5;p++){
				if(finish[p]==false){
					return false;
					}
			}
				return true;
	}
	// 生成失败则重置已分配资源
	private void recoverData(int thread2) { 
		for(int i=0;i<3;i++){  
            //重新调整系统资源数  
            available[i]+=request[i];  
            //计算各个线程拥有资源  
            allocation[thread2][i]-=request[i];  
            //重新计算需求  
            need[thread2][i]+=request[i];  
     }   
	}
	//分配
	private void allocateData(int thread2) { 
		for(int i=0;i<3;i++){  
            //重新调整可用系统资源数  
            available[i]-=request[i];  
            //计算各个线程分配后拥有资源  
            allocation[thread2][i]+=request[i];  
            //重新计算需求  
            need[thread2][i]-=request[i];  
        }     
	}
	
	private void threadWait(){
		
		
	}
}

	
