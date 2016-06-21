package RSA_Signature;

import static org.junit.Assert.*;

import java.util.Map;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;

public class TestRSASignature {

	private byte[] publicKey;
	private byte[] privateKey;
	@Before
	public void initKey() throws Exception {
		Map<String, Object> keyMap;
		try {
			keyMap = RSACoder.initKey();
			publicKey = RSACoder.getPublicKey(keyMap);
			privateKey = RSACoder.getPrivateKey(keyMap);
			System.out.println("公钥: "+Base64.toBase64String(publicKey));
			System.out.println("私钥: "+Base64.toBase64String(privateKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSign() {
		String input = "加密算法";
		System.out.println("原文 : "+input);
		byte[] sign = 
				RSACoder.sign(input.getBytes(), privateKey);
		System.out.println("加密后的数据: "+Hex.toHexString(sign));
		boolean status = RSACoder.verify(input.getBytes(), publicKey, sign);
		System.out.println("验证结果 : "+status);
		assertEquals(true,status);
	}

}
