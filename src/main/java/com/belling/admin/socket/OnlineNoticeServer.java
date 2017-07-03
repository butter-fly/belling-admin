package com.belling.admin.socket;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**  
 * <pre>
 * Description	WebSocket服务-定义成一个WebSocket服务器端，用于监听用户连接的终端访问URL地址
 * Copyright:	Copyright (c)2017
 * Company:		杭州启冠网络技术有限公司
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年3月27日 下午4:42:36  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@ServerEndpoint(value = "/noticeServer", configurator = HttpChatSessionConfigurator.class)
public class OnlineNoticeServer {
	
	/**
	 * 当前会话
	 */
	private static CopyOnWriteArraySet<OnlineNoticeServer> webSocketSet = Sets.newCopyOnWriteArraySet();
	
	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;
	
	/**
	 * 用户名
	 */
	private String userid; 
	
	
	/**
	 * request的session
	 */
	private HttpSession httpSession; 

	/**
	 * 在线列表,记录用户名称
	 */
	private static List<String> OnlineUserlist = Lists.newCopyOnWriteArrayList();
	
	/**
	 *  用户名和websocket的session绑定的路由表
	 */
	private static Map<String, Session> routetabMap = Maps.newConcurrentMap();
	
	
	
	/**
	 * 连接建立成功调用的方法-与前端JS代码对应
	 * 
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		// 单个会话对象保存
		this.session = session;
		webSocketSet.add(this); // 加入set中
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		String uId = (String) httpSession.getAttribute("userid"); // 获取当前用户
		String sessionId = httpSession.getId();
		this.userid = uId + "|" + sessionId;
		if (!OnlineUserlist.contains(this.userid)) {
			OnlineUserlist.add(userid); // 将用户名加入在线列表
		}
		routetabMap.put(userid, session); // 将用户名和session绑定到路由表
		System.out.println(userid + " -> 已上线");
		String message = getMessage(userid + " -> 已上线", "notice", OnlineUserlist);
		broadcast(message); // 广播
	}

	/**
	 * 连接关闭调用的方法-与前端JS代码对应
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		routetabMap.remove(userid); 
		OnlineUserlist.remove(userid);
		System.out.println(userid + " -> 已下线");
		String message = getMessage(userid + " -> 已下线", "notice", OnlineUserlist);
		broadcast(message);
		//singleSend(message, sn); // 广播
	}

	/**
	 * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送 -与前端JS代码对应
	 * 消息格式JSON，参数如下：
	 * "massage" : { "from" : "xxx", "to" : "xxx", "content" : "xxx", "time" :
	 * "xxxx.xx.xx" }, 
	 * "type" : {notice|message},
	 *  "list" : {[xx],[xx],[xx]}
	 *  
	 * @param _message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String _message) {
		
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param error
	 */
	@OnError
	public void onError(Throwable error) {
		System.out.println(error.getLocalizedMessage());
	}

	/**
	 * 广播消息
	 * 
	 * @param message
	 */
	public void broadcast(String message) {
		// 判断当前会话人数
		if (webSocketSet.size() == 0) return;
		
		// 获取当前所有的会话session
		for (OnlineNoticeServer chat : webSocketSet) {
			chat.session.getAsyncRemote().sendText(message);
		}
	}

	/**
	 * 对特定用户发送消息
	 * 
	 * @param message
	 * @param session
	 */
	public void singleSend(String message, Session session) {
		session.getAsyncRemote().sendText(message); // 异步发送
	}

	/**
	 * 组装返回给前台的消息
	 * 
	 * @param message 交互信息
	 * @param type 信息类型
	 * @param list 在线列表
	 * @return
	 */
	public String getMessage(String message, String type, List<String> list) {
		JSONObject member = new JSONObject();
		member.put("message", message);
		member.put("type", type);
		member.put("list", list);
		return member.toString();
	}
}
