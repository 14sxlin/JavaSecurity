package IDEA;

import static org.junit.Assert.assertEquals;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestIDEA {

	@Test
	public void test() {
		byte[] key = IDEACoder.intitKey();
		String data = "д�������ȥ������";
		byte[] input = IDEACoder.encrypt(key, data.getBytes());
		System.out.println("ԭ����: "+data);
		System.out.println("��Կ��: "+Base64.toBase64String(key));
		System.out.println("���ܺ�: "+Base64.toBase64String(input));
		byte[] output = IDEACoder.decrypt(key, input);
		System.out.println("���ܺ�: "+new String(output));
		assertEquals(data, new String(output));
	}

}
