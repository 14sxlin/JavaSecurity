package md2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public abstract class MDCoder {
	public static byte[] encodeMd2(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("md2");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] encodeMD5(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] encodeMD4(byte[] data) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("md4");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}return null;
		
	}
	
	public static String encodeMD4Hex(byte[] data) {
		byte[] b = encodeMD4(data);
		return new String(Hex.encode(b));
	}
	
}
