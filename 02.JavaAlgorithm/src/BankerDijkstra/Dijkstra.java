package BankerDijkstra;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Dijkstra {
	/**
	 * 资源向量 需设置成static 因为可能 同一个线程多次输入才能满足条件
	 */
	//每一个线程需要的最终资源数
	static int MAX[][] = {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
	// 可利用资源 数 ---系统可用资源数
	static int AVAILABLE[] = {10,5,7};
	// 已分配资源数  
	//static int ALLOCATION[][] = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	static int ALLOCATION[][] = {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
	// 每一个进程还需要的资源数，初始资源一个没有分配；
	// NEED = MAX - AVAILABLE
	static int NEED[][] = Arrays.copyOf(MAX, MAX.length);
	// 每次申请的资源数
	static int REQUEST[] = {0,0,0};
	// 进程数 和资源类 数
	final static int Pnum = 5,Rnum = 3;
	
			public static void main(String[] argv){
				JOptionPane jpane = new JOptionPane();
				int flag = 1;
				while(flag == 1){
					/**
					 * 用于判断线程号是否合法；防止第二次模拟依然是首次输入值
					 * 需要在while 内部
					 */
					int i=-1;
					while(i<0||i>=Pnum){
						String str = JOptionPane.showInputDialog(jpane, "请输入申请资源的线程号(0~4)：");
						i = Integer.parseInt(str.trim());
						if(i<0||i>=Pnum){
							JOptionPane.showMessageDialog(jpane, "输入线程不合法！");
						}else if(i>=0&&i<Pnum){
							break;
						}
					}
						//资源输入有效性标志
					boolean  tag = true;
					for(int j=0;j<Rnum;j++){
						String str = JOptionPane.showInputDialog("输入线程"+i+"所申请第"+(j+1)+"类资源数目：");
						REQUEST[j] = Integer.parseInt(str);
						//有效性检查
						if(REQUEST[j]>NEED[i][j]){
							JOptionPane.showMessageDialog(jpane, "输入资源过大,超过最终值");
							tag = false;
							break;
						}else {
						if(REQUEST[j]>AVAILABLE[j]){
							JOptionPane.showMessageDialog(jpane, "输入资源过大,暂无这么多资源");
							tag = false;
							break;
								}
							}
						}
						//判断尝试分配是否安全
						boolean isSafe = false;
						if(tag){
							Function.allocateR(i);
							isSafe = Function.check(i);
							if(isSafe == true){
								boolean f = Function.checkRun(i);
								if(f == true){
									JOptionPane.showMessageDialog(jpane, "即将释放资源");	
									Function.freeNotRestore(i);
									}
							}else {
								Function.freeAndRestore(i);
							}
					}else{
						Arrays.fill(REQUEST, 0);
					}	
				}
				
			}			
		}


	class Function {
		//分配资源方法
		protected static void allocateR(int m){
			for(int i=0;i<Dijkstra.Rnum;i++){
				Dijkstra.AVAILABLE[i] = Dijkstra.AVAILABLE[i]-Dijkstra.REQUEST[i];
				Dijkstra.ALLOCATION[m][i] = Dijkstra.ALLOCATION[m][i] + Dijkstra.REQUEST[i];
				Dijkstra.NEED[m][i] = Dijkstra.NEED[m][i] - Dijkstra.REQUEST[i]; 
			}
		}
		//判断是否安全
		protected static  boolean check(int m){
			int[] work = new int[Dijkstra.Rnum];
			boolean finish[] = new boolean[Dijkstra.Pnum];
			int queue[] = new int[Dijkstra.Pnum];//存放安全序列
			int k = 0;//安全队列的下标
			int n;
			Arrays.fill(finish, false);
			Arrays.fill(queue, 0);
			
			while(m<Dijkstra.Pnum){
				for (int j=0;j<Dijkstra.Rnum;j++){
					work[j] = Dijkstra.AVAILABLE[j];
					if((finish[m]==false)&&(work[j]>=Dijkstra.NEED[m][j])){
						work[j] += Dijkstra.ALLOCATION[m][j];
						finish[m] = true;
						queue[k++] = m;
						m=0;
					}else {
						m++;
						if(m==5){
							System.out.println("安全序列："+queue[0]+queue[1]+
									queue[2]+queue[3]+queue[4]);
							break;
						}
					}
				}
			}
			for(int p =0;p<Dijkstra.Pnum;p++){
				if(finish[p]==false)
					return false;
			}
			return true;
		}
		
		protected static void freeAndRestore(int k){
			//释放线程k占用资源 并且恢复
			for(int i=0;i<Dijkstra.Rnum;i++){
				Dijkstra.AVAILABLE[i] += Dijkstra.REQUEST[i];
				Dijkstra.NEED[k][i] += Dijkstra.REQUEST[i];
				Dijkstra.ALLOCATION[k][i] -= Dijkstra.REQUEST[i];
			}
		}
		//仅仅释放线程 所占用的资源，仅在全部资源运行后才调用
		public static void  freeNotRestore(int k){
			for(int i=0;i<Dijkstra.Rnum;i++){
				Dijkstra.AVAILABLE[i] = Dijkstra.AVAILABLE[i]+Dijkstra.ALLOCATION[k][i];
			}
		}
		public static  boolean checkRun(int k){
			int n= 0;
			for(int i=0;i<Dijkstra.Rnum;i++){
				if(Dijkstra.NEED[k][i]==0)n++;
			}
			if(n==3)
				return true;
			else
				return false;
		}
		
	}









