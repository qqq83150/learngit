package org.lw.rsa;

public class TestBase64 {

	public static void main(String[] args) throws Exception {
		String encode = Base64Utils.encode("@".getBytes());
		System.out.println(encode);
		byte[] decode = Base64Utils.decode(encode);
		System.out.println(new String(decode));
	}
}
