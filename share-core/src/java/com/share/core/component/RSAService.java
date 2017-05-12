package com.share.core.component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.util.FileSystem;
import com.share.core.util.Secret;
import com.share.core.util.StringUtil;

/**
 * rsa加密
 * @author ruan
 */
public class RSAService {
	/**
	 * logger
	 */
	private Logger logger;
	/**  
	 * RSA私钥  
	 */
	private RSAPrivateKey rsaPrivateKey;
	/**  
	 * RSA公钥  
	 */
	private RSAPublicKey rsaPublicKey;
	/**
	 * RSA公钥pem文件地址
	 */
	private String publicKey;
	/**
	 * RSA私钥pem文件地址
	 */
	private String privateKey;
	/**
	 * 加密对象的名字
	 */
	private String name;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		name = StringUtil.getString(name);
		if (name.isEmpty()) {
			logger = LoggerFactory.getLogger(RSAService.class);
		} else {
			logger = LoggerFactory.getLogger(name);
		}

		//加载公钥    
		loadPublicKey(FileSystem.loadPem(publicKey));
		logger.info("load rsa public key: {}", publicKey);

		//加载私钥    
		loadPrivateKey(FileSystem.loadPem(privateKey));
		logger.info("load rsa private key: {}", privateKey);
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 从字符串中加载公钥  
	 * @param publicKeyStr 公钥数据字符串  
	 * @throws Exception 加载公钥时产生的异常  	
	 */
	public final void loadPublicKey(String publicKeyStr) {
		try {
			byte[] buffer = Secret.base64Decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	public final void loadPrivateKey(String privateKeyStr) {
		try {
			byte[] buffer = Secret.base64Decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**  
	 * rsa加密  
	 * @param data 待加密的数据
	 */
	public final byte[] rsaEncrypt(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
			byte[] decryptedData = new byte[0];
			for (int i = 0; i < data.length; i += 100) {
				byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 100));
				decryptedData = ArrayUtils.addAll(decryptedData, doFinal);
			}
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**  
	 * rsa加密  
	 * @param data 待加密的数据
	 */
	public final byte[] rsaEncrypt(String data) {
		return rsaEncrypt(data.getBytes());
	}

	/**  
	 * rsa加密  
	 * @param data 待加密的数据
	 */
	public final String rsaEncryptToString(byte[] data) {
		return Secret.base64EncodeToString(rsaEncrypt(data));
	}

	/**  
	 * rsa加密  
	 * @param data 待加密的数据
	 */
	public final String rsaEncryptToString(String data) {
		return Secret.base64EncodeToString(rsaEncrypt(data.getBytes()));
	}

	/**  
	 * rsa解密
	 * @param data 待解密的数据 
	 */
	public final byte[] rsaDecrypt(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			byte[] decryptedData = new byte[0];
			cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
			for (int i = 0; i < data.length; i += 128) {
				byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
				decryptedData = ArrayUtils.addAll(decryptedData, doFinal);
			}
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**  
	 * rsa解密
	 * @param data 待解密的数据 
	 */
	public final byte[] rsaDecrypt(String data) {
		return rsaDecrypt(Secret.base64Decode(data));
	}

	/**  
	 * rsa解密
	 * @param data 待解密的数据 
	 */
	public final String rsaDecryptToString(byte[] data) {
		return new String(rsaDecrypt(Secret.base64Decode(data))).trim();
	}

	/**  
	 * rsa解密
	 * @param data 待解密的数据 
	 */
	public final String rsaDecryptToString(String data) {
		return new String(rsaDecrypt(Secret.base64Decode(data))).trim();
	}
}