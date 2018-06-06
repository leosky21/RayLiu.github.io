package com.weibo.jg.bean;

public class wbpoint {
	private String lng	; 
	private String lat	;
	private String place;
	private String infortext;
	private String publicTime;
	private String url;
	public String geturl()
	{
		return url;
	}
	public String getpublicTime()
	{
		return publicTime;
	}
	public String getinfortext()
	{
		return infortext;
	}
	public String getplace()
	{
		return place;
	}
	public String getLng()
	{
		return lng;
	}
	public String getLat()
	{
		return lat;
	}
	public void seturl(String url) 
	{
		this.url = url;
	}
	public void setpublicTime(String publicTime) 
	{
		this.publicTime = publicTime;
	}
	public void setinfortext(String infortext) 
	{
		this.infortext = infortext;
	}
	public void setplace(String place) 
	{
		this.place = place;
	}
	public void setLat(String lat)
	{
		this.lat = lat;
	}
	public void setLng(String lng) 
	{
		this.lng = lng;
	}
}
