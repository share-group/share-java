package com.share.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http服务器工具
 * @author ruan
 */
public final class HttpServerUtil {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(HttpServerUtil.class);

	private HttpServerUtil() {
	}

	/**
	 * 提供下载文件的服务
	 * @param file 被下载的文件
	 * @param response http response对象
	 */
	public final static void downloadFile(File file, HttpServletResponse response) {
		String fileName = file.getName().trim();
		if (!file.exists()) {
			logger.error("file " + fileName + " not exists！");
			return;
		}
		logger.info("downlading file: " + fileName);

		long fileLength = file.length();
		if (fileLength <= 0) {
			logger.error("{}'s fileLength = 0", fileName);
			return;
		}

		/* 创建输入流 */
		InputStream inStream = null;
		/* 创建输出流 */
		ServletOutputStream servletOS = null;
		try {
			response.reset();
			response.setContentType("application/x-msdownload");
			response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, SystemUtil.getSystemCharsetString()) + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
			response.setContentLengthLong(fileLength);

			inStream = new FileInputStream(file);
			servletOS = response.getOutputStream();
			int readLength;
			byte[] buf = new byte[1024];
			while ((readLength = inStream.read(buf)) != -1) {
				servletOS.write(buf, 0, readLength);
				servletOS.flush();
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (servletOS != null) {
					servletOS.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}