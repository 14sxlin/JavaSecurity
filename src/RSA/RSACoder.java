package RSA;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class RSACoder {
	private static final String PUBKEY = "RSAPublicKey";
	private static final String PRIKEY = "RSAPrivateKey";
	/**
	 * RSA长度默认1024为
	 * 密钥长度必须是64的倍数
	 * 从 512~65536
	 */
	private static final int KEY_SIZE = 512;
	
	public static Map<String,Object> initKey() throws Exception{
		KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
		kg.initialize(KEY_SIZE);
		KeyPair keyPair = kg.generateKeyPair();
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap 
						= new HashMap<>(2);
		keyMap.put(PUBKEY, pubKey);
		keyMap.put(PRIKEY, priKey);
		return keyMap;
		
	}
	/**
	 * 用私钥解密
	 * @param data 加密了的数据
	 * @param key 私钥
	 * @return 解密的数据
	 */
	public static byte[] decrypyByPrivateKey(byte[] data,byte[] key) {
		try {
			PKCS8EncodedKeySpec keyspec =
					new PKCS8EncodedKeySpec(key);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kfac.generatePrivate(keyspec);
			Cipher cipher = Cipher.getInstance(kfac.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 用公钥解密
	 * @param data 加密了的数据
	 * @param key 私钥
	 * @return 解密的数据
	 */
	public static byte[] decrypyByPublicKey(byte[] data,byte[] key) {
		try {
			X509EncodedKeySpec keyspec =
					new X509EncodedKeySpec(key);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PublicKey publicKey = kfac.generatePublic(keyspec);
			Cipher cipher = Cipher.getInstance(kfac.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用公钥加密
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPublicKey(byte[] data,byte[] key)
	{
		try {
			X509EncodedKeySpec keyspec =
					new X509EncodedKeySpec(key);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PublicKey publicKey = kfac.generatePublic(keyspec);
			Cipher cipher = Cipher.getInstance(kfac.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 * 用私钥加密
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encryptByPrivateKey(byte[] data,byte[] key)
	{
		try {
			PKCS8EncodedKeySpec keyspec =
					new PKCS8EncodedKeySpec(key);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = kfac.generatePrivate(keyspec);
			Cipher cipher = Cipher.getInstance(kfac.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getPublicKey(Map<String, Object> keyMap) {
		return ((PublicKey)keyMap.get(PUBKEY)).getEncoded();
	}
	public static byte[] getPrivateKey(Map<String, Object> keyMap) {
		return ((PrivateKey)keyMap.get(PRIKEY)).getEncoded();
	}
	
}
