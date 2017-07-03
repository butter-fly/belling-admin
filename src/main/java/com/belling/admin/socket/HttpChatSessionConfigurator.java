package com.belling.admin.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**  
 * <pre>
 * Description	获取WebSocket Open时当前用户HttpSession
 * Copyright:	Copyright (c)2017
 * Company:		杭州启冠网络技术有限公司
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年3月27日 下午4:48:26  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public class HttpChatSessionConfigurator extends ServerEndpointConfig.Configurator {
	
	/* (non-Javadoc)
	 * @see javax.websocket.server.ServerEndpointConfig.Configurator#modifyHandshake(javax.websocket.server.ServerEndpointConfig, javax.websocket.server.HandshakeRequest, javax.websocket.HandshakeResponse)
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		if (null != httpSession) {
			config.getUserProperties().put(HttpSession.class.getName(), httpSession);
		}
	}
}
