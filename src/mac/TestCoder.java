package mac;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;

public class TestCoder {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws NoSuchAlgorithmException, InvalidKeyException {
		String str = "HmacMD5消息摘要";
		byte[] key = MacCoder.initKey();
		byte[] data1 =  MacCoder.encodeHmacMd5(str.getBytes(), key);
		byte[] data2 = MacCoder.encodeHmacMd5(str.getBytes(), key);
		System.out.println("原文 : "+ str);
		System.out.println("编码1后 : "+Hex.toHexString(data1));
		System.out.println("编码2后 : "+Hex.toHexString(data2));
		assertArrayEquals(data1, data2);
	}

}
