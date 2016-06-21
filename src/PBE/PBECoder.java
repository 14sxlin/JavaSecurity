package PBE;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public abstract class PBECoder {
	public static final String ALGORITHM = "PBEWITHMD5andDES";
	/**
	 * 迭代次数
	 */
	public static final int ITERATION_COUNT = 100;
	/**
	 * 盐初始胡<br>
	 * 盐长度必须为8字节
	 * @return byte[] 盐
	 */
	public static byte[] intiSalt() {
		SecureRandom sr = new SecureRandom();
		return sr.generateSeed(8);
	}
	
	private static Key toKey(String password) {
		try {
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory keyfac = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey key = keyfac.generateSecret(keySpec);
			return key;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] encrypt(byte[] data,String password,byte[] salt) {
		try {
			Key key = toKey(password);
			PBEParameterSpec paramSpec
					= new PBEParameterSpec(salt, ITERATION_COUNT);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key,paramSpec);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] data ,String password,byte[] salt) {
		try {
			Key key = toKey(password);
			PBEParameterSpec paramSpec
					= new PBEParameterSpec(salt, ITERATION_COUNT);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
