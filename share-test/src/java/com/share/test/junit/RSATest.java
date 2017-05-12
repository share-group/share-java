package com.share.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.core.component.RSAService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:share-test.xml" })
public class RSATest {
	@Autowired
	private RSAService rsaService1024;
	@Autowired
	private RSAService rsaService2048;
	@Autowired
	private RSAService rsaService4096;
	@Autowired
	private RSAService rsaService8192;
	private final static String str = "你好吗？";

	@Test
	public void test() throws Exception {
		rsa1024();
		rsa2048();
		rsa4096();
		rsa8192();
	}

	private void rsa1024() {
		String encode1024 = rsaService1024.rsaEncryptToString(str);
		System.err.println(encode1024);
		String decode1024 = rsaService1024.rsaDecryptToString(encode1024);
		System.err.println(decode1024);
	}

	private void rsa2048() {
		String encode2048 = rsaService2048.rsaEncryptToString(str);
		System.err.println(encode2048);
		String decode2048 = rsaService2048.rsaDecryptToString(encode2048);
		System.err.println(decode2048);
	}

	private void rsa4096() {
		String encode4096 = rsaService4096.rsaEncryptToString(str);
		System.err.println(encode4096);
		String decode4096 = rsaService4096.rsaDecryptToString(encode4096);
		System.err.println(decode4096);
	}

	private void rsa8192() {
		String encode8192 = rsaService8192.rsaEncryptToString(str);
		System.err.println(encode8192);
		String decode8192 = rsaService8192.rsaDecryptToString(encode8192);
		System.err.println(decode8192);
	}
}