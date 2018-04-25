package com.clt.framework.vmt.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInforDto implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserLogin user;
	private boolean isLoginSuccess;
	private String messageError;
	private String sessionId;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public boolean isLoginSuccess() {
		return isLoginSuccess;
	}
	public void setLoginSuccess(boolean isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	public UserLogin getUser() {
		return user;
	}
	public void setUser(UserLogin user) {
		this.user = user;
	}
	public UserInforDto(UserLogin user){
		this.user=user;
	}
	public UserInforDto(boolean isSuccessful,String messageError){
		this.isLoginSuccess=isSuccessful;
		this.messageError=messageError;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user == null ? "" : this.user.getPassword();
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user == null ? "" : this.user.getUserId();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
