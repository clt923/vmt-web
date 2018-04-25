package com.clt.framework.vmt.service;

import com.clt.framework.vmt.dto.MessageResponseDto;

public interface MessageCodeInterface {
	
	public MessageResponseDto GetValueByKey(String key);
	
	public MessageResponseDto GetValueByKeyAndFormat(String key,Object... args);
}