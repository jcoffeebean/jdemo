package study.test;

import java.io.File;
import java.io.IOException;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File localFile = new File("e:/1.txt");
		File renameFile = new File("e:/2.txt");
		
		localFile.renameTo(renameFile);
		
		try {
			localFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
