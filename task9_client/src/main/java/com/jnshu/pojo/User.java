package com.jnshu.pojo;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 7910048470986175984L;
	public long uid ;
	public String username;
	public String token;
	public String series;
	public long longtime;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public long getLongtime() {
		return longtime;
	}
	public void setLongtime(long longtime) {
		this.longtime = longtime;
	}
	
	
}
