package IDEA;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public abstract class IDEACoder {
	public static final String KEY_ALOGRITHM = "IDEA";
	/**
	 * 加密/解密算法 / 工作模式 / 填充模式
	 */
	private static final  String CIPHER_ALOGRITHM = "IDEA/ECB/ISO10126Padding";
	
	public static byte[] intitKey() {
		try {
			Security.addProvider(new BouncyCastleProvider());
			KeyGenerator kg = KeyGenerator.getInstance(KEY_ALOGRITHM);
			kg.init(128);
			SecretKey key = kg.generateKey();
			return key.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static SecretKey getKey(byte[] keycode) {
		SecretKey key = new SecretKeySpec(keycode, CIPHER_ALOGRITHM);
		return key;
	}
	
	public static byte[] encrypt(byte[] keycode,byte[] data) {
		try {
			Key key = getKey(keycode);
			Cipher cipher = Cipher.getInstance(CIPHER_ALOGRITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] keycode,byte[] data) {
		try {
			Key key = getKey(keycode);
			Cipher cipher = Cipher.getInstance(CIPHER_ALOGRITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
