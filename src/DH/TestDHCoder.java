package DH;

import static org.junit.Assert.*;

import java.util.Map;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;

public class TestDHCoder {

	//�׷���Կ
	private byte[] publicKey1;
	//�׷���Կ
	private byte[] privateKey1;
	 //�׷�������Կ
	private byte[] key1;
	//�ҷ���Կ
	private byte[] publicKey2;
	//�ҷ���Կ
	private byte[] privateKey2;
	//�ҷ�������Կ
	private byte[] key2;
	
	
	@Before
	public final void initKey() throws Exception {
		//���ɼ׷���Կ��
		Map<String, Object> keyMap1 = DHCoder.initKey();
		publicKey1 = DHCoder.getPublicKey(keyMap1);
		privateKey1 = DHCoder.getPrivateKey(keyMap1);
		System.out.println("�׷���Կ: "+Base64.toBase64String(publicKey1));
		System.out.println("�׷�˽Կ: "+Base64.toBase64String(privateKey1));
		//�ɼ׷���Կ����������Կ (�ҷ���Կ)
		Map<String,Object>keyMap2 = DHCoder.initKey(publicKey1);
		publicKey2 = DHCoder.getPublicKey(keyMap2);
		privateKey2 = DHCoder.getPrivateKey(keyMap2);
		System.out.println("�ҷ���Կ: "+Base64.toBase64String(publicKey2));
		System.out.println("�ҷ�˽Կ: "+Base64.toBase64String(privateKey2));
	    key1 = DHCoder.getSecretKey(publicKey2, privateKey1);
	    key2 = DHCoder.getSecretKey(publicKey1, privateKey2);
	    System.out.println("�׷�������Կ: "+Base64.toBase64String(key1));
	    System.out.println("�ҷ�������Կ: "+Base64.toBase64String(key2));
	    assertArrayEquals(key1, key2);
	}
	@Test
	public void test() throws Exception {
		System.out.println("\n=====�׷����ҷ����ͼ�������======");
		String input1 = "���뽻���㷨";
		System.out.println("ԭ�� : "+input1);
		System.out.println("----ʹ�üױ�����Կ��������----------");
		byte[] code1 = DHCoder.encrypt(input1.getBytes(), key1);
		System.out.println("���ܺ�: "+Base64.toBase64String(code1));
		System.out.println("----ʹ���ұ�����Կ��������----------");
		byte[] decode1 = DHCoder.decrypt(code1, key2);
		String output1 = new String(decode1);
		System.out.println("���ܺ�: "+output1);
		assertEquals(input1, output1);
		System.out.println("----ʹ���ұ�����Կ��������----------");
		String input2 = "���뽻���㷨";
		byte[] code2 = DHCoder.encrypt(input2.getBytes(), key2);
		System.out.println("���ܺ�: "+Base64.toBase64String(code2));
		System.out.println("----ʹ�üױ�����Կ��������----------");
		byte[] decode2 = DHCoder.decrypt(code2, key1);
		String output2 = new String(decode2);
		System.out.println("���ܺ�: "+output2);
		assertEquals(input2, output2);
	}

}
