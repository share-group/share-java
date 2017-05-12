package com.share.soa.thrift.server;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

/**
 * thrift http servlet
 */
public class ThriftHttpServlet extends HttpServlet {
	private static final long serialVersionUID = -2355023383614264213L;
	private static final Logger logger = LoggerFactory.getLogger(ThriftHttpServlet.class);
	private TProcessor processor;
	private TProtocolFactory protocolFactory;
	private Class<?> serviceClass;
	private Class<? extends TProtocol> protocolClass;
	private Class<?> handlerClass;

	/**
	 * 私有构造函数
	 */
	private ThriftHttpServlet() {
	}

	public Class<?> getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}

	public Class<? extends TProtocol> getProtocolClass() {
		return protocolClass;
	}

	public void setProtocolClass(Class<? extends TProtocol> protocolClass) {
		this.protocolClass = protocolClass;
	}

	public Class<?> getHandlerClass() {
		return handlerClass;
	}

	public void setHandlerClass(Class<?> handlerClass) {
		this.handlerClass = handlerClass;
	}

	/**
	 * 初始化
	 */
	public void init() {
		try {
			// 初始化通讯协议
			protocolFactory = (TProtocolFactory) Class.forName(protocolClass.getName() + "$Factory").newInstance();

			// 初始化Iface
			Class<?> IfaceClazz = Class.forName(serviceClass.getName() + "$Iface");

			// 初始化处理器
			Constructor<?> constructor = Class.forName(serviceClass.getName() + "$Processor").getConstructor(new Class<?>[] { IfaceClazz });
			processor = (TProcessor) constructor.newInstance(handlerClass.newInstance());
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream output = response.getOutputStream();
		TTransport transport = new TIOStreamTransport(request.getInputStream(), output);
		try {
			processor.process(protocolFactory.getProtocol(transport), protocolFactory.getProtocol(transport));
			output.flush();
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}