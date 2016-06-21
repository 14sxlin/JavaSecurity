package PBE;

import static org.junit.Assert.*;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

public class TestPBECoder {

	@Test
	public void test() {
		String inputStr = "PBE";
		System.out.println("原文: "+inputStr);
		String pwd = "sparrowxin@sian.cn";
		System.out.println("密码 : "+pwd);
		byte[] salt = PBECoder.intiSalt();
		System.out.println("盐 : "+Base64.toBase64String(salt));
		byte[] data = PBECoder.encrypt(inputStr.getBytes(), pwd, salt);
		System.out.println("加密后: "+Base64.toBase64String(data));
		byte[] output = PBECoder.decrypt(data, pwd, salt);
		System.out.println("解密后: "+new String(output));
		assertEquals(inputStr, new String(output));
	}

}
