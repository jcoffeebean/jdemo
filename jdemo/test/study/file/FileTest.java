/**
 * @author taowei20061122@163.com 
 * @createtime 2015年2月3日
 * @version V1.0
 */
package study.file;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		File file = new File("d:/123"); // 不存在的路径
		System.out.println(file.getAbsolutePath()); // d:\123
		System.out.println(file.lastModified()); // 0
		System.out.println(file.isFile()); // false
		System.out.println(file.isDirectory()); // false
		System.out.println(file.exists()); // false
		System.out.println(new File("d:/logs").exists()); // true
	}
}
