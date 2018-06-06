package cn.hhit.exception;

public class ValiateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121498132035382211L;
	public ValiateException() {
		super();
		
	}

	public ValiateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValiateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ValiateException(String message) {
		super(message);
	}

	public ValiateException(Throwable cause) {
		super(cause);
	}
	
	

}
