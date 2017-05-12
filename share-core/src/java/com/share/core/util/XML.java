package com.share.core.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * xml类
 */
public final class XML {
	private XML() {

	}

	/**
	 * 把xml转化为Document
	 * 
	 * @param file
	 *            文件路径
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private final static Document getDoc(String file) throws SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(new File(file));
	}

	/**
	 * 读取xml文件
	 * 
	 * @param file
	 *            文件路径
	 * @param tagName
	 *            节点名称
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public final static HashMap<String, String> read(String file, String tagName)
			throws ParserConfigurationException, SAXException, IOException {
		NodeList list = getDoc(file).getElementsByTagName(tagName);
		HashMap<String, String> data = new HashMap<String, String>();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			data.put(node.getAttributes().getNamedItem("name").getNodeValue(),
					node.getAttributes().getNamedItem("value").getNodeValue());
		}
		return data;
	}

	/**
	 * 修改xml文档
	 * 
	 * @param path
	 *            文件路径
	 * @param modifyContent
	 *            要修改的内容
	 * @throws DocumentException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public final static void editXML(String file, String tagName,
			HashMap<String, String> modifyContent) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {
		Document doc = getDoc(file);
		NodeList list = doc.getElementsByTagName(tagName);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			NamedNodeMap attrList = node.getAttributes();
			String key = attrList.getNamedItem("name").getNodeValue();
			if (modifyContent.containsKey(key)) {
				attrList.getNamedItem("value").setNodeValue(
						modifyContent.get(key));
			}
		}
		doc2XmlFile(doc, file);
	}

	/**
	 * 创建xml文件
	 * 
	 * @param file
	 *            文件路径
	 * @param data
	 *            xml文件的内容
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public final static void createXML(String file, String tagName,
			HashMap<String, Object> data) throws ParserConfigurationException,
			TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element map = doc.createElement(tagName);
		doc.appendChild(map);
		for (Entry<String, Object> e : data.entrySet()) {
			Element layer = doc.createElement("option");
			layer.setAttribute("name", e.getKey());
			layer.setAttribute("value", e.getValue().toString());
			map.appendChild(layer);
		}
		doc2XmlFile(doc, file);
	}

	/**
	 * 把doc文档保存为xml文件形式
	 * 
	 * @param document
	 * @param file
	 * @throws TransformerException
	 */
	private static void doc2XmlFile(Document document, String file)
			throws TransformerException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(file));
		transformer.transform(source, result);
	}
}