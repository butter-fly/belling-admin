/**
 * 
 */
// 配置模块
layui.config({
	base : './../../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
});

// 加载模块
layui.use(['form', 'layedit', 'jacommon', 'laydate', 'layer','element','form'], function() {
	var form = layui.form(),
		layer = layui.layer,
		jacommon = layui.jacommon,
		$ = layui.jquery,
		element = layui.element(),
		layedit = layui.layedit;
	
	//自定义验证规则
	form.verify({
		account : function(value) {
			if (value.length < 2) {
				return '账号至少得2个字符!';
			}
		},
		pass : [ /(.+){6,12}$/, '密码必须6到12位' ]
	});

	//表单提交
	form.on('submit(btnSub)', function(data) {
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
			if (postData.open !== 'undefined' && postData.open !== '' && postData.open !== null && postData.open == 'on') {
				postData.isEnable = 1;
			} else {
				postData.isEnable = 0;
			}
		subBtn.addClass('layui-btn-disabled').html('<i class="layui-icon">&#xe605;</i> 提交中...');
		
		// 提交请求
		jacommon.ajaxPost(postUrl, postData, function(res) {
			jacommon.success('保存成功', function() {
				// 关闭当前窗口
				close();
			});
		}, function(res) {
			jacommon.error('保存失败，' + res.message + '(错误代码：' + res.code + ')');
		}, function() {
			subBtn.removeClass('layui-btn-disabled').html(btnText);
		});
		return false;
	});
	
	// 取消
	$("#btnBack").click(function(){
		close();
	});
	
	// 关闭弹出层
	function close() {
		window.parent.refresh(); 
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭  
	}
});