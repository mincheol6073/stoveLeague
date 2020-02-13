package com.ssafy.edu.model.response;

import java.util.*;

import com.ssafy.edu.model.KeywordValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserKeywordValue {
	
	private String userId;
	private List<KeywordValue> keyList;
}
