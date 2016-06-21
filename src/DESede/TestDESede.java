package DESede;

import static org.junit.Assert.assertEquals;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestDESede {

	@Test
	public void test() {
		byte[] key = DESede.intitKey();
		String data = "д�������ȥ������";
		byte[] input = DESede.encrypt(key, data.getBytes());
		System.out.println("ԭ����: "+data);
		System.out.println("��Կ��: "+Base64.toBase64String(key));
		System.out.println("���ܺ�: "+Base64.toBase64String(input));
		byte[] output = DESede.decrypt(key, input);
		System.out.println("���ܺ�: "+new String(output));
		assertEquals(data, new String(output));
	}

}
