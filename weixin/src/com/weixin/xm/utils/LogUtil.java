package com.weixin.xm.utils;



import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * 
 *[概要设计：]LogUtil
 *[详细设计：]日志工具类
 *@author lj 2016-8-25
 * 
 */
public class LogUtil
{

  //日志配置文件
  private static final String LOGCONFIG = "log4j.properties";
  private static Logger objLog;
  
  private static Logger getLogger() 
  {
    if(objLog==null)
    {
      objLog = LogManager.getLogger(LogUtil.class);
    }
    return objLog;
  }
  
  @SuppressWarnings("unused")
private static String getConfigFile()
  {
    String s = LogUtil.class.getClassLoader().getResource("").toString();
    String filePath = s + LOGCONFIG;
    return filePath;
  }
  
  	/**
	 *[概要设计：]记录一条信息
	 *[详细设计：]
	 *@param message 信息
	 *@param exception 异常
	 *@author lj 2016-8-25
	 */
  public static void info(String message, Exception exception) 
  {
	    try 
	    {
	      log("INFO", message, exception);
	    } 
	    catch (Exception ex) 
	    {
	    	
	    }
  }

  	/**
	 *[概要设计：]记录一条信息
	 *[详细设计：]
	 *@param message 信息
	 *@author lj 2016-8-25
	 */
  public static void info(Object message)
  {
    log("INFO", message);
  }
  	/**
	 *[概要设计：]跟踪信息
	 *[详细设计：]
	 *@param message 信息
	 *@param exception 异常
	 *@author lj 2016-8-25
	 */ 
  public static void trace(String message)
  {
    try 
    {
      log("TRACE", message);
    } 
    catch (Exception ex)
    {
    }
  }
	/**
	 *[概要设计：]跟踪信息
	 *[详细设计：]
	 *@param message 信息
	 *@author lj 2016-8-25
	 */ 
  public static void trace(String message, Exception exception) 
  {
    try 
    {
      log("TRACE", message, exception);
    } 
    catch (Exception ex) 
    {
    }
  }

/**
 *[概要设计：]记录一条错误信息
 *[详细设计：]
 *@param message 信息
 *@param exception 异常
 *@author lj 2016-8-25
 */ 
  public static void error(String message, Exception exception)
  {
    try 
    {
      log("ERROR", message, exception);
    } 
    catch (Exception ex) 
    {

    }
  }

  /**
   *[概要设计：]记录一条错误信息
   *[详细设计：]
   *@param message 信息
   *@author lj 2016-8-25
   */ 
  public static void error(String message) 
  {
    try 
    {
      log("ERROR", message);
    }
    catch (Exception ex) 
    {

    }
  }

  /**
   *[概要设计：]记录一条警告信息
   *[详细设计：]
   *@param message 信息
   *@param exception 异常
   *@author lj 2016-8-25
   */ 
  public static void warning(String message, Exception exception)
  {
    try 
    {
      log("WARN", message, exception);
    } 
    catch (Exception ex) 
    {

    }
  }

  /**
   *[概要设计：]记录一条警告信息
   *[详细设计：]
   *@param message 信息
   *@author lj 2016-8-25
   */ 
  public static void warning(String message)
  {
    try
    {
      log("WARN", message);
    } 
    catch (Exception ex) 
    {

    }
  }

  /**
   *[概要设计：]记录一条致命错误信息
   *[详细设计：]
   *@param message 信息
   *@param exception 异常
   *@author lj 2016-8-25
   */ 
  public static void fatal(String message, Exception exception) 
  {
    try 
    {
      log("FATAL", message, exception);
    } 
    catch (Exception ex)
    {

    }
  }

  /**
   *[概要设计：]记录一条致命错误信息
   *[详细设计：]
   *@param message 信息
   *@author lj 2016-8-25
   */ 
  public static void fatal(String message)
  {
    try 
    {
      log("FATAL", message);
    } catch (Exception ex)
    {

    }
  }

  /**
   *[概要设计：]记录一条调试信息
   *[详细设计：]
   *@param message 信息
   *@param exception 异常
   *@author lj 2016-8-25
   */ 
  public static void debug(String message, Exception exception)
  {
    try 
    {
      log("DEBUG", message, exception);
    } catch (Exception ex) 
    {

    }
  }

  /**
   *[概要设计：]记录一条调试信息
   *[详细设计：]
   *@param message 信息
   *@author lj 2016-8-25
   */ 
  public static void debug(String message) 
  {
    try 
    {
      log("DEBUG", message);
    } 
    catch (Exception ex)
    {

    }
  }
  
  /**
   *[概要设计：]记录日志
   *[详细设计：]
   *@param level 信息级别
   *@param msg 信息对象
   *@author lj 2016-8-25
   */ 
  public static void log(String level, Object msg)
  {
    log(level, msg, null);
  }
  /**
   *[概要设计：]记录日志
   *[详细设计：]
   *@param level 信息级别
   *@param e 异常信息
   *@author lj 2016-8-25
   */ 
  public static void log(String level, Throwable e)
  {
    log(level, null, e);
  }
  /**
   *[概要设计：]记录日志
   *[详细设计：]
   *@param level 信息级别
   *@param msg 信息对象
   *@param e 异常信息
   *@author lj 2016-8-25
   */ 
  public static void log(String level, Object msg, Throwable e)
  {
    try
    {
      StringBuilder sb = new StringBuilder();
      Throwable t = new Throwable();
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      String input = sw.getBuffer().toString();
      StringReader sr = new StringReader(input);
      BufferedReader br = new BufferedReader(sr);
      for(int i=0;i<4;i++)
        br.readLine(); 
      String line = br.readLine();
      //at com.media.web.action.DicManageAction.list(DicManageAction.java:89)
      int paren = line.indexOf("at ");
      line = line.substring(paren+3);
      paren = line.indexOf('(');
      String invokeInfo = line.substring(0, paren);
      int period = invokeInfo.lastIndexOf('.');
      sb.append('[');
      sb.append(invokeInfo.substring(0,period));
      sb.append(':');
      sb.append(invokeInfo.substring(period+1));
      sb.append("():");
      paren = line.indexOf(':');
      period = line.lastIndexOf(')');
      sb.append(line.substring(paren+1,period));
      sb.append(']');
      sb.append(" - ");
      sb.append(msg);
      getLogger().log((Priority) Level.toLevel(level), sb.toString(), e);
    }
    catch(Exception ex)
    {
    	com.weixin.xm.utils.LogUtil.info(ex.getLocalizedMessage());
    }
  }
}
