package com.share.test.http.server;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class ShareHttpMessageConverter extends AbstractHttpMessageConverter<String> {
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		System.err.println(1);
		return null;
	}

	protected void writeInternal(String t, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		System.err.println(2);
	}
}