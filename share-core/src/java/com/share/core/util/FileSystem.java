package com.share.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.share.core.util.SortUtil.Order;

/**
 * 文件系统
 */
public final class FileSystem {
	private static String projectName = "";
	private final static ClassLoader classLoader = FileSystem.class.getClassLoader();
	private final static Logger logger = LoggerFactory.getLogger(FileSystem.class);
	private final static String[] sizes = new String[] { "Byte", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
	private final static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	private final static boolean isWindows = System.getProperty("os.name").indexOf("Windows") != -1;
	private final static boolean isMacOSX = System.getProperty("os.name").indexOf("Mac OS X") != -1;
	private final static boolean isLinux = !isWindows && !isMacOSX;
	private static Properties property = new Properties();
	private final static String systemDir = classLoader.getResource("").toString().replace("file:", "").replace("/etc", "").trim() + (StringUtil.getString(projectName).isEmpty() ? "" : projectName + "/");
	private static String pid;
	private static int serverId;

	static {
		// 自动加载properties文件
		loadProperties();

		// 限制系统key的长度必须超过32位
		String systemKey = getPropertyString("system.key");
		if (systemKey.length() < 32) {
			logger.error("the ${system.key} length must >= 32!!!");
			System.exit(0);
		}

		// 初始化项目名
		if (FileSystem.isWindows() || FileSystem.isMacOSX()) {
			projectName = FileSystem.getSystemDir().replace("/bin/", "");
			projectName = projectName.substring(projectName.lastIndexOf("/") + 1);
		} else {
			projectName = StringUtil.getString(System.getProperty("project"));
		}

		// 初始化进程id
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		pid = StringUtil.getString(runtime.getName());
		int index = pid.indexOf("@");
		if (index != -1) {
			pid = StringUtil.getString(pid.substring(0, index));
		}

		// 生成serverId
		StringBuilder serverString = new StringBuilder();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
				while (addresses.hasMoreElements()) {
					serverString.append(StringUtil.getString(addresses.nextElement().getHostAddress()));
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
		serverId = serverString.toString().hashCode();
	}

	private FileSystem() {
	}

	/**
	 * 获取系统根目录
	 */
	public final static String getSystemDir() {
		if (isWindows) {
			return systemDir.substring(1);
		}
		return systemDir;
	}

	/**
	 * 获取项目名称
	 */
	public final static String getProjectName() {
		return projectName;
	}

	/**
	 * 获取进程id
	 */
	public final static String getPID() {
		return pid;
	}

	/**
	 * 获取本服唯一标识
	 */
	public final static int getServerId() {
		return serverId;
	}

	/**
	 * 判断是否为windows系统
	 */
	public final static boolean isWindows() {
		return isWindows;
	}

	/**
	 * 判断是否为是不是OS X系统
	 */
	public static boolean isMacOSX() {
		return isMacOSX;
	}

	/**
	 * 判断是否为是不是linux系统
	 */
	public static boolean isLinux() {
		return isLinux;
	}

	/**
	 * 列出指定文件夹的内容
	 * @author ruan
	 * @param dir 目录路径
	 * @return
	 */
	public final static String[] ls(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			return file.list();
		}
		return new String[0];
	}

	/**
	 * 列出指定文件夹内所有的jar文件
	 * @author ruan
	 * @param dir 目录路径
	 * @return jar文件全路径数组
	 */
	public final static List<String> lsJAR(String dir) {
		List<String> jarList = new ArrayList<String>();
		lsJAR(dir, jarList);
		return jarList;
	}

	/**
	 * 列出指定文件夹内所有的jar文件
	 * @author ruan
	 * @param dir 目录路径
	 * @param jar文件全路径数组
	 */
	private final static void lsJAR(String dir, List<String> jarList) {
		for (String file : ls(dir)) {
			File jar = new File(dir + "/" + file);
			if (jar.isDirectory()) {
				lsJAR(jar.getAbsolutePath(), jarList);
			} else {
				String absolutePath = jar.getAbsolutePath();
				if (!absolutePath.endsWith(".jar")) {
					continue;
				}
				jarList.add(absolutePath);
			}
		}
	}

	/**
	 * 写入文件(完全自定义文件格式和写入内容)
	 * 
	 * @param filename 文件的完整路径
	 * @param data 要写入的数据
	 * @param flag 是否追加(true追加，false不追加)
	 * @return boolean
	 */
	public final static boolean write(String filename, String data, boolean flag) {
		int i = filename.lastIndexOf("/");
		File file = new File(filename.substring(0, i));
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename, flag);
			Writer writer = new OutputStreamWriter(fileOutputStream, "utf-8");
			writer.write(data);
			writer.flush();
			writer.close();
			fileOutputStream.close();
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	/**
	 * 删除某个文件或目录
	 * 
	 * @param filename 文件名或目录
	 * @return 0：删除成功，-1：指定文件夹不存在，-2：指定文件不存在
	 */
	public final static int delete(String filename) {
		File file = new File(filename);
		if (file.isDirectory()) {
			String[] dir = ls(filename);
			for (int i = 0; i < dir.length; i++) {
				new File(filename + "/" + dir[i]).delete();
				if (file.isDirectory()) {
					delete(filename + "/" + dir[i]);
				}
			}
			if (file.delete()) {
				return 0;
			} else {
				return -1;
			}
		} else if (file.isFile()) {
			if (file.delete()) {
				return 0;
			} else {
				return -2;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 判断文件或者文件夹是否存在
	 * @param path 文件或者文件夹的完整路径
	 */
	public final static boolean exists(String filename) {
		return new File(filename).exists();
	}

	/**
	 * 读取文件类容
	 * @author ruan
	 * @param filename 文件的完整路径
	 */
	public final static String read(String filename) {
		if (filename == null || filename.isEmpty()) {
			return "";
		}
		try {
			return read(new FileInputStream(filename));
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 通过文件流读取文件
	 * @param inputStream 文件流
	 */
	public final static String read(InputStream inputStream) {
		try {
			String text = null;
			StringBuilder sb = new StringBuilder();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, SystemUtil.getSystemCharsetString());
			BufferedReader input = new BufferedReader(inputStreamReader);
			while ((text = input.readLine()) != null) {
				sb.append(text);
				sb.append("\n");
			}
			inputStream.close();
			inputStreamReader.close();
			input.close();
			return sb.toString().trim();
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 读取jar包内指定类型的文件
	 * @param jarFileName jar文件路径
	 * @param readFile jar包内文件的路径
	 */
	public final static List<String> readFileTypeInJAR(String jarFileName, String fileType) {
		List<String> fileList = new ArrayList<String>();
		try {
			JarFile jarFile = new JarFile(jarFileName);// 读入jar文件
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				JarEntry entry = entries.nextElement();
				String name = entry.getName().trim();
				if (name.indexOf("/") > -1 || name.indexOf("core") > -1 || !name.endsWith(fileType)) {
					continue;
				}
				fileList.add(name);
			}
			jarFile.close();

		} catch (IOException e) {
			logger.error("", e);
		}
		return fileList;
	}

	/**
	 * 计算文件大小
	 * 
	 * @author ruan
	 * @param filename 文件的完整路径
	 * @return
	 */
	public final static String getSize(String filename) {
		long size = new File(filename).length();
		if (size <= 0) {
			return "0 " + sizes[0];
		}
		return getSize(size);
	}

	/**
	 * 根据传进来的大小选择最适合的单位
	 * @param size
	 */
	public final static String getSize(double size) {
		int i = (int) Math.floor(Math.log(size) / Math.log(1024));
		return String.format("%s %s", decimalFormat.format(size / Math.pow(1024, i)), sizes[i]);
	}

	/**
	 * 保存文件
	 * @param stream 文件流
	 * @param filename 文件保存路径
	 */
	public final static boolean saveFileFromInputStream(InputStream stream, String filename) {
		try {
			FileOutputStream fs = new FileOutputStream(filename);
			byte[] buffer = new byte[1024];
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	/**
	 * 创建文件夹
	 * @param path 文件夹地址
	 */
	public final static void mkdir(String path) {
		File file = new File(path);
		if (file.exists()) {
			return;
		}
		file.mkdirs();
	}

	/**
	 * 重命名文件
	 * @param file 原文件
	 * @param newname 新文件名
	 * @return 新文件全路径
	 */
	public final static String rename(File file, String newname) {
		if (file == null) {
			return null;
		}
		if (file.getName().equals(newname)) {
			return file.getAbsolutePath().trim();
		}

		String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
		File newFile = new File(file.getParent() + "/" + newname + "." + suffix);
		file.renameTo(newFile);
		return newFile.getAbsolutePath().trim();
	}

	/**
	 * 复制文件
	 * @param file1  源文件
	 * @param file2  目标文件
	 */
	public final static void copy(String file1, String file2) {
		try {
			int byteread = 0;
			InputStream inStream = new FileInputStream(file1); //读入原文件
			FileOutputStream fs = new FileOutputStream(file2);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			inStream.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 加载一个property文件
	 * @author ruan
	 * @param file property文件的完整路径
	 * @return
	 */
	public final static synchronized Properties loadProperties(String file) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader in = new InputStreamReader(fileInputStream, "utf-8");
			Properties properties = new Properties();
			properties.load(in);
			fileInputStream.close();
			in.close();
			return properties;
		} catch (IOException e) {
			logger.error("", e);
			return null;
		} finally {
			logger.warn("load properties: {}", file);
		}
	}

	/**
	 * 获取整个property文件
	 */
	public final static Properties getProperty() {
		return property;
	}

	/**
	 * 自动发现指定路径内property文件并自动加载
	 * @param path
	 */
	private final static synchronized void loadProperties0(String path) {
		String[] fileList = ls(path);
		if (fileList.length <= 0) {
			return;
		}
		for (String file : fileList) {
			if (file.lastIndexOf(".properties") <= -1) {
				continue;
			}
			if ("config.properties".equals(file)) {
				// 这两个文件一定加载，所以不用再加载了
				continue;
			}
			property.putAll(loadProperties(path + file));
		}
	}

	/**
	 * 自动发现property文件并自动加载
	 */
	private final static synchronized void loadProperties() {
		try {
			property.putAll(loadProperties(classLoader.getResource("config.properties").toString().replace("file:", "").trim()));
		} catch (Exception e) {
			logger.error("can not find config.properties", e);
			System.exit(0);
		}
		String path;
		if (isWindows) {
			path = classLoader.getResource("config.properties").toString().replace("file:", "").replace("config.properties", "").trim();
		} else {
			path = classLoader.getResource("").toString().replace("file:", "");
		}
		loadProperties0(path);

	}

	/**
	 * Property转成有序的list
	 * @author ruan
	 * @param order 排序方式
	 * @return
	 */
	public final static List<Object> property2List(Order order) {
		return property2List(property, order);
	}

	/**
	 * Property转成有序的list
	 * @author ruan
	 * @param property property文件
	 * @param order 排序方式
	 * @return
	 */
	public final static List<Object> property2List(Properties Property, Order order) {
		HashMap<Object, Object> configMap = new HashMap<Object, Object>();
		for (Entry<Object, Object> e : Property.entrySet()) {
			configMap.put(e.getKey(), e.getValue());
		}
		return SortUtil.sortMap(configMap, order);
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static String getPropertyString(String key) {
		return StringUtil.getString(property.getProperty(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static byte getPropertyByte(String key) {
		return StringUtil.getByte(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static short getPropertyShort(String key) {
		return StringUtil.getShort(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static int getPropertyInt(String key) {
		return StringUtil.getInt(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static float getPropertyFloat(String key) {
		return StringUtil.getFloat(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static double getPropertyDouble(String key) {
		return StringUtil.getDouble(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static boolean getPropertyBoolean(String key) {
		return StringUtil.getBoolean(getPropertyString(key));
	}

	/**
	 * 是否有此配置
	 * @param key 键
	 * @return
	 */
	public final static boolean propertyHas(String key) {
		Object o = property.get(key);
		if (o == null) {
			return false;
		}
		return !o.toString().trim().isEmpty();
	}

	/**
	 * 自动发现spring配置文件并自动加载
	 */
	@SuppressWarnings("resource")
	public final static synchronized void loadSpringConfig() {
		String file = FileSystem.getProjectName() + ".xml";
		logger.warn("load spring config: {}", file);
		new ClassPathXmlApplicationContext("classpath:" + file).registerShutdownHook();
	}

	/**
	 * 加载pem文件
	 * @param filename pem文件名
	 */
	public static String loadPem(String filename) {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader in = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(in);
			br.readLine();
			String s = br.readLine();
			while (s.charAt(0) != '-') {
				sb.append(s);
				s = br.readLine();
			}
			inputStream.close();
			in.close();
			br.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		return sb.toString();
	}

	/**
	 * 获取一个包下所有类的数量和方法数量
	 * @author ruan
	 * @param packageName
	 */
	public final static Map<String, Object> getPackageClassAndMethodNum(String packageName) {
		Set<Class<?>> classSet = SystemUtil.getClasses(packageName);

		String classNum = "class num";
		String methodNum = "method total num";
		String methodDistribute = "method distribute";

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> methodDistributeMap = new HashMap<String, Object>();
		result.put("package name", packageName);
		result.put(classNum, classSet.size());

		try {
			for (Class<?> clazz : classSet) {
				int classMethodNum = clazz.getMethods().length;
				methodDistributeMap.put(clazz.getName(), classMethodNum);
				result.put(methodNum, StringUtil.getInt(result.get(methodNum)) + classMethodNum);
			}

			result.put(methodDistribute, methodDistributeMap);
		} catch (Exception e) {
			logger.error("", e);
			result.clear();
		}
		return result;
	}

	/**
	 * 读取csv文件
	 * @param path 文件路径
	 * @return
	 */
	public final static List<List<String>> readCSV(String path) {
		List<List<String>> content = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(path));
			content = readCSV(fileReader);
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return content;
	}

	/**
	 * 读取csv文件
	 * @param reader 文件流
	 * @return
	 */
	public final static List<List<String>> readCSV(Reader reader) {
		List<List<String>> content = new ArrayList<>();
		CsvListReader csvListReader = null;
		try {
			csvListReader = new CsvListReader(reader, CsvPreference.EXCEL_PREFERENCE);
			List<String> line = new ArrayList<String>();
			while ((line = csvListReader.read()) != null) {
				content.add(line);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (csvListReader != null) {
					csvListReader.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return content;
	}

	/**
	  * 写入csv文件
	  * @param path 文件路径
	  * @param header 头部
	  * @param content 内容
	  */
	public final static void writeCSV(String path, String[] header, List<String[]> content) {
		FileWriter fileWriter = null;
		CsvListWriter writer = null;
		try {
			fileWriter = new FileWriter(new File(path));
			writer = new CsvListWriter(fileWriter, CsvPreference.EXCEL_PREFERENCE);
			writer.writeHeader(header);
			for (String[] str : content) {
				writer.write(str);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}