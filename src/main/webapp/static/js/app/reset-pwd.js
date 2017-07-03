/**
 * 
 */
layui.config({
	base: '../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
}).use(['layer', 'form', 'jacommon'], function() {
	var layer = layui.layer,
		$ = layui.jquery,
		jacommon = layui.jacommon,
		form = layui.form();
		
	//自定义验证规则
	form.verify({
		oldPassword : [ /(.+){6,12}$/, '旧密码必须6到12位' ],
		password : 	  [ /(.+){6,12}$/, '新密码必须6到12位' ],
		confirmPassword : function(value) {
			var pass = $.trim($('#password').val());
			if (value != pass) {
				return '两次密码不一致';
			}
		}
	});
	
	// 表单提交
	form.on('submit(btnSub)',function(data){
		var postUrl = data.form.action,
		postData = data.field,
		subBtn = $(data.elem),
		btnText = subBtn.html();
		subBtn.addClass('layui-btn-disabled').html('<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> 登录中...');
		
		// 提交请求
		jacommon.ajaxPost(postUrl, postData, function(res) {
			jacommon.success('修改成功', function() {
				close();
			});
		}, function(res) {
			jacommon.error('修改失败，' + res.message + '(错误代码：' + res.code + ')', null);
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
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭  
	}
});