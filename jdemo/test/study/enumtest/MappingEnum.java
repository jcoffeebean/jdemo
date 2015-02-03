/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.enumtest;

public interface MappingEnum {
    public String getType();
    public boolean isKey();
    public boolean isNullable();
    public String name(); //java.lang.Enum抽象类中有此方法
}
