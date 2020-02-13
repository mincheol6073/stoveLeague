package com.ssafy.edu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scala.collection.Seq;

@Getter
@Setter
@NoArgsConstructor
public class TfKeyword {
	
	Map<String,Integer> hashMap = new HashMap<String,Integer>();
	
	public void init() {
		hashMap.clear();
	}
	
//	static public class KeywordValue implements Comparable<KeywordValue>{
//	    public String keyword;
//	    public int value;
//	    
//	    public KeywordValue(String keyword, int value) {
//	        this.keyword = keyword;
//	        this.value = value;
//	    }
//	    public int compareTo(KeywordValue o) {
//	        return o.value-this.value;
//	    }
//	    @Override
//	    public String toString() {
//	        return "KeywordValue [keyword=" + keyword + ", value=" + value + "]";
//	    }
//	}
	
	public void addTf(String text) {
		CharSequence normalized = TwitterKoreanProcessorJava.normalize(text);
        Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
        Seq<KoreanTokenizer.KoreanToken> stemmed = TwitterKoreanProcessorJava.stem(tokens);
        LinkedList tkp = (LinkedList) TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(stemmed);
        
        for (int i = 0; i < tkp.size(); i++) {
        	int slice = tkp.get(i).toString().indexOf("(");
        	String voca = tkp.get(i).toString().substring(0,slice);
        	String tag = tkp.get(i).toString().substring(slice);
        	if(tag.contains("Noun")) {
        		if(hashMap.containsKey(voca)) {
        			int v = hashMap.get(voca)+1;
        			hashMap.remove(voca);
        			hashMap.put(voca, v);
        		}else hashMap.put(voca, 1);
        	}
        }
	}
    
    public List<KeywordValue> getTfRank(int end) {
    	if(hashMap.keySet().size() <= end) {
    		end = end-1;
    	}
    	List<KeywordValue> list = new ArrayList();
    	for(String key : hashMap.keySet()) {
    		Integer v = hashMap.get(key);
    		list.add(new KeywordValue(key, v));
    	}
    	Collections.sort(list);    	
    	
    	List<KeywordValue> ret = new ArrayList();
    	for(int i = 0 ; i < end ; i++) {
    		ret.add(list.get(i));
    	}
        return ret;
    }
}
