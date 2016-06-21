package crc32;

import java.util.zip.CRC32;

import org.junit.Test;

public class CrcTest {

	@Test
	public void test() {
		String str = "≤‚ ‘CRC-32";
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		String hex = Long.toHexString(crc32.getValue());
		System.out.println("‘≠Œƒ «: "+str);
		System.out.println("CRC32:  "+hex);
	}

}
