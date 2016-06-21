package DES;

import static org.junit.Assert.*;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestDESCoder {

	@Test
	public void test() {
		String  input = "DES初级加密算法";
		byte[] data = input.getBytes();
		System.out.println("原文 :"+input);
		byte[] key = DESCoder.genKey();
		System.out.println("密钥: "+Base64.toBase64String(key));
		data = DESCoder.encrypt(DESCoder.getKey(key), data);
		System.out.println("加密后: "+Base64.toBase64String(data));
		byte[] output = DESCoder.decrypt(DESCoder.getKey(key), data);
		String str = new String(output);
		System.out.println("解密后: "+str);
		assertEquals(input, str);

	}

}
