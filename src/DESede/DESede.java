package DESede;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public abstract class DESede {
	/**
	 * java 6 ֧�� 112 �� 168 λ ��Կ
	 * bouncy ֧�� 128 �� 192λ
	 */
	private static final  String KEY_algorithm = "DESede";
	/**
	 * ����/�����㷨 / ����ģʽ / ���ģʽ
	 */
	private static final  String CIPHER_algorithm = "DESede/ECB/PKCS5Padding";
	
	public static byte[] intitKey() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance(KEY_algorithm);
			kg.init(112);
			SecretKey key = kg.generateKey();
			return key.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static SecretKey getKey(byte[] keycode) {
		try {
			DESedeKeySpec sp = new DESedeKeySpec(keycode);
			SecretKeyFactory kf = SecretKeyFactory.getInstance(KEY_algorithm);
			return kf.generateSecret(sp);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] encrypt(byte[] keycode,byte[] data) {
		try {
			Key key = getKey(keycode);
			Cipher cipher = Cipher.getInstance(CIPHER_algorithm);
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
			Cipher cipher = Cipher.getInstance(CIPHER_algorithm);
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
