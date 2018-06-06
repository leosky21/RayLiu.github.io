package Imitate_Process;

import java.util.ArrayList;
import java.util.List;
import Imitate_Process.Imitate_ProcessRR;

public class Imitate_ProcessSPF {
	private static List<enitity_PCB> ready,run,block,finish;
	private static enitity_PCB p1 ,p2,p3,p4,p5;
	
	protected void SPFInit(){
		int p = 0;
		int allT= 0;
		// 初始化ready队列，并生成相关数据
		ready = new ArrayList<enitity_PCB>();
		run = new ArrayList<enitity_PCB>();
		block = new ArrayList<enitity_PCB>();
		finish = new ArrayList<enitity_PCB>();
		p1 = new enitity_PCB();
		p2 = new enitity_PCB();
		p3 = new enitity_PCB();
		p4 = new enitity_PCB();
		p5 = new enitity_PCB();
		ready.add(p1);
		ready.add(p2);
		ready.add(p3);
		ready.add(p4);
		ready.add(p5);
		for(int i=0;i<5;i++){
			//list.add(new enitity_PCB());
			ready.get(i).setId(i+1);
			allT=(int)(Math.random()*100)%5+1;
			ready.get(i).setAllTime(allT);
			// 保证BlockTime < AllTime
			ready.get(i).setBlockTime((int)(Math.random()*100)%allT+1);
			//ready.get(i).setCpuTime((int)(Math.random()*100)%5+1);
			ready.get(i).setCpuTime(p);
			ready.get(i).setStartBlock((int)(Math.random()*100)%5+1);
			ready.get(i).setState("ready");
			if(i<4)
				ready.get(i).setNext(ready.get(i+1));
		}	
	}
	protected void SPFJudgeReady(){
		// 挑选 短进程 重新排序 进入Run队列
	}
	protected void SPFRun(){
		while(!((block.size()==0)&&(ready.size()==0)&&(run.size()==0))){
		if(ready.size()<=0){
			//判断 ready 队列是否为空
			// 为空，则减少blocktime 的时间片
			SPFJudgeBlock();	
			SPFRun();
			SPFPrint();
		}
		else if(ready.size()>0){
			// ready 不为空，则从ready获取新的元素
			run.add(ready.get(0));
			ready.remove(0);//}
			// 更改状态为run
			run.get(0).setState("run");
			//对于Run队列中的进程：cputime+1,alltime-1,startblock-1;
			run();
			// block队列中同时 减去一个时间片
				if(SPFFinish()){
					run.remove(0);
					SPFRun();
					}else{
						SPFReady();
				}
			}
		}	
	}
	protected void run(){
		int pcpuTime=0;
		int palltime=0;
		int startblock=0;
		// run 队列运行一个时间片
		if((run.get(0).getBlockTime()==0)&&(
			run.get(0).getStartBlock()==0)){
		 pcpuTime = run.get(0).getCpuTime(); 
			pcpuTime++;  	
			run.get(0).setCpuTime(pcpuTime);
		 palltime = run.get(0).getAllTime(); 
			palltime--; 	run.get(0).setAllTime(palltime);
		}else{
		 pcpuTime = run.get(0).getCpuTime(); 
			pcpuTime++;  	run.get(0).setCpuTime(pcpuTime);
		 palltime = run.get(0).getAllTime(); 
			palltime--; 	run.get(0).setAllTime(palltime);
		 startblock = run.get(0).getStartBlock(); 
			startblock--;   run.get(0).setStartBlock(startblock);
			}
		// block 运行一个时间片，且同时将接触block添加到ready队列
			if(block.size()>0){
				SPFJudgeBlock();
			//System.out.println("----------------------运行过rrJudgeBlock了,+block.get(0).getBlockTime():"+block.get(0).getBlockTime());
			}
			SPFPrint();
		//isBlock();
	}
	// 递归运行
	protected void isBlock(){
		// 一个时间片之后判断有无 需要阻塞的元素 添加到block 队列中
		 if(run.get(0).getStartBlock()==0){
			run.get(0).setState("block");
			// 可以阻塞，加入block队列，并且递归运行
			block.add(run.get(0));
			run.remove(0);
			SPFRun();
			}
	}
	protected void SPFReady(){
		if(run.get(0).getStartBlock()>0
				&& run.get(0).getAllTime()>0){
			run.get(0).setState("ready");
			// 将运行一次的元素重新放置到ready队列
			ready.add(run.get(0));
			// 重新链接 逻辑关系
			SPFSort();
			// 移除现run队列元素并低轨运行
			run.remove(0);
			SPFRun();	
		}else{
			isBlock();
		}
	}
	protected void SPFSort(){// 重新链接逻辑关系
		for(int i=0;i<ready.size()-1;i++){
			ready.get(i).setNext(ready.get(i+1));
		}	
	}
	protected boolean SPFFinish(){
		// 判断alltime=0 run队列中的元素是否运行结束，结束则更改状态为 finish 且放入到finish队列
		//System.out.println("---------run.get(0).getAllTime():"+run.get(0).getAllTime());
		int allTime = run.get(0).getAllTime();
		if(allTime!=0){
			return false;
		}else {
			// 已运行结束，加入finish队列
			run.get(0).setState("finish");
			enitity_PCB p = run.get(0);
			finish.add(p);
			return true;
		}
	}
	
	protected void SPFJudgeBlock(){
		// 运行一个时间片，减少阻塞时间blockTime
		// 将blocktime==0的元素放置到 ready队列中
			for (int i=0;i<block.size() ;i++ ) {
				int rBlockTime=block.get(i).getBlockTime();
				if(rBlockTime == 0){
					//将 blcoktime==0 的元素放置到 ready 队列中
					// 并重新确定逻辑关系
			
					block.get(i).setBlockTime(rBlockTime);
					block.get(i).setState("ready");
						ready.add(block.get(i));
						SPFSort();
						// 移除block队列中该元素
						block.remove(i);	
					//System.out.println("-----------------------------rrJudgeBlock内部三，block.size():"+block.size());
					}else{
						rBlockTime--;
						block.get(i).setBlockTime(rBlockTime);
					}				
				}
			//	rPrint();
		}
	
	
	public static void main(String[] argv){
		Imitate_ProcessSPF ip = new Imitate_ProcessSPF();
		ip.SPFInit();
		System.out.println("--------------------------------------初始队列值--------------------------------");
		for(int i =0;i<5;i++){
			System.out.print(
					"id:"+ready.get(i).getId()+" "+
					//"地址："+ready.get(i)+
					"/getBlockTime: "+ready.get(i).getBlockTime()+" "+
					"/getAllTime: "+ready.get(i).getAllTime()+" "+ 
					"/getCpuTime: "+ready.get(i).getCpuTime()+" "+
					"/getStartBlock: "+ready.get(i).getStartBlock()+" "+
					"/getState: "+ready.get(i).getState()+" "
					+"/getNext: "+ready.get(i).getNext()
					);
			System.out.println("");
		}
		
			ip.SPFRun();
			ip.SPFPrint();
	}





protected void SPFPrint(){
	System.out.println("");
	System.out.println("*******************************************************************************");
	System.out.println("READY:");
	for(int i=0;i<ready.size();i++){
		System.out.print("id:"+ready.get(i).getId()+" "+
				//"地址："+ready.get(i)+
				"/getBlockTime: "+ready.get(i).getBlockTime()+" "+
				"/getAllTime: "+ready.get(i).getAllTime()+" "+ 
				"/getCpuTime: "+ready.get(i).getCpuTime()+" "+
				"/getStartBlock: "+ready.get(i).getStartBlock()+" "+
				"/getState: "+ready.get(i).getState()+" "
//				"/getNext: "+ready.get(i).getNext()
				);
		System.out.println("");
	}
	System.out.println("");
	System.out.println("RUN:");
	for(int j=0;j<run.size();j++){
		System.out.print("id:"+run.get(j).getId()+" "+
				//"地址："+run.get(j)+
				"/getBlockTime: "+run.get(j).getBlockTime()+" "+
				"/getAllTime: "+run.get(j).getAllTime()+" "+ 
				"/getCpuTime: "+run.get(j).getCpuTime()+" "+
				"/getStartBlock: "+run.get(j).getStartBlock()+" "+
				"/getState: "+run.get(j).getState()+" "
//				"/getNext: "+run.get(j).getNext()
				);
		System.out.println("");
	}
	System.out.println("");
	System.out.println("BLOCK:");
	for(int m=0;m<block.size();m++){	
		System.out.print("id:"+block.get(m).getId()+" "+
				//"地址："+block.get(m)+
				"/getBlockTime: "+block.get(m).getBlockTime()+" "+
				"/getAllTime: "+block.get(m).getAllTime()+" "+ 
				"/getCpuTime: "+block.get(m).getCpuTime()+" "+
				"/getStartBlock: "+block.get(m).getStartBlock()+" "+
				"/getState: "+block.get(m).getState()+" "
//				+"/getNext: "+block.get(m).getNext()
				);
		System.out.println("");
	}
	System.out.println("");
	System.out.println("FINISH:");
	for(int n=0;n<finish.size();n++){
		System.out.print("id:"+finish.get(n).getId()+" "+
				//"地址："+finish.get(n)+
				"/getBlockTime: "+finish.get(n).getBlockTime()+" "+
				"/getAllTime: "+finish.get(n).getAllTime()+" "+ 
				"/getCpuTime: "+finish.get(n).getCpuTime()+" "+
				"/getStartBlock: "+finish.get(n).getStartBlock()+" "+
				"/getState: "+finish.get(n).getState()+" "
//				+"/getNext: "+finish.get(n).getNext()
				);
		System.out.println("");
	}
}
}

