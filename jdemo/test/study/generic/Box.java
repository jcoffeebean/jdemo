/**
 * 泛型测试
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.generic;

public class Box<T> {

	private T data;
	
	private Box(){
	}
	
	public Box(T data) {
		setData(data);
	}

	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	
}
