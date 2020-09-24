package test.czy;
import java.text.DecimalFormat;
import java.util.*;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.Word;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;

public class compare {

    public static Map<String,int[]> getFrequency(String text1, String text2){   //分词、并计算词频
        Map<String,int[]> wordMap = new HashMap<>();
        TokenizerEngine engine = new HanLPEngine();

        Result textList1 = engine.parse(text1);
        Result textList2 = engine.parse(text2);


        Iterator<Word> iterator1 = textList1.iterator();
        Iterator<Word> iterator2 = textList2.iterator();

        while (iterator1.hasNext()){
            String word = iterator1.next().toString();
            String afterWord = "";

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                //只判断中文
                if (String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
                    afterWord += c;
                }

            }

            if (wordMap.containsKey(afterWord)){
                wordMap.get(afterWord)[0] ++;
            } else {
                int[] sum = new int[2];
                sum[0] = 1;
                wordMap.put(afterWord,sum);

            }
        }

        while (iterator2.hasNext()){
            String word = iterator2.next().toString();
            String afterWord = "";

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                //只判断中文
                if (String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
                    afterWord += c;
                }

            }

            if (wordMap.containsKey(afterWord)){
                wordMap.get(afterWord)[1] ++;
            } else {
                int[] sum = new int[2];
                sum[1] = 1;
                wordMap.put(afterWord,sum);

            }
        }

        return wordMap;

    }



    public static double Similary(Map<String,int[]> map){   //利用余弦定理计算出查重率

        double molecule=0;
        double denominator;
        double temp1 = 0;
        double temp2 = 0;
        Set set = map.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()){
            Map.Entry<String,int[]> entry = (Map.Entry<String, int[]>) i.next();
            molecule += (entry.getValue()[0] * entry.getValue()[1]);
            temp1 += entry.getValue()[0] * entry.getValue()[0];
            temp2 += entry.getValue()[1] * entry.getValue()[1];
        }
        denominator = Math.sqrt(temp1 * temp2);
        double result = molecule/denominator;
        //将结果保留2位小数
        DecimalFormat df = new DecimalFormat("#.00");

        return Double.parseDouble(df.format(result));

    }

}

