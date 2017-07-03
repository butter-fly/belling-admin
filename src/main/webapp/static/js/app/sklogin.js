/**
 * 
 */
var ws = null,
	wsServer = "ws://" + location.host + WEB_ROOT + "/noticeServer",
	httpServer = "http://" + location.host + WEB_ROOT + "/noticeServer";
layui.config({
	base : 'static/js/',
	version : new Date().getTime()
}).use([ 'element', 'layer'], function() {
	var layer = layui.layer, 
	$ = layui.jquery;
	
	//创建WebSocket对象
	if ('WebSocket' in window) {
		ws = new WebSocket(wsServer);
	} else if ('MozWebSocket' in window) {
		ws = new MozWebSocket(wsServer);
	} else {
		ws = new SockJS(httpServer);
	}

	// Socket创建并连接
	ws.onopen = function(evt) {
		return;
		layer.msg("已建立Socket连接", {
			offset : 0
		});
	};
	
	
	// Socket 消息传递监听
	ws.onmessage = function(evt) {
		analysisMessage(evt.data); //解析后台传回的消息,并予以展示
	};

	// Socket异常监听
	ws.onerror = function(evt) {
		layer.msg("产生Socket异常", {
			offset : 0
		});
	};

	// Socket关闭监听
	ws.onclose = function(evt) {
		return;
		layer.msg("已关闭Socket连接", {
			offset : 0
		});
	};
	
	/**
	 * 获取当前连接，没有在
	 */
	function getConnection() {
		if (ws == null) {
			//创建WebSocket对象
			if ('WebSocket' in window) {
				ws = new WebSocket(wsServer);
			} else if ('MozWebSocket' in window) {
				ws = new MozWebSocket(wsServer);
			} else {
				ws = new SockJS(httpServer);
			}

			// Socket创建并连接
			ws.onopen = function(evt) {
				layer.msg("已建立Socket连接", {
					offset : 0
				});
			};

			// Socket 消息传递监听
			ws.onmessage = function(evt) {
				analysisMessage(evt.data); //解析后台传回的消息,并予以展示
			};

			// Socket异常监听
			ws.onerror = function(evt) {
				layer.msg("产生Socket异常", {
					offset : 0
				});
			};

			// Socket关闭监听
			ws.onclose = function(evt) {
				return;
				layer.msg("已关闭Socket连接", {
					offset : 0
				});
			};
		} else {
			layer.msg("Socket连接已存在!", {
				offset : 0,
				shift : 6
			});
		}
	}

	/**
	 * 关闭连接
	 */
	function closeConnection() {
		if (ws != null) {
			ws.close();
			ws = null;
			layer.msg("已关闭Socket连接", {
				offset : 0
			});
		} else {
			layer.msg("未开启Socket连接", {
				offset : 0,
				shift : 6
			});
		}
	}
	
	/**
	 * 解析后台传来的消息，具体格式如下：
	 * "massage" : {
	 *              "from" : "xxx",
	 *              "to" : "xxx",
	 *              "content" : "xxx",
	 *              "time" : "xxxx.xx.xx",
	 *				"ip":"192.168.1.122"
	 *          },
	 * "type" : {notice|message},
	 * "list" : {[xx],[xx],[xx]}
	 */
	function analysisMessage(message) {
		message = JSON.parse(message);
		//提示消息
		if (message.type == "notice") {
			layer.msg(message.message, {
				offset : 0
			});
		}
	}

});


