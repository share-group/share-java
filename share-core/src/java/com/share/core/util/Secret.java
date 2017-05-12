package com.share.core.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密类
 */
public final class Secret {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Secret.class);
	/**
	 * 系统加密key
	 */
	private final static String systemKey = SystemUtil.getSystemKey();
	/**
	 * 系统字符集
	 */
	private final static String systemCharsetString = SystemUtil.getSystemCharsetString();
	/**
	 * 签名算法
	 */
	public final static String SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * 获取公钥的key
	 */
	private final static String PUBLIC_KEY = "RSAPublicKey";
	/**
	 * 获取私钥的key
	 */
	private final static String PRIVATE_KEY = "RSAPrivateKey";
	/**
	 * RSA最大加密明文大小
	 */
	private final static int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private final static int MAX_DECRYPT_BLOCK = 1024;
	/**
	 * base64加密
	 */
	private final static Encoder base64Encoder = Base64.getEncoder();
	/**
	 * base64解密
	 */
	private final static Decoder base64Decoder = Base64.getDecoder();
	/**
	 * AES加密位数
	 */
	private final static int AES_ENCRYPT_KEY_SIZE = 128;

	private Secret() {
	}

	/**
	 * des加密
	 * @param data
	 */
	public final static byte[] desEncrypt(String data) {
		try {
			return desEncrypt(data.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * des加密
	 * @param data
	 */
	public final static byte[] desEncrypt(byte[] data) {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(systemKey.getBytes(systemCharsetString));

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// 用密钥初始化Cipher对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * DES加密
	 * @param bytes 待加密的内容
	 */
	public final static String desEncryptToString(byte[] bytes) {
		return base64EncodeToString(desEncrypt(bytes));
	}

	/**
	 * DES加密
	 * @param content 待加密的内容
	 */
	public final static String desEncryptToString(String content) {
		try {
			return desEncryptToString(content.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * des解密
	 * @param data
	 */
	public final static byte[] desDecrypt(String data) {
		try {
			return desDecrypt(data.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * des解密
	 * @param data
	 */
	public final static byte[] desDecrypt(byte[] data) {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(systemKey.getBytes(systemCharsetString));

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// 用密钥初始化Cipher对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * DES解密
	 * @param encryptBytes 待解密的byte[]
	 * @return 解密后的String
	 */
	public static String desDecryptToString(byte[] encryptBytes) {
		try {
			return new String(desDecrypt(encryptBytes));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * DES解密
	 * @param encryptString 待解密的字符串
	 * @return 解密后的String
	 */
	public static String desDecryptToString(String encryptString) {
		try {
			return new String(desDecrypt(encryptString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES加密
	 * @param bytes 待加密的内容
	 */
	public final static byte[] aesEncrypt(byte[] bytes) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(systemKey.getBytes(systemCharsetString));
			kgen.init(AES_ENCRYPT_KEY_SIZE, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES加密
	 * @param content 待加密的内容
	 */
	public final static byte[] aesEncrypt(String content) {
		try {
			return aesEncrypt(content.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES加密
	 * @param bytes 待加密的内容
	 */
	public final static String aesEncryptToString(byte[] bytes) {
		return base64EncodeToString(aesEncrypt(bytes));
	}

	/**
	 * AES加密
	 * @param content 待加密的内容
	 */
	public final static String aesEncryptToString(String content) {
		try {
			return aesEncryptToString(content.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @return 解密后的String
	 */
	public static byte[] aesDecrypt(byte[] encryptBytes) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(systemKey.getBytes(systemCharsetString));
			kgen.init(AES_ENCRYPT_KEY_SIZE, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			return cipher.doFinal(encryptBytes);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES解密
	 * @param encryptString 待解密的字符串
	 * @return 解密后的String
	 */
	public static byte[] aesDecrypt(String encryptString) {
		try {
			return Secret.aesDecrypt(base64Decode(encryptString.getBytes(systemCharsetString)));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @return 解密后的String
	 */
	public static String aesDecryptToString(byte[] encryptBytes) {
		try {
			return new String(aesDecrypt(encryptBytes));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * AES解密
	 * @param encryptString 待解密的字符串
	 * @return 解密后的String
	 */
	public static String aesDecryptToString(String encryptString) {
		try {
			return new String(aesDecrypt(encryptString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * MD5加密
	 * @param string
	 */
	public final static String md5(String string) {
		try {
			return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(string.getBytes(systemCharsetString)));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 哈希加密
	 * @param string
	 */
	public final static String sha(String string) {
		try {
			return byteArrayToHexString(MessageDigest.getInstance("SHA").digest(string.getBytes(systemCharsetString)));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64加密
	 * @param str
	 */
	public final static String base64EncodeToString(String str) {
		try {
			return base64Encoder.encodeToString(str.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64加密
	 * @param bytes
	 */
	public final static String base64EncodeToString(byte[] bytes) {
		return base64Encoder.encodeToString(bytes);
	}

	/**
	 * base64加密
	 * @param str
	 */
	public final static byte[] base64Encode(String str) {
		try {
			return base64Encoder.encode(str.getBytes(systemCharsetString));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64加密
	 * @param bytes
	 */
	public final static byte[] base64Encode(byte[] bytes) {
		return base64Encoder.encode(bytes);
	}

	/**
	 * base64解密
	 * @param str
	 */
	public final static String base64DecodeToString(String str) {
		try {
			return new String(base64Decode(str));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64解密
	 * @param bytes
	 */
	public final static String base64DecodeToString(byte[] bytes) {
		try {
			return new String(base64Decode(bytes));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64解密
	 * @param str
	 */
	public final static byte[] base64Decode(String str) {
		try {
			return base64Decoder.decode(str);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64解密
	 * @param bytes
	 */
	public final static byte[] base64Decode(byte[] bytes) {
		try {
			return base64Decoder.decode(bytes);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 生成密钥对(公钥和私钥)
	 */
	public final static Map<String, Object> genKeyPair() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed(SystemUtil.getSystemKey().getBytes(systemCharsetString)); // 初始化随机产生器
			keyPairGen.initialize(MAX_DECRYPT_BLOCK, secrand);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
			return keyMap;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 用私钥对信息生成数字签名
	 * @param data 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static String sign(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = base64Decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateK);
			signature.update(data);
			return base64EncodeToString(signature.sign());
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 校验数字签名
	 * @param data 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 * @param sign 数字签名
	 */
	public final static boolean verify(byte[] data, String publicKey, String sign) {
		try {
			byte[] keyBytes = base64Decode(publicKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicK = keyFactory.generatePublic(keySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicK);
			signature.update(data);
			return signature.verify(base64Encode(sign));
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 私钥解密
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) {
		try {
			byte[] keyBytes = base64Decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 公钥解密
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public final static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) {
		try {
			byte[] keyBytes = base64Decode(publicKey);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 公钥加密
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public final static byte[] encryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = base64Decode(publicKey);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 私钥加密
	 * @param data 源数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static byte[] encryptByPrivateKey(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = base64Decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取私钥
	 * @param keyMap 密钥对
	 */
	public final static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return base64EncodeToString(key.getEncoded());
	}

	/**
	 * 获取公钥
	 * @param keyMap 密钥对
	 */
	public final static String getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return base64EncodeToString(key.getEncoded());
	}

	/**
	 * byteArrayToHexString
	 * @author ruan
	 * @param bytes
	 * @return
	 */
	private final static String byteArrayToHexString(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		buf.trimToSize();
		return buf.toString();
	}
}