package cn.ray.exception;

public class CustomerException extends Exception {
	private String expMessage;

	public CustomerException() {

	}

	public CustomerException(String msg) {
		this.expMessage = msg;
	}

	public String getExpMessage() {
		return expMessage;
	}

	public void setExpMessage(String expMessage) {
		this.expMessage = expMessage;
	}
}