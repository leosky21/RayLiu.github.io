package com.ray.enitity;

public class Schedule {
	private String CName;
	private String TimeID;
	private String TimeOf;
	private String SMonday;
	private String STuesday;
	private String SWensday;
	private String SThursday;
	private String SFriday;
	
	public Schedule() {
		super();
	}
	
	public Schedule(String cName, String timeOf,String timeID, String sMonday, String sTuesday, String sWensday,
			String sThursday, String sFriday) {
		super();
		CName = cName;
		TimeID = timeID;
		TimeOf=timeOf;
		SMonday = sMonday;
		STuesday = sTuesday;
		SWensday = sWensday;
		SThursday = sThursday;
		SFriday = sFriday;
	}

	public String getCName() {
		return CName;
	}
	public String getTimeID() {
		return TimeID;
	}
	public String getTimeOf() {
		return TimeOf;
	}
	public String getSMonday() {
		return SMonday;
	}
	public String getSTuesday() {
		return STuesday;
	}
	public String getSWensday() {
		return SWensday;
	}
	public String getSThursday() {
		return SThursday;
	}
	public String getSFriday() {
		return SFriday;
	}
	public void setCName(String cName) {
		CName = cName;
	}
	public void setTimeID(String timeID) {
		TimeID = timeID;
	}
	public void setTimeOf(String TimeOf) {
		this.TimeOf = TimeOf;
	}
	public void setSMonday(String sMonday) {
		SMonday = sMonday;
	}
	public void setSTuesday(String sTuesday) {
		STuesday = sTuesday;
	}
	public void setSWensday(String sWensday) {
		SWensday = sWensday;
	}
	public void setSThursday(String sThursday) {
		SThursday = sThursday;
	}
	public void setSFriday(String sFriday) {
		SFriday = sFriday;
	}
	
	
	
}
