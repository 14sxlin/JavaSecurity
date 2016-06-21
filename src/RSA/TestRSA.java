package RSA;

import static org.junit.Assert.*;

import java.util.Map;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;

public class TestRSA {

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
	public void test() {
		System.out.println("\n-----------私钥加密  公钥解密------------\n");
		String input1 = "加密算法";
		System.out.println("原文 : "+input1);
		byte[] data1 = 
				RSACoder.encryptByPrivateKey(input1.getBytes(), privateKey);
		System.out.println("加密后的数据: "+Hex.toHexString(data1));
		data1 = RSACoder.decrypyByPublicKey(data1, publicKey);
		System.out.println("解密后的数据: "+new String(data1));
		assertEquals(input1, new String(data1));
	}

}
