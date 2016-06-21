package DH;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DHCoder {
	//�ǶԳƼ�����Կ�㷨
	public static final String KEY_ALGORITHM="DH";
	/**
	 * ������Կ�㷨,���ԳƼ�����Կ�㷨
	 * ��ѡDES,DESede.AES�㷨
	 */
	public static final String SECRET_ALGORITHM = "AES";
	/**
	 * ��Կ����
	 * DH�㷨Ĭ����Կ����Ϊ1024
	 * ��Կ���ȱ�����64�ı���
	 */
	private static final int KEY_SIZE = 512;
	
	//��Կ
	private static final String PUBLIC_KEY = "DHPublicKey";
	//˽Կ
	private static final String PRIVATE_KEY = "DHPrivateKey";
	
	/**
	 * ��ʼ���׷���Կ
	 * @return Map �׷�����ԿMap
	 * @throws Exception
	 */
	public static Map<String,Object> initKey() throws Exception{
		KeyPairGenerator keyPairGenerator =
					KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		DHPublicKey publicKey = 
				(DHPublicKey) keyPair.getPublic();
		DHPrivateKey privateKey = 
				(DHPrivateKey) keyPair.getPrivate();
		HashMap<String, Object> keyMap = 
				new HashMap<String,Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	/**
	 * ��ʼ���ҷ�����Կ
	 * @param key �׷���Կ
	 * @return Map �ҷ���ԿMap
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidAlgorithmParameterException 
	 */
	public static Map<String,Object> initKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException{
		//�����׷���Կ
		//ת����Կ����
		X509EncodedKeySpec X509spec = 
				new X509EncodedKeySpec(key);
		KeyFactory kfac = 
				KeyFactory.getInstance(KEY_ALGORITHM);
		//������Կ
		PublicKey pubk = kfac.generatePublic(X509spec);
		//�ɼ׷���Կ�����ҷ���Կ ����������
		DHParameterSpec dhParameterSpec =
					((DHPublicKey)pubk).getParams();
		KeyPairGenerator keyPairGen = 
				KeyPairGenerator.getInstance(kfac.getAlgorithm());
		//��������ȥ��ʼ��
		keyPairGen.initialize(dhParameterSpec);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		DHPublicKey publicKey = 
					(DHPublicKey) keyPair.getPublic();
		DHPrivateKey privateKey = 
					(DHPrivateKey) keyPair.getPrivate();
		Map<String, Object> map  = new HashMap<>();
		map.put(PRIVATE_KEY, privateKey);
		map.put(PUBLIC_KEY, publicKey);
		return map;
	}
	 public static byte[] encrypt(byte[] data, byte[] key) {
		 try {
			SecretKey secretKey = 
					 	new SecretKeySpec(key, SECRET_ALGORITHM);
			 Cipher cipher = 
					 Cipher.getInstance(secretKey.getAlgorithm());
			 cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			 return cipher.doFinal(data);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 public static byte[] decrypt(byte[] data, byte[] key) {
		 try {
			 SecretKey secretKey = 
					 new SecretKeySpec(key, SECRET_ALGORITHM);
			 Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
			 cipher.init(Cipher.DECRYPT_MODE, secretKey);
			 return cipher.doFinal(data);
		 } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				 | BadPaddingException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return null;
	 }
	public static byte[] getSecretKey(byte[] publicKey,byte[] privateKey)
	{
		try {
			KeyFactory kfac = 
					KeyFactory.getInstance(KEY_ALGORITHM);
			X509EncodedKeySpec x509keyspec = 
					new X509EncodedKeySpec(publicKey);
			PublicKey pubKey = kfac.generatePublic(x509keyspec);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec
			 					= new PKCS8EncodedKeySpec(privateKey);
			PrivateKey priKey = 
					kfac.generatePrivate(pkcs8EncodedKeySpec);
			KeyAgreement keyAgree  =  
						KeyAgreement.getInstance(kfac.getAlgorithm());
			keyAgree.init(priKey);
			keyAgree.doPhase(pubKey, true);
			SecretKey secretKey = 
					keyAgree.generateSecret(SECRET_ALGORITHM);
			return secretKey.getEncoded();
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] getPrivateKey(Map<String,Object> keyMap)
	{
		Key key =  (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}
	public static byte[] getPublicKey(Map<String,Object> keyMap)
	{
		Key key =  (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}
}
