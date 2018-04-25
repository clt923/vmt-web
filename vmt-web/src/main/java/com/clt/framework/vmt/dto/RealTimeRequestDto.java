package com.clt.framework.vmt.dto;

import java.io.Serializable;

public class RealTimeRequestDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String jsonData;
	private String api;
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
}
