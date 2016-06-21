package DH;

import static org.junit.Assert.*;

import java.util.Map;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;

public class TestDHCoder {

	//甲方公钥
	private byte[] publicKey1;
	//甲方密钥
	private byte[] privateKey1;
	 //甲方本地密钥
	private byte[] key1;
	//乙方公钥
	private byte[] publicKey2;
	//乙方密钥
	private byte[] privateKey2;
	//乙方本地密钥
	private byte[] key2;
	
	
	@Before
	public final void initKey() throws Exception {
		//生成甲方密钥对
		Map<String, Object> keyMap1 = DHCoder.initKey();
		publicKey1 = DHCoder.getPublicKey(keyMap1);
		privateKey1 = DHCoder.getPrivateKey(keyMap1);
		System.out.println("甲方公钥: "+Base64.toBase64String(publicKey1));
		System.out.println("甲方私钥: "+Base64.toBase64String(privateKey1));
		//由甲方公钥产生本地密钥 (乙方密钥)
		Map<String,Object>keyMap2 = DHCoder.initKey(publicKey1);
		publicKey2 = DHCoder.getPublicKey(keyMap2);
		privateKey2 = DHCoder.getPrivateKey(keyMap2);
		System.out.println("乙方公钥: "+Base64.toBase64String(publicKey2));
		System.out.println("乙方私钥: "+Base64.toBase64String(privateKey2));
	    key1 = DHCoder.getSecretKey(publicKey2, privateKey1);
	    key2 = DHCoder.getSecretKey(publicKey1, privateKey2);
	    System.out.println("甲方本地密钥: "+Base64.toBase64String(key1));
	    System.out.println("乙方本地密钥: "+Base64.toBase64String(key2));
	    assertArrayEquals(key1, key2);
	}
	@Test
	public void test() throws Exception {
		System.out.println("\n=====甲方向乙方发送加密数据======");
		String input1 = "密码交换算法";
		System.out.println("原文 : "+input1);
		System.out.println("----使用甲本地密钥加密数据----------");
		byte[] code1 = DHCoder.encrypt(input1.getBytes(), key1);
		System.out.println("加密后: "+Base64.toBase64String(code1));
		System.out.println("----使用乙本地密钥解密数据----------");
		byte[] decode1 = DHCoder.decrypt(code1, key2);
		String output1 = new String(decode1);
		System.out.println("解密后: "+output1);
		assertEquals(input1, output1);
		System.out.println("----使用乙本地密钥加密数据----------");
		String input2 = "密码交换算法";
		byte[] code2 = DHCoder.encrypt(input2.getBytes(), key2);
		System.out.println("加密后: "+Base64.toBase64String(code2));
		System.out.println("----使用甲本地密钥解密数据----------");
		byte[] decode2 = DHCoder.decrypt(code2, key1);
		String output2 = new String(decode2);
		System.out.println("解密后: "+output2);
		assertEquals(input2, output2);
	}

}
