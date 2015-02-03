/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.enumtest;

public enum PersonMapping2 implements MappingEnum {
	
	PERSONID("INTEGER", true, false), 
	FIRSTNAME("TEXT", false, false), 
	LASTNAME("TEXT", false, false), 
	AGE("INTEGER", false, false);
	
	MappingEnum mappingEnum = null;
	
	private PersonMapping2(String type, boolean key, boolean nullable) {
		mappingEnum = new MappingEnumImpl(type, key, nullable);
	}

	public String getType() {
		return mappingEnum.getType();
	}

	public boolean isKey() {
		return mappingEnum.isKey();
	}

	public boolean isNullable() {
		return mappingEnum.isNullable();
	}
	
}
