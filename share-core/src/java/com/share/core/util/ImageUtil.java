package com.share.core.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 图片工具类
 * @author ruan
 */
public class ImageUtil {
	private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 根据尺寸图片居中裁剪
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param w 宽
	 * @param h 高
	 */
	public final static void cutCenterImage(String src, String dest, int w, int h) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			int imageIndex = 0;
			Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * 图片裁剪二分之一 
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 */
	public final static void cutHalfImage(String src, String dest) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			int imageIndex = 0;
			int width = reader.getWidth(imageIndex) / 2;
			int height = reader.getHeight(imageIndex) / 2;
			Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 图片裁剪通用接口 
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param x 起始点x坐标
	 * @param y 起始点y坐标
	 * @param w 宽
	 * @param h 高
	 */
	public final static void cutImage(String src, String dest, int x, int y, int w, int h) {
		try {
			String suffix = src.substring(src.lastIndexOf(".") + 1);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) iterator.next();
			InputStream in = new FileInputStream(src);
			ImageInputStream iis = ImageIO.createImageInputStream(in);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, suffix, new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	/**
	 * 图片缩放
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 * @param w 宽
	 * @param h 高
	 */
	public final static void zoomImage(String src, String dest, int w, int h) {
		try {
			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);
			BufferedImage bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
			wr = w * 1.0 / bufImg.getWidth();
			hr = h * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 图片缩放(只压质量)
	 * @author ruan 
	 * @param src 图片源地址
	 * @param dest 图片目标地址
	 */
	public final static void zoomImage(String src, String dest) {
		try {
			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);
			BufferedImage bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.SCALE_SMOOTH);
			wr = bufImg.getWidth() * 1.0 / bufImg.getWidth();
			hr = bufImg.getHeight() * 1.0 / bufImg.getHeight();
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 生成二维码图片
	 * @author ruan 
	 * @param content 二维码内容
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param stream 图片输出流
	 * @param logoPic logo图片地址
	 */
	public final static void createQRCodeImage(String content, int width, int height, String logoPic, OutputStream stream) {
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, SystemUtil.getSystemCharsetString());
			hints.put(EncodeHintType.MARGIN, 0);
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}

			logoPic = StringUtil.getString(logoPic);
			if (!logoPic.isEmpty()) {
				image = addLogo2QRCode(image, new File(logoPic));
			}
			ImageIO.write(image, "png", stream);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 生成二维码图片
	 * @author ruan 
	 * @param content 二维码内容
	 * @param dest 图片生成地址
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param logoPic logo图片地址
	 */
	public final static void createQRCodeImage(String content, String dest, int width, int height, String logoPic) {
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, SystemUtil.getSystemCharsetString());
			hints.put(EncodeHintType.MARGIN, 0);
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			logoPic = StringUtil.getString(logoPic);
			if (!logoPic.isEmpty()) {
				image = addLogo2QRCode(image, new File(logoPic));
			}
			ImageIO.write(image, dest.substring(dest.lastIndexOf(".") + 1), new File(dest));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 给二维码图片加logo
	 * @author ruan 
	 * @param qrPic 二维码图片
	 * @param logoPic logo图片
	 */
	private final static BufferedImage addLogo2QRCode(BufferedImage image, File logoPic) {
		try {
			Graphics2D g = image.createGraphics();

			String sPath = logoPic.getAbsolutePath();
			String imageTmp = sPath.substring(0, sPath.lastIndexOf(".")) + "_tmp" + sPath.substring(sPath.lastIndexOf("."));
			zoomImage(logoPic.getAbsolutePath(), imageTmp, 100, 100);
			logoPic = new File(imageTmp);

			BufferedImage logo = ImageIO.read(logoPic);

			int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();

			// 计算图片放置位置
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;

			//开始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
			//g.setStroke(new BasicStroke(0));
			//g.setColor(Color.green);
			g.drawRect(x, y, widthLogo, heightLogo);
			g.dispose();
			image.flush();
			logo.flush();
			logoPic.delete();
			return image;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}