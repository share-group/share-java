package com.share.core.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.share.core.util.RandomUtil;
import com.share.core.util.StringUtil;

/**
 * 验证码服务
 * @author ruan
 */
public class CaptchaService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CaptchaService.class);
	/**
	 * 随机字符串数组
	 */
	private final static String[] randomStringArr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	/**
	 * 验证码暂存
	 */
	private LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(120, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
		public String load(String key) throws Exception {
			return null;
		}
	});

	/**
	 * 保存验证码
	 */
	private void store(HttpServletRequest request, String yzm) {
		cache.put(request.getSession().getId(), StringUtil.getString(yzm));
	}

	/**
	 * 检查验证码
	 * @return 1-正确，0-错误，-1验证码超时
	 */
	public int check(HttpServletRequest request, String yzm) {
		String key = request.getSession().getId();
		String sessionValue = cache.getIfPresent(key);
		if (sessionValue == null) {
			return -1;
		}
		return yzm.equalsIgnoreCase(sessionValue) ? 1 : 0;
	}

	/**
	 * 销毁验证码
	 */
	public void destroy(HttpServletRequest request) {
		cache.invalidate(request.getSession().getId());
	}

	/**
	 * 获取一张随机字符串数字的图片
	 * @param width
	 * @param height
	 * @param num
	 * @param request
	 * @param response
	 */
	public void getRandomStringImg(int width, int height, int num, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/png");

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		//g.setColor(new Color(RandomUtil.rand(1,100), RandomUtil.rand(1,150), RandomUtil.rand(1,200)));
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(RandomUtil.rand(1, 100), RandomUtil.rand(1, 150), RandomUtil.rand(1, 200)));

		// 生成验证码
		String numberStr = getRandomString(num);
		store(request, numberStr);

		// 绘图
		int length = numberStr.length();
		for (int i = 0; i < length; i++) {
			String Str = numberStr.substring(i, i + 1);
			g.setFont(new Font("Atlantic Inline", Font.BOLD, RandomUtil.rand(40, 45)));
			g.setColor(new Color(RandomUtil.rand(1, 255), RandomUtil.rand(1, 255), RandomUtil.rand(1, 255)));
			if (i == 0) {
				g.drawString(Str, 3, RandomUtil.rand(35, 40));
			} else {
				g.drawString(Str, i * 25, RandomUtil.rand(35, 40));
			}
		}

		// 设置干扰线
		for (int i = 0; i < 8; i++) {
			g.drawLine(RandomUtil.rand(1, width), RandomUtil.rand(1, height), RandomUtil.rand(1, width), RandomUtil.rand(1, height));
			g.setColor(new Color(RandomUtil.rand(1, 255), RandomUtil.rand(1, 255), RandomUtil.rand(1, 255)));
		}

		// 设置干扰点
		for (int i = 0; i < 50; i++) {
			g.drawOval(RandomUtil.rand(1, width), RandomUtil.rand(1, height), 1, 1);
			g.setColor(new Color(RandomUtil.rand(1, 255), RandomUtil.rand(1, 255), RandomUtil.rand(1, 255)));
		}

		// 图象生效
		g.dispose();

		// 输出图象到页面
		try {
			ImageIO.write(image, "PNG", response.getOutputStream());
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取一串随机字符串
	 * @param num 字符串数量
	 */
	private final static String getRandomString(int num) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < num; i++) {
			str.append(randomStringArr[RandomUtil.rand(0, randomStringArr.length - 1)]);
		}
		return str.toString();
	}
}