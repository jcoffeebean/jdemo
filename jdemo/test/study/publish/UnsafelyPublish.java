/**
 * 不安全的对象发布示例
 * @author taowei20061122@163.com 
 * @createtime 2014年11月6日
 * @version V1.0
 */
package study.publish;

public class UnsafelyPublish {
	
	private  int num;
	
	public UnsafelyPublish(int num) {
		this.num = num;
	}
	
	public void assertSanity() {
		if (num != num) {
			throw new AssertionError("失败的对象发布");
		}
	}
	

}
