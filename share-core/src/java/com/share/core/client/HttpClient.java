package com.share.core.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.util.StringUtil;
import com.share.core.util.SystemUtil;

/**
 * http客户端
 * @author ruan
 */
public final class HttpClient {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	/**
	 * 默认字符集
	 */
	private final static Charset charset = SystemUtil.getSystemCharset();
	/**
	 * 默认超时时间(3秒)
	 */
	private int connectTimeout = 3000;
	/**
	 * 证书编码格式
	 */
	private String keystore = "";
	/**
	 * 证书路径(相对于classpath)
	 */
	private String cert = "";
	/**
	 * 证书密码
	 */
	private String password = "";
	/**
	 * ssl加密算法
	 */
	private String[] sslProtocols = new String[0];
	/**
	 * http连接池
	 */
	private CloseableHttpClient client;
	/**
	 * 连接池管理器
	 */
	private PoolingHttpClientConnectionManager cm;

	/**
	 * 构造函数
	 */
	@SuppressWarnings("unused")
	private HttpClient() {
	}

	/**
	 * 构造函数
	 * @param connectTimeout 超时时间
	 */
	public HttpClient(int connectTimeout) {
		this("", "", "", null, connectTimeout);
	}

	/**
	 * 构造函数
	 * @param keystore 证书编码格式
	 * @param cert 证书路径(相对于classpath)
	 * @param password 证书密码
	 * @param sslProtocols ssl加密算法
	 */
	public HttpClient(String keystore, String cert, String password, String[] sslProtocols) {
		this(keystore, cert, password, sslProtocols, 0);
	}

	/**
	 * 构造函数
	 * @param keystore 证书编码格式
	 * @param cert 证书路径(相对于classpath)
	 * @param password 证书密码
	 * @param sslProtocols ssl加密算法
	 * @param connectTimeout 超时时间
	 */
	public HttpClient(String keystore, String cert, String password, String[] sslProtocols, int connectTimeout) {
		this.keystore = keystore;
		this.cert = cert;
		this.password = password;
		if (connectTimeout > 0) {
			this.connectTimeout = connectTimeout;
		}
		if (sslProtocols != null && sslProtocols.length > 0) {
			this.sslProtocols = sslProtocols;
		}
		init();
	}

	/**
	 * 初始化
	 * @author ruan
	 */
	public void init() {
		// 是否需要有https证书
		boolean isCert = !keystore.isEmpty() && !cert.isEmpty() && !password.isEmpty() && sslProtocols.length > 0;

		try {
			RegistryBuilder<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create();
			socketFactoryRegistry.register("http", PlainConnectionSocketFactory.getSocketFactory());
			if (isCert) {
				KeyStore keyStore = KeyStore.getInstance(keystore);
				InputStream instream = ClassLoader.getSystemResourceAsStream(cert);
				keyStore.load(instream, password.toCharArray());
				instream.close();
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, sslProtocols, null, new DefaultHostnameVerifier());
				socketFactoryRegistry.register("https", sslsf);
			} else {
				SSLContext sslcontext = SSLContext.getInstance("TLS");
				sslcontext.init(null, new TrustManager[] { new TrustAllSSLCert() }, new SecureRandom());
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier());
				socketFactoryRegistry.register("https", sslsf);
			}

			// 初始化连接池
			cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry.build());
			cm.setMaxTotal(200);
			cm.setDefaultMaxPerRoute(100);
			cm.setValidateAfterInactivity(connectTimeout);

			// 设置字符集
			ConnectionConfig.Builder connectionConfigBuilder = ConnectionConfig.custom();
			connectionConfigBuilder.setCharset(charset);
			cm.setDefaultConnectionConfig(connectionConfigBuilder.build());

			// 设置socket连接选项
			SocketConfig.Builder socketConfigBuilder = SocketConfig.custom();
			socketConfigBuilder.setTcpNoDelay(true);
			socketConfigBuilder.setSoKeepAlive(false);
			socketConfigBuilder.setSoReuseAddress(true);
			socketConfigBuilder.setSoTimeout(connectTimeout);
			cm.setDefaultSocketConfig(socketConfigBuilder.build());

			// 请求超时设置
			RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
			requestConfigBuilder.setConnectTimeout(connectTimeout);
			requestConfigBuilder.setConnectionRequestTimeout(connectTimeout);
			requestConfigBuilder.setSocketTimeout(connectTimeout);

			client = HttpClients.custom().setConnectionManager(cm).build();
			logger.info("http client inited");
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	/**
	* 获取http连接池
	*/
	public CloseableHttpClient getClient() {
		return client;
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		try {
			cm.close();
			client.close();
		} catch (IOException e) {
			logger.error("", e);
		}
		logger.info("http client closed");
	}

	/**
	 * 设置超时时间
	 * @author ruan
	 * @param connectTimeout
	 */
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSslProtocols(String sslProtocols) {
		this.sslProtocols = sslProtocols.split(",");
	}

	/**
	 * 发送get请求，返回string
	 * @author ruan
	 * @param url
	 */
	public String getString(String url) {
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse httpResponse = client.execute(get);
			return StringUtil.getString(EntityUtils.toString(httpResponse.getEntity(), charset));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	/**
	 * 发送get请求，返回byte[]
	 * @author ruan
	 * @param url
	 */
	public byte[] getByte(String url) {
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse httpResponse = client.execute(get);
			return EntityUtils.toByteArray(httpResponse.getEntity());
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	/**
	 * 发送get请求
	 * @param url
	 * @param data
	 */
	public String get(String url, Map<String, Object> data) {
		StringBuilder sb = new StringBuilder(url);
		sb.append("?");
		try {
			for (Entry<String, Object> e : data.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(URLEncoder.encode(StringUtil.getString(e.getValue()), SystemUtil.getSystemCharsetString()));
				sb.append("&");
			}
		} catch (Exception e) {
			logger.error("", e);
			return "";
		}
		int len = sb.length();
		sb.delete(len - 1, len);
		return getString(sb.toString());
	}

	/**
	 * 发送post请求
	 * @author ruan
	 * @param url
	 * @return 字符串
	 */
	public String post(String url) {
		return post(url, null, null);
	}

	/**
	 * 发送post请求
	 * @author ruan 
	 * @param url
	 * @param data 字符串数据
	 */
	public String post(String url, String data) {
		HttpPost httppost = new HttpPost(url);
		try {
			if (data != null && !data.isEmpty()) {
				httppost.setEntity(new StringEntity(data, charset));
			}
			HttpResponse response = client.execute(httppost);
			return EntityUtils.toString(response.getEntity(), charset);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			httppost.releaseConnection();
		}
		return null;
	}

	/**
	 * 发送post请求
	 * @author ruan
	 * @param url
	 * @param data
	 * @return 字符串
	 */
	public String post(String url, Map<String, Object> data) {
		return post(url, data, null);
	}

	/**
	 * 发送post请求
	 * @author ruan
	 * @param url
	 * @param data
	 * @param header 
	 * @return 字符串
	 */
	public String post(String url, Map<String, Object> data, Map<String, Object> header) {
		HttpPost httppost = new HttpPost(url);
		try {
			if (header != null && !header.isEmpty()) {
				for (Entry<String, Object> e : header.entrySet()) {
					httppost.setHeader(e.getKey(), StringUtil.getString(e.getValue()));
				}
			}
			if (data != null && !data.isEmpty()) {
				List<NameValuePair> valuePairList = new ArrayList<NameValuePair>();
				for (Entry<String, Object> e : data.entrySet()) {
					valuePairList.add(new BasicNameValuePair(StringUtil.getString(e.getKey()), URLEncoder.encode(StringUtil.getString(e.getValue()), SystemUtil.getSystemCharsetString())));
				}
				httppost.setEntity(new UrlEncodedFormEntity(valuePairList, charset));
			}
			HttpResponse response = client.execute(httppost);
			return StringUtil.getString(EntityUtils.toString(response.getEntity(), charset));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			httppost.releaseConnection();
		}
		return null;
	}

	/**
	 * 发送post请求(没有做urlencode的)
	 * @author ruan
	 * @param url
	 * @return 字符串
	 */
	public String postNotUrlencode(String url) {
		return postNotUrlencode(url, null, null);
	}

	/**
	 * 发送post请求(没有做urlencode的)
	 * @author ruan
	 * @param url
	 * @param data
	 * @return 字符串
	 */
	public String postNotUrlencode(String url, Map<String, Object> data) {
		return postNotUrlencode(url, data, null);
	}

	/**
	 * 发送post请求(没有做urlencode的)
	 * @author ruan
	 * @param url
	 * @param data
	 * @param header 
	 * @return 字符串
	 */
	public String postNotUrlencode(String url, Map<String, Object> data, Map<String, Object> header) {
		HttpPost httppost = new HttpPost(url);
		try {
			if (header != null && !header.isEmpty()) {
				for (Entry<String, Object> e : header.entrySet()) {
					httppost.setHeader(e.getKey(), StringUtil.getString(e.getValue()));
				}
			}
			if (data != null && !data.isEmpty()) {
				List<NameValuePair> valuePairList = new ArrayList<NameValuePair>();
				for (Entry<String, Object> e : data.entrySet()) {
					valuePairList.add(new BasicNameValuePair(StringUtil.getString(e.getKey()), StringUtil.getString(e.getValue())));
				}
				httppost.setEntity(new UrlEncodedFormEntity(valuePairList, charset));
			}
			HttpResponse response = client.execute(httppost);
			return StringUtil.getString(EntityUtils.toString(response.getEntity(), charset));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			httppost.releaseConnection();
		}
		return null;
	}

	/**
	 * 发送post请求
	 * @author ruan
	 * @param url
	 * @param data
	 * @return 字节流
	 */
	public byte[] post(String url, byte[] data) {
		HttpPost httppost = new HttpPost(url);
		try {
			ByteArrayEntity reqEntity = new ByteArrayEntity(data);
			httppost.setEntity(reqEntity);
			HttpResponse response = client.execute(httppost);
			return EntityUtils.toByteArray(response.getEntity());
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			httppost.releaseConnection();
		}
		return null;
	}

	/**
	 * 上传文件
	 * @param url 要上传的url
	 * @param filepath 文件路径
	 * @param filePartName 文件上传域的名
	 */
	public String uploadFile(String url, String filepath, String filePartName) {
		return uploadFile(url, null, filepath, filePartName);
	}

	/**
	 * 上传文件
	 * @param url 要上传的url
	 * @param data 附带参数
	 * @param filepath 文件路径
	 * @param filePartName 文件上传域的名
	 */
	public String uploadFile(String url, Map<String, Object> data, String filepath, String filePartName) {
		HttpPost httpPost = new HttpPost(url);
		try {
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addBinaryBody("file", new File(filepath));
			if (data != null && !data.isEmpty()) {
				for (Entry<String, Object> e : data.entrySet()) {
					multipartEntityBuilder.addTextBody(StringUtil.getString(e.getKey()), StringUtil.getString(e.getValue()));
				}
			}
			httpPost.setEntity(multipartEntityBuilder.build());
			HttpResponse response = client.execute(httpPost);
			return StringUtil.getString(EntityUtils.toString(response.getEntity(), charset));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			httpPost.releaseConnection();
		}
		return null;
	}

	/**
	 * 下载文件
	 * @param url 文件的网络地址
	 * @param filepath 文件的本地地址
	 */
	public void downloadFile(String url, String filepath) {
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File(filepath);
			FileOutputStream fileout = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			logger.warn("download file {} success ...", url);
			httpget.releaseConnection();
		}
	}

	/**
	 * 信任所有ssl证书
	 * @author ruan
	 */
	private final static class TrustAllSSLCert implements TrustManager, X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
			return;
		}
	}
}