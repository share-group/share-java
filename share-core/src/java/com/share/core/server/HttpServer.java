package com.share.core.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;
import com.share.core.util.FileSystem;

/**
 * jetty http 服务器
 * @author ruan
 *
 */
public class HttpServer extends AbstractServer {
	/**
	 * jetty server 对象
	 */
	private Server server;
	/**
	 * 端口
	 */
	private int port;
	/**
	 * webapp路径
	 */
	private List<String> webappPaths = new ArrayList<>();
	/**
	 * web.xml路径
	 */
	private final static String webXmlPath = "/WEB-INF/web.xml";

	/**
	 * 构造函数(默认80端口)
	 */
	public HttpServer() {
		this(80);
	}

	/**
	 * 构造函数
	 * @param port 端口
	 */
	public HttpServer(int port) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}

		this.port = port;
		webappPaths.add("lib/webapp");
		webappPaths.add("src/webapp");

		server = new Server(new QueuedThreadPool(200));
		String webappPath = getWebappPath();
		WebAppContext webAppContext = new WebAppContext(webappPath + webXmlPath, "/");
		webAppContext.setResourceBase(webappPath);

		// 不限制提交数据的大小，提交的key数量大小
		webAppContext.setMaxFormContentSize(-1);
		webAppContext.setMaxFormKeys(-1);
		server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", -1);
		server.setAttribute("org.eclipse.jetty.server.Request.maxFormKeys", -1);

		// 设置返回数据为1M
		HttpConfiguration httpConfig = new HttpConfiguration();
		httpConfig.setResponseHeaderSize(1048576);

		ServerConnector serverConnector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
		serverConnector.setName(FileSystem.getProjectName());
		serverConnector.setReuseAddress(true);
		serverConnector.setPort(port);

		server.setHandler(webAppContext);
		server.addConnector(serverConnector);
		server.setStopAtShutdown(true);
	}

	/**
	 * 获取webapp路径
	 */
	private String getWebappPath() {
		for (String webappPath : webappPaths) {
			File webappFile;
			if (FileSystem.isWindows() || FileSystem.isMacOSX()) {
				webappFile = new File(webappPath, webXmlPath);
			} else {
				webappFile = new File(FileSystem.getSystemDir() + FileSystem.getProjectName() + "/" + webappPath, webXmlPath);
			}
			if (webappFile.exists()) {
				logger.warn("find " + webappFile.getAbsolutePath());
				return webappPath;
			}
		}
		throw new IllegalStateException("not find any webappPath");
	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		} finally {
			logger.warn("http server {} start success, bind port {} ...", FileSystem.getProjectName(), port);
		}
	}
}
