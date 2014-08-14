package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

	public Encrypt() {
	}

	public static String encode(String msg){
		return encode(msg, "MD5");
	}
	public static String encode(String msg, String method) {
		if (msg == null || method == null)
			return msg;
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance(method);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return msg;
		}
		algorithm.reset();
		byte buf[] = msg.getBytes();
		algorithm.update(buf);
		byte digest[] = algorithm.digest();
		StringBuffer retsult = new StringBuffer(0);
		for (int i = 0; i < digest.length; i++)
			retsult.append(Encrypt.toHexString(digest[i]));
		return retsult.toString();

	}

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String toHexString(byte b) {
		int i = 0, j = 0;
		i = (byte) ((b >>> 4) & 15);
		j = (byte) (b & 15);
		return new StringBuffer().append(digits[i]).append(digits[j])
				.toString();
	}

	public static void main(String args[]) {

		System.out.println(Encrypt.encode("111", "MD5"));
		System.out.println(Encrypt.encode("111"));
		System.out.println(Encrypt.encode("111", "SHA"));
		System.out.println(Encrypt.encode("111", "SHA-1"));
	}


}