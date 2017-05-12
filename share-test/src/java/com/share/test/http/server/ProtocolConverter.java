package com.share.test.http.server;

import java.io.IOException;

import javax.servlet.ServletInputStream;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.share.core.filter.ProtocolFilter;
import com.share.core.interfaces.AbstractConverter;
import com.share.core.protocol.protocol.ProtocolBase;
import com.share.core.util.StringUtil;
import com.share.test.protocol.ReqDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * protocol参数转换器
 * @author ruan
 *
 */
public class ProtocolConverter extends AbstractConverter {
	public ProtocolBase resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		ProtocolBase x = null;
		try {
			ServletInputStream inputStream = (ServletInputStream) webRequest.getAttribute(ProtocolFilter.protocolFilterKey, 0);
			String url = StringUtil.getString(webRequest.getAttribute(ProtocolFilter.url, 0));
			int size = inputStream.available();
			if (size > 0) {
				byte[] bytes = new byte[size];
				inputStream.read(bytes, 0, size);
				ByteBuf buffer = Unpooled.buffer(bytes.length);
				buffer.readBytes(bytes);
				x = new ReqDemo();
				x.loadFromBuffer(buffer);

			}
			inputStream.close();
		} catch (IOException e) {
			logger.error("", e);
		}
		return x;
	}
}