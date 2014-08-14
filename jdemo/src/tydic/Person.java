package tydic;
public class Person {
	private String p_name;
	private int p_age;
	
	public void setP_name1(String p_name) {
		this.p_name = p_name;
	}
	public void setAge(int age) {
		p_age = age;
	}
	
	public String getP_name1() {
		return this.p_name;
	}
	
	public int getAge() {
		return p_age;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
	}
}
