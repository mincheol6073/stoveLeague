package tfidf;

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
