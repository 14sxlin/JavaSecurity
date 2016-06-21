package PBE;

import static org.junit.Assert.*;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestPBECoder {

	@Test
	public void test() {
		String inputStr = "PBE";
		System.out.println("ԭ��: "+inputStr);
		String pwd = "sparrowxin@sian.cn";
		System.out.println("���� : "+pwd);
		byte[] salt = PBECoder.intiSalt();
		System.out.println("�� : "+Base64.toBase64String(salt));
		byte[] data = PBECoder.encrypt(inputStr.getBytes(), pwd, salt);
		System.out.println("���ܺ�: "+Base64.toBase64String(data));
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		System.out.println("���ܺ�: "+new String(output));
		assertEquals(inputStr, new String(output));
	}

}
