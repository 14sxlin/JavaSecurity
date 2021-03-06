package IDEA;

import static org.junit.Assert.assertEquals;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestIDEA {

	@Test
	public void test() {
		byte[] key = IDEACoder.intitKey();
		String data = "写完这个就去打篮球";
		byte[] input = IDEACoder.encrypt(key, data.getBytes());
		System.out.println("原文是: "+data);
		System.out.println("密钥是: "+Base64.toBase64String(key));
		System.out.println("加密后: "+Base64.toBase64String(input));
		byte[] output = IDEACoder.decrypt(key, input);
		System.out.println("解密后: "+new String(output));
		assertEquals(data, new String(output));
	}

}
