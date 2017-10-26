package com.github.xws;

public class Upgrade {
	
	private String type;
	
	private String xwsspecname;
	
	private boolean discarded = false;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXwsspecname() {
		return xwsspecname;
	}

	public void setXwsspecname(String xwsspecname) {
		this.xwsspecname = xwsspecname;
	}

	public boolean isDiscarded() {
		return discarded;
	}
	
	public void toggleDiscardFlag(boolean flag) {
		this.discarded = flag;
	}

	public void discard() {
		this.discarded = true;
	}
	
	public void attach() {
		this.discarded = false;
	}

}
