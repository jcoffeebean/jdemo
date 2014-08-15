/**
 * @author taowei20061122@163.com 
 * @createtime 2013-10-23
 * @version V1.0
 */
package study.entity;

public class QQ {
	
	private String xm;
	private String sfzh;
	private String qq;
	
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public String toString() {
		return "[xm:" + xm + ", sfzh:" + sfzh + ", qq:" + qq + "]";
	}
}
