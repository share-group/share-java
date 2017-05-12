package com.share.core.data.config;

import com.share.core.annotation.Config;
import com.share.core.interfaces.DSuper;

@Config(key = { "level" })
public class CUserConfig extends DSuper {
	private int level;
	private int exp;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}