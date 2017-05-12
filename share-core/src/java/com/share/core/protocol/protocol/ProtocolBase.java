package com.share.core.protocol.protocol;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.slf4j.LoggerFactory;

import com.share.core.util.JSONObject;

/**
 * 协议父类
 */
public abstract class ProtocolBase {
	/**
	 * 协议号
	 */
	private final int protocol = Protocol.valueOf(getClass().getSimpleName()).getValue();

	/**
	 * 从buffer转成对象
	 * @param buffer
	 */
	public abstract void loadFromBuffer(ByteBuf buffer);

	/**
	 * 从对象转成buffer
	 * @param buffer
	 */
	public abstract void convert2Buffer(ByteBuf buffer);

	/**
	 * 获取协议号
	 */
	public int getProtocol() {
		return protocol;
	}

	/**
	 * 把结构体转换为对象
	 * 
	 * @author ruan
	 * @return
	 */
	private LinkedHashMap<String, Object> toObject() {
		LinkedHashMap<String, Object> string = new LinkedHashMap<String, Object>();
		try {
			for (Field field : getClass().getDeclaredFields()) {
				Class<?> cla = field.getType();
				field.setAccessible(true);
				if (cla.equals(int.class)) {
					string.put(field.getName(), field.getInt(this));
				} else if (cla.equals(int[].class)) {
					int[] intArray = (int[]) field.get(this);
					if (intArray != null) {
						ArrayList<Integer> array = new ArrayList<Integer>();
						for (int i : intArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(long.class)) {
					string.put(field.getName(), field.getLong(this));
				} else if (cla.equals(long[].class)) {
					long[] longArray = (long[]) field.get(this);
					if (longArray != null) {
						ArrayList<Long> array = new ArrayList<Long>();
						for (long i : longArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(short.class)) {
					string.put(field.getName(), field.getShort(this));
				} else if (cla.equals(short[].class)) {
					short[] shortArray = (short[]) field.get(this);
					if (shortArray != null) {
						ArrayList<Short> array = new ArrayList<Short>();
						for (short i : shortArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(double.class)) {
					string.put(field.getName(), field.getDouble(this));
				} else if (cla.equals(double[].class)) {
					double[] doubleArray = (double[]) field.get(this);
					if (doubleArray != null) {
						ArrayList<Double> array = new ArrayList<Double>();
						for (double i : doubleArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(float.class)) {
					string.put(field.getName(), field.getFloat(this));
				} else if (cla.equals(float[].class)) {
					float[] floatArray = (float[]) field.get(this);
					if (floatArray != null) {
						ArrayList<Float> array = new ArrayList<Float>();
						for (float i : floatArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(boolean.class)) {
					string.put(field.getName(), field.getBoolean(this));
				} else if (cla.equals(boolean[].class)) {
					boolean[] booleanArray = (boolean[]) field.get(this);
					if (booleanArray != null) {
						ArrayList<Boolean> array = new ArrayList<Boolean>();
						for (boolean i : booleanArray) {
							array.add(i);
						}
						string.put(field.getName(), array);
					}
				} else if (cla.equals(byte.class)) {
					string.put(field.getName(), field.getByte(this));
				} else if (cla.equals(byte[].class)) {
					byte[] byteArray = (byte[]) field.get(this);
					if (byteArray != null) {
						string.put(field.getName(), new String(byteArray).trim());
					}
				} else if (cla.equals(char.class)) {
					string.put(field.getName(), field.getChar(this));
				} else if (cla.equals(String.class)) {
					string.put(field.getName(), field.get(this));
				} else {
					Object value = field.get(this);
					if (value instanceof ProtocolBase) {
						string.put(field.getName(), ((ProtocolBase) field.get(this)).toObject());
					} else if (value instanceof ProtocolBase[]) {
						ProtocolBase[] protocolBaseArray = (ProtocolBase[]) field.get(this);
						if (protocolBaseArray != null) {
							ArrayList<Object> array = new ArrayList<Object>();
							for (ProtocolBase i : protocolBaseArray) {
								array.add(i.toObject());
							}
							string.put(field.getName(), array);
						}
					} else {
						byte[] byteArray = (byte[]) field.get(this);
						if (byteArray != null) {
							string.put(field.getName(), new String(byteArray).trim());
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LoggerFactory.getLogger(getClass()).error("", e);
		}
		return string;
	}

	/**
	 * hashCode
	 */
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * 字符串方法
	 */
	public String toString() {
		LinkedHashMap<String, Object> object = new LinkedHashMap<String, Object>();
		object.put("protocol", getProtocol());
		object.putAll(toObject());
		return JSONObject.encode(object);
	}
}