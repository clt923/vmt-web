package com.clt.framework.vmt.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider{
	public CustomDaoAuthenticationProvider(){
		this.hideUserNotFoundExceptions=false;
	}
}
