package com.jf.pagebean;

public class BookQuery {
	
	private String ISBN;
	private String bookTypeId;
	private String bookName;
	private String autho;
	private String press;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAutho() {
		return autho;
	}
	public void setAutho(String autho) {
		this.autho = autho;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	
	@Override
	public String toString() {
		return "BookQuery [ISBN=" + ISBN + ", bookTypeId=" + bookTypeId
				+ ", bookName=" + bookName + ", autho=" + autho + ", press="
				+ press + "]";
	}
	
	
	
	
}
