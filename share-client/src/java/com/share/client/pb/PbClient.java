package com.share.client.pb;

import io.netty.channel.ChannelHandlerContext;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.protobuf.GeneratedMessageLite;
import com.share.core.client.SocketClient;
import com.share.core.interfaces.ShareFrame;
import com.share.core.protocol.protobuf.demo.Demo.ReqDemo;
import com.share.core.util.FileSystem;

@SuppressWarnings("serial")
public class PbClient extends ShareFrame {
	private static JTextArea input = null;
	private static JTextArea result = null;
	private static JScrollPane scroll = null;
	private JTextField hostTextField = null;
	private JTextField emailTextField = null;
	public static HashMap<Integer, Object> protocolresponseMap = new HashMap<Integer, Object>();
	private JComboBox<String> change = null;
	public static ConcurrentHashMap<Integer, Long> requestTimeMap = new ConcurrentHashMap<Integer, Long>();
	//public static ExecutorService executor;
	public static ChannelHandlerContext ctx;
	public static long t = 0;
	public static HashMap<Short, Error> errorMap = new HashMap<Short, Error>();

	/**
	 * @author ruan 2013-2-6
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String dir = FileSystem.getSystemDir() + "/../share-core/";
		//ShareConfig.init(ServerType.Client, dir);
		//executor = Executors.newFixedThreadPool(ShareConfig.cpuCores);
		new PbClient();
	}

	public Field[] fieldlist;

	public PbClient() throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException {
		setTitle("share-client pb版");
		email();
		host();
		//listProtocol();
		input();
		button();
		result();
		//fillupInput(change.getSelectedItem().toString());
		addPanel();
	}

	private void email() {
		JPanel panel = new JPanel();
		emailTextField = new JTextField(14);
		emailTextField.setText("ruanzhijun@qq.com");
		JLabel emailLabel = new JLabel("email：");
		panel.add(emailLabel);
		panel.add(emailTextField);
		panel.setBounds(-30, 0, 280, 25);
		mainFrame.add(panel);
	}

	private void host() {
		JPanel panel = new JPanel();
		hostTextField = new JTextField(14);
		hostTextField.setText("127.0.0.1:8080");
		JLabel hostLabel = new JLabel("host：");
		JButton connectButton = new JButton("连接");
		panel.add(hostLabel);
		panel.add(hostTextField);
		panel.add(connectButton);
		panel.setBounds(0, 25, 280, 35);
		connectButton.addActionListener(new connectButtonActionListener());
		mainFrame.add(panel);
	}

	private void listProtocol() {
		JPanel protocol = new JPanel();
		change = new JComboBox<String>();
		ArrayList<Short> requestList = new ArrayList<Short>();
		Collections.sort(requestList);
		change.addItemListener(new changeItemListener());
		change.setBounds(90, 63, 200, 20);
		mainFrame.add(change);
		JLabel labelProtocol = new JLabel("请选择协议：");
		protocol.add(labelProtocol);
		protocol.setBounds(0, 60, 90, 30);
		mainFrame.add(protocol);
	}

	private void input() {
		JLabel title = new JLabel("传入的参数：");
		input = new JTextArea();
		title.setBounds(8, 62, 280, 30);
		input.setBounds(8, 94, 280, 520);
		mainFrame.add(title);
		mainFrame.add(input);
	}

	private void button() {
		JButton send = new JButton("发送");
		JButton clear = new JButton("清空");
		send.setBounds(8, 620, 60, 30);
		clear.setBounds(100, 620, 60, 30);
		send.addActionListener(new sendButtonActionListener());
		clear.addActionListener(new clearButtonActionListener());
		mainFrame.add(send);
		mainFrame.add(clear);
	}

	private void result() {
		result = new JTextArea();
		result.setLineWrap(true);
		scroll = new JScrollPane(result);
		scroll.setBounds(300, 10, 680, 605);
		JButton resultButton = new JButton("清空结果");
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		resultButton.setBounds(300, 620, 90, 30);
		resultButton.addActionListener(new resultButtonActionListener());
		mainFrame.add(resultButton);
		mainFrame.add(scroll);
	}

	private void cleanInput() {
		input.setText("");
	}

	private void cleanResult() {
		result.setText("");
	}

	private class changeItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() != ItemEvent.SELECTED) {
				return;
			}
			cleanInput();
			fillupInput(e.getItem().toString());
		}
	}

	private void fillupInput(String clazzName) {
		try {
			Object clazz = Class.forName("protocol.request." + clazzName.split("-")[1]).newInstance();
			Field[] fileds = clazz.getClass().getFields();
			for (Field field : fileds) {
				Class<?> cla = field.getType();
				if (field.getName().equals("session_id") || field.getName().equals("protocol")) {
					continue;
				}
				if (field.getName().equals("protocol") && cla == int.class) {
					input.append("protocol=" + field.getInt(clazz));
				} else {
					input.append(field.getName());
				}
				input.append("=\n");
			}
			int len = input.getText().length();
			if (len >= 2) {
				input.setText(input.getText().substring(0, len - 2));
				input.append("=");
			}
			fieldlist = fileds;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	private class resultButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cleanResult();
		}
	}

	private class clearButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cleanInput();
		}
	}

	private class sendButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (ctx == null) {
				error("socket 未连接！");
				return;
			}
			//String[] selectArr = change.getSelectedItem().toString().split("-");
			ReqDemo.Builder req = ReqDemo.newBuilder();
			req.setId(10);
			req.setEmail("jljhljk");
			req.setName("dfdf");
//
//			try {
//				for (String column : input.getText().split("\n")) {
//					String[] arr = column.split("=");
//					if (arr.length <= 1) {
//						continue;
//					}
//					for (Field field : fieldlist) {
//						if (!field.getName().equals(arr[0])) {
//							continue;
//						}
//						field.setAccessible(true);
//						Class<?> cla = field.getType();
//						if (cla == int.class) {
//							field.setInt(req, Integer.parseInt(arr[1]));
//						} else if (cla == long.class) {
//							field.setLong(req, Long.parseLong(arr[1]));
//						} else if (cla == short.class) {
//							field.setShort(req, Short.parseShort(arr[1]));
//						} else if (cla == double.class) {
//							field.setDouble(req, Double.parseDouble(arr[1]));
//						} else if (cla == float.class) {
//							field.setFloat(req, Float.parseFloat(arr[1]));
//						} else if (cla == boolean.class) {
//							field.setBoolean(req, Boolean.parseBoolean(arr[1]));
//						} else if (cla == char.class) {
//							field.setChar(req, arr[1].toCharArray()[0]);
//						} else if (cla == byte[].class) {
//							byte[] tmp = (byte[]) field.get(req);
//							if (tmp == null) {
//								tmp = new byte[arr[1].length()];
//								StringUtil.copyToBytes(arr[1], tmp);
//								field.set(req, tmp);
//							} else {
//								StringUtil.copyToBytes(arr[1], tmp);
//							}
//						}
//					}
//				}
//			} catch (IllegalAccessException e1) {
//				e1.printStackTrace();
//			}

			write(req.build());
		}
	}

	private void write(GeneratedMessageLite req) {
		ctx.channel().writeAndFlush(req);
		t = System.nanoTime();
	}

	private class connectButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] arr = hostTextField.getText().split(":");
			connect(arr[0], Integer.parseInt(arr[1]));
		}
	}

	private final static void connect(String host, int port) {
		SocketClient socketClient = new SocketClient(host, port, 10, new PbSocketlientInitializer());
		socketClient.connect();
	}

	public final static void echo(Object str) {
		if (str == null) {
			return;
		}
		result.append(str + "\r\n");
		Point p = new Point();
		p.setLocation(0, result.getLineCount() * 1000);
		scroll.getViewport().setViewPosition(p);
	}

}
