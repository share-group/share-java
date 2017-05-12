package com.share.core.protocol.protocol;

import java.util.HashMap;
import java.util.Map;

import com.share.core.exception.ProtocolDefineException;
import com.share.core.exception.ProtocolDuplicateException;

/**
 * 协议号，协议号名字的定义必需要和协议体的类名一样<br>
 * 两种定义方式，一种是数字，另外一种是字符串<br>
 * 数字定义方式：<br>
 * <li>协议号共8位：abxxxyyy</li>
 * <li>第一位：1.与客户端通讯 2.系统内部通讯 3.与后台通讯 4.第三方接口</li>
 * <li>第二位：1.请求 2.响应</li>
 * <li>第三、四、五位：模块编号，从001开始</li>
 * <li>最后三位：接口编号，从001开始</li>
 * <li>例如：11001001、32001001</li>
 * <br>
 * 字符串定义方式：<br>
 * 直接输入字符串即可，可以用作定义url
 */
public enum Protocol {
	/**
	 * 登录请求(11001001)
	 */
	ReqDemo(11001001),
	/**
	 * 登录响应(12001001)
	 */
	ResDemo(12001001);

	/**
	 * 协议号
	 */
	private int protocol;

	// 检查：1.协议号定义是否符合规则，2.协议号定义是否有重复，并给出提示
	static {
		int min = 10000000;
		Map<Object, Protocol> checkRepeatMap = new HashMap<Object, Protocol>();
		for (Protocol protocol : Protocol.values()) {
			int protocolID = protocol.getValue();

			// 协议号定义是否符合规则
			if (protocolID < min) {
				throw new ProtocolDefineException("illegal protocol: " + protocolID + ", must larger than " + min + "!");
			}

			// 协议号定义是否有重复
			if (checkRepeatMap.containsKey(protocolID)) {
				Protocol p = checkRepeatMap.get(protocolID);
				throw new ProtocolDuplicateException(protocol + " 的协议号 " + protocolID + " 与 " + p + " 的协议号 " + p.getValue() + " 重复了");
			}

			checkRepeatMap.put(protocolID, protocol);
		}
		checkRepeatMap = null;
	}

	/**
	 * 构造函数
	 * @param protocol 协议号
	 */
	private Protocol(int protocol) {
		this.protocol = protocol;
	}

	/**
	 * 获取协议号的值
	 * @return
	 */
	public int getValue() {
		return protocol;
	}

	/**
	 * toString
	 */
	public String toString() {
		return name();
	}
}