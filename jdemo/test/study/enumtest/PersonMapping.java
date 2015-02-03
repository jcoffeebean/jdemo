/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.enumtest;

public enum PersonMapping implements MappingEnum {
	
	PERSONID("INTEGER", true, false), 
	FIRSTNAME("TEXT", false, false), 
	LASTNAME("TEXT", false, false), 
	AGE("INTEGER", false, false);
	
	private PersonMapping(String type, boolean key, boolean nullable) {
		this.type = type;
		this.key = key;
		this.nullable = nullable;
	}

	public String getType() {
		return type;
	}

	public boolean isKey() {
		return key;
	}

	public boolean isNullable() {
		return nullable;
	}

	private final String type;
	private final boolean key;
	private final boolean nullable;
	
}
