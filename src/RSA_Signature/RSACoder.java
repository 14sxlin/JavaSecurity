package RSA_Signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

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

	public static byte[] sign(byte[] datat,byte[] privateKey) {
		try {
			PKCS8EncodedKeySpec spec = 
					new PKCS8EncodedKeySpec(privateKey);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PrivateKey priKey = kfac.generatePrivate(spec);
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(priKey);
			signature.update(datat);
			return signature.sign();
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}
	
	public static boolean verify(byte[] data,byte[] publicKey,byte[] sign) {
		try {
			X509EncodedKeySpec spec =
								new X509EncodedKeySpec(publicKey);
			KeyFactory kfac = KeyFactory.getInstance("RSA");
			PublicKey pubKey = kfac.generatePublic(spec);
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(pubKey);
			signature.update(data);
			return signature.verify(sign);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static byte[] getPublicKey(Map<String, Object> keyMap) {
		return ((PublicKey)keyMap.get(PUBKEY)).getEncoded();
	}
	public static byte[] getPrivateKey(Map<String, Object> keyMap) {
		return ((PrivateKey)keyMap.get(PRIKEY)).getEncoded();
	}
	
}
