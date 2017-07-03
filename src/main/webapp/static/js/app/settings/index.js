/**
 * 
 */
// 配置模块
layui.config({
	base : './../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
});

layui.use(['form', 'layedit', 'jacommon', 'laydate', 'layer','element','form'], function() {
	var form = layui.form(),
		layer = layui.layer,
		jacommon = layui.jacommon,
		$ = layui.jquery,
		element = layui.element(), //Tab的切换功能，切换事件监听等，需要依赖element模块
		layedit = layui.layedit;
	
	//表单提交
	form.on('submit(btn1)', function(data) {
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
		subBtn.addClass('layui-btn-disabled').html('<i class="layui-icon">&#xe605;</i> 提交中...');
		
		// 提交请求
		jacommon.ajaxPost(postUrl, postData, function(res) {
			jacommon.success('保存成功', function() {
				// data.form.reset();
			});
		}, function(res) {
			jacommon.error('保存失败，' + res.message + '(错误代码：' + res.code + ')');
		}, function() {
			subBtn.removeClass('layui-btn-disabled').html(btnText);
		});
		return false;
	});
	
	//表单提交
	form.on('submit(btn2)', function(data) {
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
			if (postData.mult_login !== 'undefined' && postData.mult_login !== '' && postData.mult_login !== null && postData.mult_login == 'on') {
				postData.mult_login = 1;
			} else {
				postData.mult_login = 0;
			}
			
			if (postData.kill_login !== 'undefined' && postData.kill_login !== '' && postData.kill_login !== null && postData.kill_login == 'on') {
				postData.kill_login = 1;
			} else {
				postData.kill_login = 0;
			}
		subBtn.addClass('layui-btn-disabled').html('<i class="layui-icon">&#xe605;</i> 提交中...');
		
		// 提交请求
		jacommon.ajaxPost(postUrl, postData, function(res) {
			jacommon.success('保存成功', function() {
				// data.form.reset();
			});
		}, function(res) {
			jacommon.error('保存失败，' + res.message + '(错误代码：' + res.code + ')');
		}, function() {
			subBtn.removeClass('layui-btn-disabled').html(btnText);
		});
		return false;
	});
});