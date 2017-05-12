package com.share.soa.thrift.impl;

import org.apache.thrift.TException;

import com.share.core.interfaces.BaseHandler;
import com.share.soa.thrift.protocol.ShareObject;
import com.share.soa.thrift.protocol.ShareObjectService.Iface;

public class ShareObjectServiceHandler extends BaseHandler implements Iface {

	@Override
	public int test(int a) throws TException {
		logger.info(a + "\ttest");
		return a;
	}

	@Override
	public String testString(int n, boolean aaa) throws TException {
		logger.info("testString");
		return null;
	}

	@Override
	public String shareObject(ShareObject shareObject) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
