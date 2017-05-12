package com.share.soa.thrift.impl;

import org.apache.thrift.TException;

import com.share.core.interfaces.BaseHandler;
import com.share.soa.thrift.protocol.Userf.Iface;

public class UserHandler extends BaseHandler implements Iface {

	@Override
	public int getUser(int a) throws TException {
		System.err.println(a);
		return 0;
	}

	@Override
	public String getUser2(String name) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
