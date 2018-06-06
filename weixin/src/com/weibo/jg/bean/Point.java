package com.weibo.jg.bean;


public class Point 
{
	
private String lng	; 
private String lat	;
private int count;


public String getLng()
{
	return lng;
}
public void setLng(String lng) 
{
	this.lng = lng;
}
public String getLat()
{
	return lat;
}
public void setLat(String lat)
{
	this.lat = lat;
}
public int getCount() 
{
	return count;
}
public void setCount(int count) 
{
	this.count = count+5;
} 

}
