package com.share.core.annotation.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.RequestMapping;

import com.share.core.annotation.Menu;
import com.share.core.util.Check;

/**
 * 菜单注解解析器
 * @author ruan
 */
public final class MenuProcessor extends AnnotationProcessor {
	/**
	 * 排序比较器(为了页面美观，把短的排在前面)
	 */
	private final static Comparator<String> comparator = new Comparator<String>() {
		public int compare(String s1, String s2) {
			boolean isEnglish1 = Check.isEnglish(s1);
			boolean isEnglish2 = Check.isEnglish(s2);
			boolean isChinese1 = Check.isChinese(s1);
			boolean isChinese2 = Check.isChinese(s2);

			// 同为英文
			if (isEnglish1 && isEnglish2) {
				int len1 = s1.length();
				int len2 = s2.length();
				if (len1 > len2) {
					return 1;
				} else if (len1 < len2) {
					return -1;
				}
				return s1.compareTo(s2);
			}

			// 同为中文
			if (isChinese1 && isChinese2) {
				int len1 = s1.length();
				int len2 = s2.length();
				if (len1 > len2) {
					return 1;
				} else if (len1 < len2) {
					return -1;
				}
				return s1.compareTo(s2);
			}

			// 一边中文一边英文
			return s1.compareTo(s2);
		}
	};
	/**
	 * 菜单map
	 */
	private static TreeMap<String, ArrayList<String>> menuMap = new TreeMap<String, ArrayList<String>>(comparator);
	/**
	 * 要显示的菜单map
	 */
	private static TreeMap<String, ArrayList<String>> showMenuMap = new TreeMap<String, ArrayList<String>>(comparator);
	/**
	 * 菜单名 => URL map
	 */
	private static HashMap<String, String> menu2URLMap = new HashMap<String, String>();

	/**
	 * 私有构造函数，只能通过spring实例化
	 */
	private MenuProcessor() {
	}

	protected void resolve(Object object, Class<?> clazz) {
		throw new RuntimeException();
	}

	protected void resolve(Object object, Method method) {
		// 必须要有@RequestMapping和@Menu两个注解才可以加入
		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		Menu menu = method.getAnnotation(Menu.class);
		if (requestMapping == null || menu == null) {
			return;
		}

		synchronized (menuMap) {
			// 存入map，留待生成菜单时候用
			ArrayList<String> menuList = menuMap.get(menu.parentMenu().trim());
			if (menuList == null) {
				menuList = new ArrayList<String>();
				menuMap.put(menu.parentMenu().trim(), menuList);
			}
			if (!menu.isInner()) {
				ArrayList<String> showMenuList = showMenuMap.get(menu.parentMenu().trim());
				if (showMenuList == null) {
					showMenuList = new ArrayList<String>();
					showMenuMap.put(menu.parentMenu().trim(), showMenuList);
				}
				showMenuList.add(menu.menu().trim());
			}
			menuList.add(menu.menu().trim());
			Collections.sort(menuList, comparator);
			menu2URLMap.put(menu.menu().trim(), requestMapping.value()[0]);
			logger.info("adding menu {} => {}", requestMapping.value()[0], menu.menu().trim());
		}
	}

	/**
	 * 获取所有菜单
	 */
	public TreeMap<String, ArrayList<String>> getAllMenu() {
		return menuMap;
	}

	/**
	 * 获取所有要显示的菜单
	 */
	public TreeMap<String, ArrayList<String>> getShowMenuMap() {
		return showMenuMap;
	}

	/**
	 * 获取某个模块的菜单
	 * @author ruan
	 * @param moduleName
	 * @return
	 */
	public ArrayList<String> getMenuByModule(String moduleName) {
		return menuMap.get(moduleName.trim());
	}

	/**
	 * 获取所有url
	 */
	public HashMap<String, String> getAllUrl() {
		return menu2URLMap;
	}

	/**
	 * 根据菜单名获取url
	 * @param menu
	 */
	public String getUrlByMenu(String menu) {
		return menu2URLMap.get(menu);
	}

	/**
	 * 获取排序比较器
	 */
	public Comparator<String> getComparator() {
		return comparator;
	}
}