package DES;

import static org.junit.Assert.*;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestDESCoder {

	@Test
	public void test() {
		String  input = "DES���������㷨";
		byte[] data = input.getBytes();
		System.out.println("ԭ�� :"+input);
		byte[] key = DESCoder.genKey();
		System.out.println("��Կ: "+Base64.toBase64String(key));
		data = DESCoder.encrypt(DESCoder.getKey(key), data);
		System.out.println("���ܺ�: "+Base64.toBase64String(data));
		byte[] output = DESCoder.decrypt(DESCoder.getKey(key), data);
		String str = new String(output);
		System.out.println("���ܺ�: "+str);
		assertEquals(input, str);

	}

}
