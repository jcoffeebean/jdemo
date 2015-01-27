/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月27日
 * @version V1.0
 */
package study.generic;

public class GenericTest {

	public static void main(String[] args) {
		Box<Integer> iBox = new Box<Integer>(1);
//		Box<Number> nBox = iBox; // error
		
		Box<Float> fBox = new Box<Float>(0.1f);
//		nBox.setData(fBox); // error
		getData(iBox);
		getData(fBox);
		
		Box<String> sBox = new Box<String>("string 123");
		getData(sBox);
//		getUpperNumberData(sBox);
		getUpperNumberData(iBox);
		getUpperNumberData(fBox);
		
	}

//	public static void getData(Box<Number> data) {
//		System.out.println("data: " + data.getData());
//	}
	
	public static void getData(Box<?> data) {
		System.out.println("data: " + data.getData());
	}
	
	public static void getUpperNumberData(Box<? extends Number> data) {
		System.out.println("data: " + data.getData());
	}
}
