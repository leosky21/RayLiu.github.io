package cn.tedu.entity;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 实体类
 * 用于将数据库中表记录，映射为java中对象来使用的。
 * 类名   <--> 表名
 * 属性名 <--> 字段名
 * 对象  <--> 记录
 * 
 * Bean规范：
 * （1）在特定的包结构中
 * （2）要实现可序列化接口
 * （3）所有属性都是私有的，并有对应的getter和setter
 * （4）提供一个无参的构造器
 */
public class Cost implements Serializable{
	//属性
	private Integer costId;	//资费编号
	private String name;		//资费名称
	private Integer baseDuration;
			//基本时长，套餐内包含的时长
	private double baseCost;	//基本费用：套餐最低费用
	private double unitCost;
			//单元费用，超出套餐每分钟的费用
	private String status;
			//套餐状态：0开通，1暂停
	private String descr;	//套餐说明
	private Timestamp creatime;	//创建时间
			//java.sql.Date	保存了年月日
			//java.sql.Timestamp 保存了年月日时分秒
	private Timestamp startime;	//开通时间
	private String costType;	//资费类型
			//1包月；2套餐；3计时
	
	//构造器
	public Cost() {}

	//方法
	public Integer getCostId() {
		return costId;
	}

	public void setCostId(Integer costId) {
		this.costId = costId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBaseDuration() {
		return baseDuration;
	}

	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public Timestamp getStartime() {
		return startime;
	}

	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}
	
	
}




