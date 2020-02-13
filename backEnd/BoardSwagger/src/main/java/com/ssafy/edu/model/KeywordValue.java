package com.ssafy.edu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KeywordValue implements Comparable<KeywordValue> {
	public String keyword;
    public int value;
    
    public KeywordValue(String keyword, int value) {
        this.keyword = keyword;
        this.value = value;
    }

	@Override
	 public int compareTo(KeywordValue o) {
        return o.value-this.value;
    }
}
