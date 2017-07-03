/**
 * 
 */
layui.config({
	base: 'static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
}).use(['layer', 'form', 'jacommon'], function() {
	var layer = layui.layer,
		$ = layui.jquery,
		jacommon = layui.jacommon,
		form = layui.form();
	
	// 表单提交 
	form.on('submit(login)',function(data){
		var postUrl = data.form.action,
		postData = data.field,
		subBtn = $(data.elem),
		btnText = subBtn.html();
		subBtn.addClass('layui-btn-disabled').html('<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> 登录中...');
		if (postData.rememberMe !== 'undefined' && postData.rememberMe !== '' && postData.rememberMe !== null && postData.rememberMe == 'on') {
			postData.rememberMe = 1;
		} else {
			postData.rememberMe = 0;
		}
		
//		alert(JSON.stringify(postData));
		// 提交请求
		jacommon.ajaxPost(postUrl, postData, function(res) {
			jacommon.msgOffsetSuccess('登录成功，正在跳转...', function() {
				location.href = WEB_ROOT + '/admin';
			});
		}, function(res) {
			jacommon.msgOffsetError('登录失败，' + res.message + '(错误代码：' + res.code + ')', null);
		}, function() {
			subBtn.removeClass('layui-btn-disabled').html(btnText);
		});
		return false;
	});
});