package com.clt.framework.vmt.dto;

import java.io.Serializable;

public class MessageResponseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean status;
	private String errorMessage;
	private boolean isServeError;
	public MessageResponseDto(){
		
	}
	
	public MessageResponseDto(Boolean statusCode,String error){
		status=statusCode;
		errorMessage=error;
	}
	
	public MessageResponseDto(Boolean statusCode,String error,boolean isServer){
		status=statusCode;
		errorMessage=error;
		isServeError=isServer;
	}
		
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isServeError() {
		return isServeError;
	}

	public void setServeError(boolean isServeError) {
		this.isServeError = isServeError;
	}
	
}
