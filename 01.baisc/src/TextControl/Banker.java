package TextControl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Banker {
	
	private  double yu_e ;
	private   double li_lv;
	
	// 构造方法，每个对象值就会绑定了（相当于每次新生出来一个孩子，就会默认给他确定性别、家庭环境）
	public Banker(double yu_e,double li_lv) {
		this.yu_e = yu_e;
		this.li_lv = li_lv;			
	}
	// 存钱的方法
	public void add_money(double add_money) {
		yu_e = yu_e + add_money;
	}
	// 查询余额
	public double get_yue() {
		return yu_e;
	}
	// 更改利率
	public void setli_lv(double lilv) {
		li_lv = lilv;
		System.out.println("当前利率更改为："+li_lv);
	}
	// 读取利率
	public double getlilv() {
		return li_lv;
	}
	// 取钱
	public void getMoney(double money) {
		if(this.yu_e >= money) {
			this.yu_e = this.yu_e - money;
			System.out.println("取出"+money);
			System.out.println("剩余:"+this.yu_e);
		} else {
			System.out.println("余额不足");
		}
	}
	//计算5年的利息
	public void fiveMoney() {
		for(int i=1;i<=5;i++) {
			this.yu_e = this.yu_e + this.yu_e* this.li_lv;
			// BigDecimal 是为了确保银行系统的数据不会出错，java特别出来的一个专门正对银行系统类似的数据计算处理。可以不用太关注
			BigDecimal bigDecimal = new BigDecimal(this.yu_e);
			BigDecimal totalMoney = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			System.out.println("年利率为"+this.li_lv+"的第"+i+"年的存款为"+totalMoney);
		}
	}
	
	public static void main(String[] args) {
		// 设立temp的原因查看求公倍数公约数的程序注释
		String temp = "";
		double startMoney=0;
		double  lilv=0.00;
		
		Scanner  scanner = new Scanner(System.in);
		System.out.println("输入初始账户余额：");
		temp = scanner.nextLine();
		startMoney = Double.parseDouble(temp) ;
		
		for(int i=0;i<3;i++) {
		System.out.println("输入第"+(i+1)+"中利率：");
		temp = scanner.nextLine();
		lilv = Double.parseDouble(temp);
		Banker bankerAccout = new Banker(startMoney, lilv);
		bankerAccout.fiveMoney();
		}
		
		
	}

}
