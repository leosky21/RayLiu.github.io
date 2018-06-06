package cn.hhit.utils;

import java.io.UnsupportedEncodingException;

public enum Msgutil {
	
	errorInfo("errorInfo",-1),
	keyResult("result",0),  // success 则是0, error 则是 -1
	// 未携带合适的参数
	ResgisterNoUser("未传正确的user信息", 1),
	// 一切正常
	SUCCESS("true", 2),
	// 防止 csrf 攻击与页面重复提交
	RepeatMsg("请不要重复提交请求!!！", 3),
	// 未知错误，请联系管理员
	ERROR_MSG("未知错误，请联系管理员", 4),
	// 未携带合适的参数
	LoginNoUser("用户名或者密码错误",5),
	// 没有查询到该条PersistentLogins记录
	PersistentLoginsLoss("验证已过期,请重新登录",6),
	// Null info
	InfoIsNull("信息为null",7);
	
	// 成员变量
	private String Msg;
	private int index;

	// 构造方法
	private Msgutil(String Msg, int index) {
		this.Msg = Msg;
		this.index = index;
	}

	public String getMSG_Key() throws UnsupportedEncodingException {
		return this.Msg;
	}

	public byte[] getInfo() throws UnsupportedEncodingException {
		return this.Msg.getBytes("utf-8");
	}

	public int getIndex() {
		return index;
	}

	// 接口方法
	public void print() {
		System.out.println(this.index + ":" + this.Msg);
	}
}
