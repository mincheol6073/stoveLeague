package com.websocket.chat.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import scala.collection.*;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

public class TfKeyword {

    public double tf(ArrayList<String> doc, String term) {
        double result = 0;
        for (int i=0; i<doc.size(); i++) {
            String word = (String) doc.get(i);
            System.out.println("word>>>"+word);
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    public class KeywordValue implements Comparable<KeywordValue>{
        public String keyword;
        public double value;

        public KeywordValue(String keyword, double value) {
            this.keyword = keyword;
            this.value = value;
        }

        public String getKeyword() {
            return keyword;
        }
        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
        public double getValue() {
            return value;
        }
        public void setValue(double value) {
            this.value = value;
        }
        @Override
        public int compareTo(KeywordValue o) {
            return (int)((o.value*100000000)-(this.value*100000000));
        }
        @Override
        public String toString() {
            return "KeywordValue [keyword=" + keyword + ", value=" + value + "]";
        }
    }


    public List<KeywordValue> tfRanking(String text) {
        TfKeyword jtkte = new TfKeyword();
        CharSequence normalized = TwitterKoreanProcessorJava.normalize(text);
        Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
        Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
        LinkedList tkp = (LinkedList) TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
        HashSet<String> set= new HashSet<String>();
        ArrayList<String> stemnormalizeList = new ArrayList<String>();
        for (int i = 0; i < tkp.size(); i++) {
            if(tkp.get(i).toString().contains("(Noun:")) {
                int slice = tkp.get(i).toString().indexOf("(Noun:");
                set.add(tkp.get(i).toString().substring(0,slice));
            }
            int slice = tkp.get(i).toString().indexOf("(");
            stemnormalizeList.add(tkp.get(i).toString().substring(0,slice));
        }


        String[] textArr = new String[set.size()];
        for(int i=0; i<set.size(); i++) {
            textArr[i] = (String) set.toArray()[i];
        }

        ArrayList<KeywordValue> returnList = new ArrayList<KeywordValue>();

        for(String word : textArr) {
            System.out.println(word);
            returnList.add(new KeywordValue(word, jtkte.tf(stemnormalizeList, word)));
        }
        Collections.sort(returnList);;
        System.out.println(returnList);

        return returnList;
    }


    public static void main(String[] args) {
        TfKeyword jtkte = new TfKeyword();
        jtkte.tfRanking("를 처리하는 예시입니닼ㅋㅋㅋㅋㅋ 한국어 예시 한국어 한국어 한국어 한국어 한국 #한국어");
    }
}

