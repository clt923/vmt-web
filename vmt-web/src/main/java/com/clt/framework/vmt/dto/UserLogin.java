package com.clt.framework.vmt.dto;

import java.io.Serializable;

public class UserLogin  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String ttNo;
	
	private String team;
	
	private String currentUrl;
	
	private String machineType;
	
	private String lang;
	
	private String ipAddress;
	private String trailerNo;
	
	public String getTrailerNo() {
		return trailerNo;
	}

	public void setTrailerNo(String trailerNo) {
		this.trailerNo = trailerNo;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTtNo() {
		return ttNo;
	}
	public void setTtNo(String ttNo) {
		this.ttNo = ttNo;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	
}
