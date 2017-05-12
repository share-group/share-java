package com.share.core.component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.share.core.util.FileSystem;
import com.share.core.util.JSONObject;
import com.share.core.util.Secret;
import com.share.core.util.StringUtil;
import com.share.core.util.Time;

/**
 * jwt
 */
public class JwtService {
	/**
	 * logger
	 */
	private Logger logger;
	/**  
	 * RSA私钥  
	 */
	private Algorithm rsaPrivateKey;
	/**  
	 * RSA公钥  
	 */
	private Algorithm rsaPublicKey;
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
	 * 私有化构造函数，只有spring才可以实例化
	 */
	private JwtService() {
	}

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		name = StringUtil.getString(name);
		if (name.isEmpty()) {
			logger = LoggerFactory.getLogger(JwtService.class);
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
			rsaPublicKey = Algorithm.RSA512((RSAPublicKey) keyFactory.generatePublic(keySpec));
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
			rsaPrivateKey = Algorithm.RSA512((RSAPrivateKey) keyFactory.generatePrivate(keySpec));
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	 * 生成token
	 * @param json 要加密的json数据
	 */
	public String sign(JSONObject json) {
		return sign(json, 0);
	}

	/**
	 * 生成token
	 * @param json 要加密的json数据
	 * @param expire 过期时间(单位：秒)
	 */
	public String sign(JSONObject json, int expires) {
		try {
			JWTCreator.Builder jwt = JWT.create();
			for (Entry<String, Object> e : json.toMap().entrySet()) {
				jwt.withClaim(e.getKey(), StringUtil.getString(e.getValue()));
			}
			if (expires > 0) {
				jwt.withExpiresAt(new Date(Time.now(true) + expires * 1000));
			}
			return jwt.sign(rsaPrivateKey);
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 把token反解析成map
	 * @param token
	 */
	public JSONObject verify(String token) {
		try {
			JWTVerifier verifier = JWT.require(rsaPublicKey).build();
			DecodedJWT jwt = verifier.verify(token);
			JSONObject json = new JSONObject();
			for (Entry<String, Claim> e : jwt.getClaims().entrySet()) {
				String value = StringUtil.getString(e.getValue().asString());
				if (value.isEmpty()) {
					continue;
				}
				json.put(e.getKey(), value);
			}
			return json;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}