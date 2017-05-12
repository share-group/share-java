package com.share.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * ip类
 */
public final class Ip {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Ip.class);

	/**
	 * 把ip转为10进制长整型
	 * 
	 * @author ruan 2013-1-21
	 * @param strip
	 * @return
	 */
	public final static long ip2Long(String strip) {
		String[] ipArr = strip.split("\\.");
		return (Long.parseLong(ipArr[0]) << 24) + (Long.parseLong(ipArr[1]) << 16) + (Long.parseLong(ipArr[2]) << 8) + Long.parseLong(ipArr[3]);
	}

	/**
	 * 把10进制长整型转为ip
	 * 
	 * @author ruan 2013-1-21
	 * @param longip
	 * @return
	 */
	public final static String long2Ip(long longip) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(longip >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longip & 0x00ffffff) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longip & 0x0000ffff) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longip & 0x000000ff));
		return sb.toString();
	}

	/**
	 * 获取本地ip
	 * 
	 * @author ruan 2013-7-22
	 * @return
	 */
	public final static String local() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		return "0.0.0.0";
	}

	/**
	* 获取指定开头的IP，不存在就返回0.0.0.0
	*/
	public static String getHostAddress(String prefix) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
				while (addresses.hasMoreElements()) {
					String address = addresses.nextElement().getHostAddress();
					if (address.startsWith(prefix)) {
						return address;
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return "0.0.0.0";
	}

	/**
	 * 获取局域网IPv4地址、虚拟机IPv4地址
	 * 优先返回指定前缀
	 * 如下只返回：
	 * 192.168.1.218
	 * 192.168.206.1
	 * 192.168.149.1
	 * 
	 * 
	 * 	127.0.0.1
		0:0:0:0:0:0:0:1
		fe80:0:0:0:0:100:7f:fffe%net4
		192.168.1.218
		fe80:0:0:0:f018:9543:f9f5:2e9%eth3
		fe80:0:0:0:0:5efe:c0a8:1da%net5
		192.168.206.1
		fe80:0:0:0:1d88:158b:7b37:e5d3%eth4
		fe80:0:0:0:0:5efe:c0a8:ce01%net6
		192.168.149.1
		fe80:0:0:0:1425:b782:904f:3d6e%eth5
		fe80:0:0:0:0:5efe:c0a8:9501%net7
	 * @return
	 */

	public static String getValidIPAddress(String prefix) {
		List<String> ips = Lists.newArrayList();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ia = addresses.nextElement();
					if (ia.isSiteLocalAddress() && !ia.isLoopbackAddress() && ia.getHostAddress().indexOf(":") == -1) {
						ips.add(ia.getHostAddress());

					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		if (!ips.isEmpty()) {
			for (String ip : ips) {
				if (ip.startsWith(prefix)) {
					return ip;
				}
			}

			return ips.get(0);
		}

		return "0.0.0.0";
	}
}