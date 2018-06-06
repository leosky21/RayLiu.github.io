package com.weibo.hotspot.bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//加载停用词、停用动词表、新浪专有词
public class LoadStopWord
{
    private String classpath = this.getClass().getResource("/").getPath();
    public static List<String> stopword = new ArrayList<String> ( ); // 停用词表
    public static List<String> stopverd = new ArrayList<String> ( ); // 停用动词表
    public static List<String> sina = new ArrayList<String> ( ); // 新浪专有词
    
    static LoadStopWord STOPword=new LoadStopWord();
    
    public LoadStopWord() 
    {
        stopwordRead();
    }
    
    public static  LoadStopWord  getInstrance()
    {
        return STOPword;
    }
    
    public List<String> getStopword()
    {
        return stopword;
    }

    public void setStopword(List<String> stopword)
    {
        LoadStopWord.stopword = stopword;
    }

    public List<String> getStopverd()
    {
        return stopverd;
    }


    public void setStopverd(List<String> stopverd)
    {
        LoadStopWord.stopverd = stopverd;
    }

    public List<String> getSina()
    {
        return sina;
    }

    public void setSina(List<String> sina)
    {
        LoadStopWord.sina = sina;
    }

    /**
     * 停用词读取
     * 
     * @return
     * @throws IOException
     */
    public void stopwordRead ( ) 
    {
        /* 读入停用词 */
        String fileName = classpath + "files/stopword/stopword.txt";
        FileReader f;
        try
        {
            f = new FileReader ( fileName );
            BufferedReader br = new BufferedReader ( f );
            String line;
            // 处理文件
            /* 读取停用词 */
            try 
            {
                while ( ( line = br.readLine ( ) ) != null ) 
                {
                    stopword.add ( line );
                }

                /* 读入停用动词 */
                fileName = classpath + "files/stopword/stopVerd.txt";
                f = new FileReader ( fileName );
                br = new BufferedReader ( f );

                // 处理文件
                /* 读取停用词 */
                while ( ( line = br.readLine ( ) ) != null ) 
                {
                    stopverd.add ( line );
                }

                /* 读入新浪 */
                fileName = classpath + "files/stopword/sina.txt";
                f = new FileReader ( fileName );
                br = new BufferedReader ( f );

                // 处理文件
                /* 读取停用词 */
                while ( ( line = br.readLine ( ) ) != null )
                {
                    sina.add ( line );
                }

                br.close ( );
                f.close ( );

            }
            catch ( IOException e ) 
            {
                e.printStackTrace ( );
            }
        } 
        catch ( FileNotFoundException e1 ) 
        {
            e1.printStackTrace ( );
        }
    }
    
}
