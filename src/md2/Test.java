package md2;

import static org.junit.Assert.*;

import org.junit.Before;

public class Test {

	@Before
	public void setUp() throws Exception {
	}

	@org.junit.Test
	public void testMD2() {
		String str = "MD2��ϢժҪ";
		byte[] data1 = MDCoder.encodeMd2(str.getBytes());
		byte[] data2 = MDCoder.encodeMd2(str.getBytes());
		assertArrayEquals(data1, data2);
	}
	
	@org.junit.Test
	public void testMD5() {
		String str = "MD5��ϢժҪ";
		byte[] data1 = MDCoder.encodeMD5(str.getBytes());
		byte[] data2 = MDCoder.encodeMD5(str.getBytes());
		assertArrayEquals(data1, data2);
	}
	
	@org.junit.Test
	public void testMD4() {
		String str = "MD4��ϢժҪ";
		byte[] data1 = MDCoder.encodeMD4(str.getBytes());
		byte[] data2 = MDCoder.encodeMD4(str.getBytes());
		assertArrayEquals(data1, data2);
	}
	@org.junit.Test
	public void testMD4Hex() {
		String str = "MD4��ϢժҪ";
		String data1 = MDCoder.encodeMD4Hex(str.getBytes());
		String data2 = MDCoder.encodeMD4Hex(str.getBytes());
		System.out.println("ԭ��: "+str);
		System.out.println("ת��1: "+data1);
		System.out.println("ת��2: "+data2);
		assertEquals(data1, data2);
	}

	
}
