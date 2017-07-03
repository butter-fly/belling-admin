/**
 * Created by Administrator on 2017/2/27 0027.
 * @desc  : 用户列表JS
 * @author: Chenjx
 * @demo http://jadmin.jsyso.com/a
 */
// 配置模块
layui.config({
	base : './../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	jacommon : 'jacommon'
});

// 树节点
var setting = null, treeObj = null;

// 加载所需模块
layui.use(['form','layer', 'laytpl', 'jacommon', 'tab'], function(){
	// 操作对象
	var form = layui.form(),
		layer = layui.layer , 
		laytpl = layui.laytpl,
		jacommon = layui.jacommon,
		$ = layui.jquery;
		
	// 默认设置 
	setting = {
		showLine: true, 
		checkable: true,
		view: {
			addDiyDom: addDiyDom,
			selectedMulti: false,
			expandSpeed:""
		},
		async: {
			enable: true,
			contentType: "application/x-www-form-urlencoded",
			type: "post",
			otherParam: null,
			autoParam:["id", "name", "level"],
			dataType: "text",
			url: WEB_ROOT + "/admin/permission/nodes"
		},
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
	
	//树菜单初始化
	treeObj = $.fn.zTree.init($("#_tree"), setting);
	
	//在节点上固定显示用户自定义控件
	var IDMark_A = "_a";
	function addDiyDom(treeId, treeNode) {
		var aObj = $("#" + treeNode.tId + IDMark_A);
		// 操作按钮
		var opcheckboxtpl = document.getElementById('opBtn').innerHTML;
		var editStr = null;
		// 数据菜单
		laytpl(opcheckboxtpl).render(treeNode, function(html){
			editStr = html;
		});
		
//		console.log('editStr' + editStr);
		aObj.after(editStr);
		
		// 添加菜单
		var addBtn = $("#add_" + treeNode.id);
		if(addBtn){
			addBtn.bind("click", function(){
				setForm(treeNode,'add');
			});
		}
		
		// 编辑菜单
		var editBtn = $("#edit_" + treeNode.id);
		if(editBtn){
			editBtn.bind("click", function(){
				setForm(treeNode,'update');
			});
		}
		
		// 删除菜单
		var deleteBtn = $("#delete_" + treeNode.id);
		if(deleteBtn){
			deleteBtn.bind("click", function(){
				setForm(treeNode, 'del');
			});
		}
	};

	//树加载成功后，全部展开
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		treeObj.expandAll(true);
	};
		
	//增加、修改时填充表单
	function setForm(treeNode, flag){
		if(flag == 'add'){
			var _url = WEB_ROOT + "/admin/permission/edit";
			if ( null != treeNode.id && '' != treeNode.id) {
				_url = _url  + '?pId=' + treeNode.id;
			}
			var index = layer.open({
				type: 2,
				title: '添加权限',
				content: _url,
				shadeClose: true, //点击遮罩关闭层
				area : ['550px' , '500px'],
			});
		} else if(flag == 'update'){
			var index = layer.open({
				type: 2,
				title: '编辑权限',
				content: WEB_ROOT + "/admin/permission/edit?id=" + treeNode.id,
				shadeClose: true, //点击遮罩关闭层
				area : ['550px' , '500px'],
			});
		}  else if(flag == 'del'){
			jacommon.confirm('确认删除"' + treeNode.name + '"吗，此操作不可逆？', function() {
				var postUrl = WEB_ROOT + '/admin/permission/delete?t=' + new Date().getTime();
				var postData = {'id' : treeNode.id};
				// 提交请求
				jacommon.ajaxPost(postUrl, postData, function(res) {
					jacommon.success('删除成功', function() {
						reloadTree();
					});
				}, function(res) {
					jacommon.error('删除失败，' + res.message + '(错误代码：' + res.code + ')');
				}, function() {
					 
				});
			});
		}
	}
});

// 刷新树
function reloadTree(){
	treeObj = $.fn.zTree.init($("#_tree"), setting);
}
