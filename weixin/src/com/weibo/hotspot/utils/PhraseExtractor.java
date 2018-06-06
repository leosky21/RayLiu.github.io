package com.weibo.hotspot.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.occurrence.Occurrence;
import com.hankcs.hanlp.corpus.occurrence.PairFrequency;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.weibo.jg.bean.Wbinfor;

import java.util.LinkedList;
import java.util.List;

/**
 * 利用互信息的短语提取器 互信息体现了两个变量之间的相互依赖程度。二元互信息是指两个事件相关性的量（论文给出如下定义）
 * 
 * @author
 */
public class PhraseExtractor
{
    public static List<String> extractPhrase(List<Wbinfor> wbinfos)
    {

        String text = "";

        for (Wbinfor wbinfo : wbinfos)
        {
            text += wbinfo.getInfortext() + "\n";
        }
        List<String> topic = getMutuallnformation(text, 4);

        return topic;
    }

    public static List<String> getMutuallnformation(String text, int size)
    {
        List<String> phraseList = new LinkedList<String>();
        Occurrence occurrence = new Occurrence();
        Filter[] filterChain = new Filter[] { CoreStopWordDictionary.FILTER,
                new Filter()
                {
                    @Override
                    public boolean shouldInclude(Term term)
                    {
                        switch (term.nature)
                        {
                            case t:
                            case nx:
                                return false;
                            default:
                                break;
                        }
                        return true;
                    }
                } };
        for (List<Term> sentence : NotionalTokenizer.seg2sentence(text,
                filterChain))
        {
            if (HanLP.Config.DEBUG)
            {
                System.out.println(sentence);
            }
            occurrence.addAll(sentence);
        }
        occurrence.compute();
        if (HanLP.Config.DEBUG)
        {
            System.out.println(occurrence);
            for (PairFrequency phrase : occurrence.getPhraseByMi())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→')
                        + "\tmi=" + phrase.mi + " , ");
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByLe())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→')
                        + "\tle=" + phrase.le + " , ");
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByRe())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→')
                        + "\tre=" + phrase.re + " , ");
            }
            System.out.println();
            for (PairFrequency phrase : occurrence.getPhraseByScore())
            {
                System.out.print(phrase.getKey().replace(Occurrence.RIGHT, '→')
                        + "\tscore=" + phrase.score + " , ");
            }
            System.out.println();
        }

        for (PairFrequency phrase : occurrence.getPhraseByScore())
        {
            if (phraseList.size() == size)
                break;
            phraseList.add(phrase.first + phrase.second);
        }
        return phraseList;
    }

}
