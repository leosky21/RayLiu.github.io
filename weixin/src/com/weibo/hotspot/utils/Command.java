package com.weibo.hotspot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 概要说明 : 命令行调用方法  <br>
 * 详细说明 : TODO ADD Instruction.  <br>
 * 创建时间 : 2016年11月17日 下午4:17:27 <br>
 * @author  by jiangj
 */
public class Command
{

    public static void exeCmd(String[] commandstr)
    {
        BufferedReader br=null;
        try
        {
            Process p=Runtime.getRuntime().exec(commandstr);
            br= new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line=null;
            StringBuffer sb=new StringBuffer();
            while (null!=(line=br.readLine()))
            {
                sb.append(line+"\n");
            }
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        finally 
        {
            if(br!=null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                      
                    e.printStackTrace();  
                    
                }
            }
        }
    }
}
