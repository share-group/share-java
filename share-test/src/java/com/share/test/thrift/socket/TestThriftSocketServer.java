package com.share.test.thrift.socket;

import org.apache.thrift.protocol.TCompactProtocol;

import com.share.core.util.FileSystem;
import com.share.soa.thrift.impl.ShareObjectServiceHandler;
import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.server.ThriftSocketServer;

public class TestThriftSocketServer {

	public static void main(String[] args) {
		ThriftSocketServer thriftSocketServer = new ThriftSocketServer(FileSystem.getPropertyInt("thrift.socket.port"), ShareObjectService.class, TCompactProtocol.class, ShareObjectServiceHandler.class);
		thriftSocketServer.start();
	}

}
