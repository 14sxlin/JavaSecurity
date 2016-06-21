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
		String str = "HmacMD5��ϢժҪ";
		byte[] key = MacCoder.initKey();
		byte[] data1 =  MacCoder.encodeHmacMd5(str.getBytes(), key);
		byte[] data2 = MacCoder.encodeHmacMd5(str.getBytes(), key);
		System.out.println("ԭ�� : "+ str);
		System.out.println("����1�� : "+Hex.toHexString(data1));
		System.out.println("����2�� : "+Hex.toHexString(data2));
		assertArrayEquals(data1, data2);
	}

}
