/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月28日
 * @version V1.0
 */
package study.enumtest;

public class MappingEnumImpl implements MappingEnum {
	
	private String type;
	private boolean key;
	private boolean nullable;
	
	public MappingEnumImpl(String type, boolean key, boolean nullable) {
		this.type = type;
		this.key = key;
		this.nullable = nullable;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean isKey() {
		return key;
	}

	@Override
	public boolean isNullable() {
		return nullable;
	}
	
	@Override
	public String name() {
		return name();
	}

}
