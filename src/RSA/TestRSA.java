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
			System.out.println("��Կ: "+Base64.toBase64String(publicKey));
			System.out.println("˽Կ: "+Base64.toBase64String(privateKey));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test() {
		System.out.println("\n-----------˽Կ����  ��Կ����------------\n");
		String input1 = "�����㷨";
		System.out.println("ԭ�� : "+input1);
		byte[] data1 = 
				RSACoder.encryptByPrivateKey(input1.getBytes(), privateKey);
		System.out.println("���ܺ������: "+Hex.toHexString(data1));
		data1 = RSACoder.decrypyByPublicKey(data1, publicKey);
		System.out.println("���ܺ������: "+new String(data1));
		assertEquals(input1, new String(data1));
	}

}
