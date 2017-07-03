/**
 * 角色授权JS功能实现
 */
// 配置模块
layui.config({
	base : './../../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
});

var setting = null, treeObj = null;
layui.use(['element','form', 'jacommon', 'layer'], function(){
	var $ = layui.jquery,
	layer = layui.layer,
	element = layui.element(),
	jacommon = layui.jacommon,
	form = layui.form();
	
	// 参数设置
	setting = {
		view : {
			selectedMulti : true
		},
		async : {
			enable : true,
			contentType : "application/x-www-form-urlencoded",
			type : "post",
			otherParam : {
				"isEnable" : true
			},
			dataType : "text",
			url : WEB_ROOT + "/admin/permission/nodes"
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			}
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onAsyncSuccess : zTreeOnAsyncSuccess,
			onClick : zTreeOnClick
		}
	};
	
	//树菜单初始化
	treeObj = $.fn.zTree.init($("#_tree"), setting);
	
	// 刷新节点树
	function reloadTree(){
		treeObj = $.fn.zTree.init($("#_tree"), setting);
	}
	
	//树加载成功后，全部展开
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		treeObj.expandAll(true);
		
		// 初始化选中节点 
		if (null != permissions && permissions.length > 0) {
			for(var i=0, lg = permissions.length; i< lg; i++){
				var node = treeObj.getNodeByParam("id", permissions[i], null);
				if(node){
					treeObj.checkNode(node, true);
				}
			}
			
			// 初始化根节点选中
			var pnode = treeObj.getNodeByParam("id", null, null);
			treeObj.checkNode(pnode, true);
		}
	};
	
	// 单击事件
	function zTreeOnClick(event, treeId, treeNode){
		treeObj.checkNode(treeNode, null, true);
	}
	
	//表单提交
	form.on('submit(btnSub)', function(data) {
		var permissionIds = getCheckedIds();
		var postUrl = data.form.action,
			postData = data.field,
			subBtn = $(data.elem),
			btnText = subBtn.html();
		postData.permissionIds = permissionIds;
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
	
	// 获取选中节点IDS
	function getCheckedIds() {
		var permissionIds = '';
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes){
			for(var i = 0, lg = nodes.length; i < lg; i++){
				if(nodes[i].id){
					permissionIds += nodes[i].id + ",";
				}
			}
			permissionIds = permissionIds.substring(0,permissionIds.length-1);
		}
		console.log('permissionIds:' + permissionIds);
		$("#permissionIds").val(permissionIds);
		return permissionIds;
	}
	
	// 关闭弹出层
	function close() {
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭  
	}
});
