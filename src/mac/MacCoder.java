package mac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public abstract class MacCoder {
	public static byte[] initKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = 
				KeyGenerator.getInstance("HmacMD5");
		SecretKey key = keyGen.generateKey();
		return key.getEncoded();
	}
	
	public static byte[] encodeHmacMd5(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeyException
	{
		SecretKey secrkey = new SecretKeySpec(key, "HmacMD5");
		Mac mac = Mac.getInstance(secrkey.getAlgorithm());
		mac.init(secrkey);
		return mac.doFinal();
		
	}
	
}
