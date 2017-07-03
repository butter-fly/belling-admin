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

	//表单提交
	form.on('submit(btnSub)', function(data) {
		// alert(JSON.stringify(data.field));
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
			postData.roleIds = getCheckedRoles();
		
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
	
	// 角色数组
	var roleIds = new Array();
	
	// 初始化选中角色
	$('input[name="ids"]:checked').each(function(){    
		roleIds.push($(this).data('id'));
	}); 

	
	// 监听checkbox
	form.on('checkbox(role_cbx_tree)', function(data){
		var rid = $(data.elem).data('id');
		// 添加
		if (data.elem.checked) {
			roleIds.push(rid);
		} else {
			// 移除
			jacommon.remove(roleIds, rid);
		}
		form.render('checkbox');
	});
	
	// 取消
	$("#btnBack").click(function(){
		close();
	});
	
	// 获取选中角色IDS
	function getCheckedRoles() {
		var obj = $('input[type="checkbox"]:checked');
		var list = '';
		obj.each(function (index, elem) {
		    list += $(elem).data('id') + ',';
		});
		// 去除最后一位逗号
		list = list.substr(0, (list.length - 1));
		return list;
	}
	
	// 关闭弹出层
	function close() {
		window.parent.refresh(); 
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭  
	}
});