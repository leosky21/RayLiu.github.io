package com.weibo.hotspot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短文本
 */
public class ShortText 
{
    String wid;
	String content = "";
	List<String> wordsList = new ArrayList<String> ( );// 关键词
	Map<String, Integer> wordcount = new HashMap<String, Integer> ( );// 词语出现个数
	
	String hashtag="";  //哈希标签
	List<String> wordsInHashtag = new ArrayList<String> ( );// 关键词
	
	String timetag="";
	
	boolean nsflag;
	

	Integer countInteger = 0;// 单词的总个数

	List<String> topic = new ArrayList<String> ( );// 话题

	public List<String> getTopic ( ) 
	{
		return topic;
	}

	public void setTopic ( List<String> topic ) 
	{
		this.topic = topic;
	}

	public ShortText ( List<String> transSet ) 
	{
		this.wordsList = transSet;
	}

	public ShortText ( ) 
	{
	}

	public List<String> getWordsList ( )
	{
		return wordsList;
	}


	public boolean isNsflag()
    {
        return nsflag;
    }

    public void setNsflag(boolean nsflag)
    {
        this.nsflag = nsflag;
    }

    public String getHashtag()
    {
        return hashtag;
    }

    public void setHashtag(String hashtag)
    {
        this.hashtag = hashtag;
    }

    public List<String> getWordsInHashtag()
    {
        return wordsInHashtag;
    }

    public void setWordsInHashtag(List<String> wordsInHashtag)
    {
        this.wordsInHashtag = wordsInHashtag;
    }

    public String getWid()
    {
        return wid;
    }

    public void setWid(String wid)
    {
        this.wid = wid;
    }

    public String getContent ( )
    {
		return content;
	}

	public void setContent ( String content ) 
	{
		this.content = content;
	}

	public Map<String, Integer> getWordcount ( ) 
	{
		return wordcount;
	}

	public void setWordcount ( Map<String, Integer> wordcount ) 
	{
		this.wordcount = wordcount;
	}

	public Integer getCountInteger ( ) 
	{
		return countInteger;
	}

	public void setCountInteger ( Integer countInteger )
	{
		this.countInteger = countInteger;
	}

	public void setWordsList ( List<String> wordsList )
	{
		this.wordsList = wordsList;
	}

	public String getTimetag()
    {
        return timetag;
    }

    public void setTimetag(String timetag)
    {
        this.timetag = timetag;
    }

    /**
	 * 词语添加
	 * 
	 * @param w
	 */
	public void addword ( String w ) 
	{

		if ( !wordsList.contains ( w ) ) 
		{
			wordsList.add ( w );
		}
	}

	/**
	 * 词语个数统计
	 * 
	 * @param w
	 */
	public void putword ( String w ) 
	{

		if ( wordcount.containsKey ( w ) ) 
		{
			wordcount.put ( w , wordcount.get ( w ) + 1 );
		}
		else 
		{
			wordcount.put ( w , 1 );
		}
	}
}
