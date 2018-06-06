package cn.hhit.eshop.utils;

public class MsgUtil {
	//多余的点击注销，可隐藏该按钮
		public static final String EXTRA_LOGOUT_MSG = "已注销，勿重复注销";
	// 防止 csrf 攻击与页面重复提交
		public static final String LOGINMESSAGE="请不要重复提交请求，返回原始页面刷新后再次尝试！！！如果多次尝试未成功,请 enable Cookie!!";
	// 未知错误，请联系管理员
		public static final String ERROR_MSG = "未知错误，请联系管理员";	
}
