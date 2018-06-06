package com.weibo.hotspot.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Word {

	String word = "";
	
	double df=0;  //文档频率；
	
	double  subjectEntropy=0;   // 主体词熵

	Integer countInteger = 0;// 单词的总个数
	
	
	   // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount = new HashMap<String, Integer> ( );
	/**一天24小时
	 * 分时间段统计
	 */
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount0 = new HashMap<String, Integer> ( ); 
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount1 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount2 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount3 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount4 = new HashMap<String, Integer> ( );
    
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount5 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount6 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount7 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount8 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount9 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount10 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount11 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount12 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount13 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount14 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount15 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount16 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount17 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount18 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount19 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount20 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount21 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount22 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount23 = new HashMap<String, Integer> ( );
    // 词语在文本中出现个数.  key : 文本wid ;   value: 1  ;
    Map<String, Integer> textcount24 = new HashMap<String, Integer> ( );
	
	
	
	
    public double getDf()
    {
        return df;
    }
    public void setDf(double df)
    {
        this.df = df;
    }
    public double getSubjectEntropy()
    {
        return subjectEntropy;
    }
    public void setSubjectEntropy(double subjectEntropy)
    {
        this.subjectEntropy = subjectEntropy;
    }
    public Integer getCountInteger()
    {
        return countInteger;
    }
    public void setCountInteger(Integer countInteger)
    {
        this.countInteger = countInteger;
    }
    public String getWord()
    {
        return word;
    }
    public void setWord(String word)
    {
        this.word = word;
    }
    public Map<String, Integer> getTextcount()
    {
        return textcount;
    }
    public void setTextcount(Map<String, Integer> textcount)
    {
        this.textcount = textcount;
    }
    
    public Map<String, Integer> getTextcount0()
    {
        return textcount0;
    }
    public void setTextcount0(Map<String, Integer> textcount0)
    {
        this.textcount0 = textcount0;
    }
    public Map<String, Integer> getTextcount1()
    {
        return textcount1;
    }
    public void setTextcount1(Map<String, Integer> textcount1)
    {
        this.textcount1 = textcount1;
    }
    public Map<String, Integer> getTextcount2()
    {
        return textcount2;
    }
    public void setTextcount2(Map<String, Integer> textcount2)
    {
        this.textcount2 = textcount2;
    }
    public Map<String, Integer> getTextcount3()
    {
        return textcount3;
    }
    public void setTextcount3(Map<String, Integer> textcount3)
    {
        this.textcount3 = textcount3;
    }
    public Map<String, Integer> getTextcount4()
    {
        return textcount4;
    }
    public void setTextcount4(Map<String, Integer> textcount4)
    {
        this.textcount4 = textcount4;
    }
    public Map<String, Integer> getTextcount5()
    {
        return textcount5;
    }
    public void setTextcount5(Map<String, Integer> textcount5)
    {
        this.textcount5 = textcount5;
    }
    public Map<String, Integer> getTextcount6()
    {
        return textcount6;
    }
    public void setTextcount6(Map<String, Integer> textcount6)
    {
        this.textcount6 = textcount6;
    }
    public Map<String, Integer> getTextcount7()
    {
        return textcount7;
    }
    public void setTextcount7(Map<String, Integer> textcount7)
    {
        this.textcount7 = textcount7;
    }
    public Map<String, Integer> getTextcount8()
    {
        return textcount8;
    }
    public void setTextcount8(Map<String, Integer> textcount8)
    {
        this.textcount8 = textcount8;
    }
    public Map<String, Integer> getTextcount9()
    {
        return textcount9;
    }
    public void setTextcount9(Map<String, Integer> textcount9)
    {
        this.textcount9 = textcount9;
    }
    public Map<String, Integer> getTextcount10()
    {
        return textcount10;
    }
    public void setTextcount10(Map<String, Integer> textcount10)
    {
        this.textcount10 = textcount10;
    }
    public Map<String, Integer> getTextcount11()
    {
        return textcount11;
    }
    public void setTextcount11(Map<String, Integer> textcount11)
    {
        this.textcount11 = textcount11;
    }
    public Map<String, Integer> getTextcount12()
    {
        return textcount12;
    }
    public void setTextcount12(Map<String, Integer> textcount12)
    {
        this.textcount12 = textcount12;
    }
    public Map<String, Integer> getTextcount13()
    {
        return textcount13;
    }
    public void setTextcount13(Map<String, Integer> textcount13)
    {
        this.textcount13 = textcount13;
    }
    public Map<String, Integer> getTextcount14()
    {
        return textcount14;
    }
    public void setTextcount14(Map<String, Integer> textcount14)
    {
        this.textcount14 = textcount14;
    }
    public Map<String, Integer> getTextcount15()
    {
        return textcount15;
    }
    public void setTextcount15(Map<String, Integer> textcount15)
    {
        this.textcount15 = textcount15;
    }
    public Map<String, Integer> getTextcount16()
    {
        return textcount16;
    }
    public void setTextcount16(Map<String, Integer> textcount16)
    {
        this.textcount16 = textcount16;
    }
    public Map<String, Integer> getTextcount17()
    {
        return textcount17;
    }
    public void setTextcount17(Map<String, Integer> textcount17)
    {
        this.textcount17 = textcount17;
    }
    public Map<String, Integer> getTextcount18()
    {
        return textcount18;
    }
    public void setTextcount18(Map<String, Integer> textcount18)
    {
        this.textcount18 = textcount18;
    }
    public Map<String, Integer> getTextcount19()
    {
        return textcount19;
    }
    public void setTextcount19(Map<String, Integer> textcount19)
    {
        this.textcount19 = textcount19;
    }
    public Map<String, Integer> getTextcount20()
    {
        return textcount20;
    }
    public void setTextcount20(Map<String, Integer> textcount20)
    {
        this.textcount20 = textcount20;
    }
    public Map<String, Integer> getTextcount21()
    {
        return textcount21;
    }
    public void setTextcount21(Map<String, Integer> textcount21)
    {
        this.textcount21 = textcount21;
    }
    public Map<String, Integer> getTextcount22()
    {
        return textcount22;
    }
    public void setTextcount22(Map<String, Integer> textcount22)
    {
        this.textcount22 = textcount22;
    }
    public Map<String, Integer> getTextcount23()
    {
        return textcount23;
    }
    public void setTextcount23(Map<String, Integer> textcount23)
    {
        this.textcount23 = textcount23;
    }
    public Map<String, Integer> getTextcount24()
    {
        return textcount24;
    }
    public void setTextcount24(Map<String, Integer> textcount24)
    {
        this.textcount24 = textcount24;
    }
    /**
     * 词语个数统计
     * 
     * @param w
     */
    public void put ( String word ) 
    {

        if ( textcount.containsKey (word ) ) 
        {
            textcount.put ( word , textcount.get ( word ) + 1 );
        }
        else 
        {
            textcount.put ( word , 1 );
        }
    }
	/**
	 * 词语个数统计
	 * 
	 * @param w
	 */
	public void put ( String wid ,String timetag)
	{
	    if (StringUtils.isBlank(timetag))
        {
            return;
        }
	    
	    switch (Integer.parseInt(timetag))
        {
            case 0:
                this.textcount0.put(wid, 1);
                
                break;
            case 1:
                this.textcount1.put(wid, 1);
                break;
            case 2:
                this.textcount2.put(wid, 1);
                break;
            case 3:
                this.textcount3.put(wid, 1);
                break;
            case 4:
                this.textcount4.put(wid, 1);
                break;
            case 5:
                this.textcount5.put(wid, 1);
                break;
            case 6:
                this.textcount5.put(wid, 1);
                break;
            case 7:
                this.textcount7.put(wid, 1);
                break;
            case 8:
                this.textcount8.put(wid, 1);
                break;
            case 9:
                this.textcount9.put(wid, 1);
                break;
            case 10:
                this.textcount10.put(wid, 1);
                break;
            case 11:
                this.textcount11.put(wid, 1);
                break;
            case 12:
                this.textcount12.put(wid, 1);
                break;
            case 13:
                this.textcount13.put(wid, 1);
                break;
            case 14:
                this.textcount14.put(wid, 1);
                break;
            case 15:
                this.textcount15.put(wid, 1);
                break;
            case 16:
                this.textcount16.put(wid, 1);
                break;
            case 17:
                this.textcount17.put(wid, 1);
                break;
              case 18:
                  this.textcount18.put(wid, 1);        
                 break;

              case 19:
                  this.textcount19.put(wid, 1);
                  break;
              case 20:
                  this.textcount20.put(wid, 1);
                  break;
              case 21:
                  this.textcount21.put(wid, 1);
                  break;
              case 22:
                  this.textcount22.put(wid, 1);
                  break;
              case 23:
                  this.textcount23.put(wid, 1);
                  break;
              case 24:
                  this.textcount24.put(wid, 1);
                  break;    

            default:
                break;
        }

		
	}

	
	
}
