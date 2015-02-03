/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.enumtest;
/**
 *    
 *    枚举类型可以存储附加的数据和方法
 *    枚举类型可通过接口来定义行为
 *    枚举类型的项行为可通过接口来访问，跟正常的 Java 类无异
 *    values() 方法可用来返回接口中的一个数组
 * 
 */
public class EnumTest {


	public static String dbTableCreate(String tableName) {
		StringBuilder builder = new StringBuilder("Create table ");
		builder.append(tableName);
		builder.append("(");
		for (PersonMapping column : PersonMapping.values()) {
			builder.append(column.name()); //枚举类型默认继承抽象类java.lang.Enum
			builder.append(" ");
			builder.append(column.getType());
			builder.append(column.isKey() ? " primary key" : "");
			builder.append(", ");
		}
		builder = new StringBuilder(builder.substring(0, builder.length() - 2));
		builder.append(");");
		return builder.toString();
	}

	public static String dbTableCreate(String tableName, MappingEnum[] values) {
		StringBuilder builder = new StringBuilder("Create table ");
		builder.append(tableName);
		builder.append("(");
		for (MappingEnum column : values) {
			builder.append(column.name());
			builder.append(" ");
			builder.append(column.getType());
			builder.append(column.isKey() ? " primary key" : "");
			builder.append(", ");
		}
		builder = new StringBuilder(builder.substring(0, builder.length() - 2));
		builder.append(");");
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(dbTableCreate("T1"));
		System.out.println(dbTableCreate("T1", PersonMapping.values()));
		System.out.println(dbTableCreate("T2", PersonMapping2.values()));
		System.out.println(dbTableCreate("T3", PersonMapping3.values()));
	}

}
