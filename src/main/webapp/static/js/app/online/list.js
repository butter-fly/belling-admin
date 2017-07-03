/**
 * Created by Administrator on 2017/2/27 0027.
 * @desc  : 用户列表JS
 * @author: Chenjx
 */
// 配置模块
layui.config({
	base : './../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	page_table : 'page_table',
	jacommon : 'jacommon'
});

// Table变量
var _tableObj, _datatable;
// 加载所需模块
layui.use(['form','layer', 'laytpl', 'page_table', 'jacommon', 'tab'], function(){
	//  操作对象
	var form = layui.form(),
		layer = layui.layer , 
		laytpl = layui.laytpl,
		pageTable = layui.page_table,
		jacommon = layui.jacommon,
		$ = layui.jquery;
	
	// 初始化表格
	_tableObj = $('#dataTable');
	_datatable = _tableObj.DataTable({
//		"dom": '<"top">rt<"bottom"flp><"clear">',
		"autoWidth": false,                     // 自适应宽度
		"stateSave": true,                      // 刷新后保存页数
		"bDestroy" : true,//不加这个在第二次展示此表格时报错
		"serverSide": true,
		"bRetrieve": true,
		"deferRender": true,                    // 当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
		"sServerMethod" : "POST",               // POST
		"ajax": {
			"url": WEB_ROOT + "/admin/online/page",
			"type": "POST",
			"data" : function(d) {
				d.v = "1.0.1";
			},
			"error": handleAjaxError // this sets up jQuery to give me errors  
		},
		"pagingType": "full_numbers",         // 分页样式 simple,simple_numbers,full,full_numbers
		"displayLength": 2, // 每页显示条数
		"rowId": 'sessionId', // row加sessionId
		"order": [[ 1, "desc" ]],               // 排序
		"columns": [                            // 自定义数据列
//			{"data": null, class: 'text-c'},
			{data: 'loginAccount'},
			{data: 'sessionId'},
			{data: 'lastAccess'},
			{data: 'ip'},
			{data: 'startTime'},
			{data: null}
		],
		"language" : {
			"sProcessing" : "正在获取数据，请稍后...",
			"sLengthMenu" : "",    
			"zeroRecords" : "没有找到数据",
			"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 ), 共 _TOTAL_ 条记录",
			"infoEmpty" : "暂无数据",
			"sLoadingRecords" : "加载中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		},
		"searching" : false, // 是否显示搜索
		"ordering" : false,
		"bInfo" : true, // 是否显示页脚分页
		"bPaginate" : true,
		"stateSaveParams": function () {           // 初始化完成调用事件
			// 重新渲染form checkbox 如果你要使用layui的复选框样式打开这个
//			form.render('checkbox');
		},
		"fnRowCallback" : function(row, data, index) {
			// 多选按钮
//			var opcheckboxtpl = document.getElementById('opcheckbox').innerHTML;
//			// 数据菜单
//			laytpl(opcheckboxtpl).render(data, function(html){
//				$('td:eq(0)', row).html(html);
//			});
 
			// 操作菜单
			var opbtntpl = document.getElementById('opbtn').innerHTML;
			// 数据菜单
			laytpl(opbtntpl).render(data, function(html){
				$('td:eq(5)', row).html($.trim(html) == '' ? '--' : html);
			});
			
			return row;
		},
		"initComplete": function () {
			
		},
		"footerCallback": function (row, data, start, end, display ) {
			
		}
	});
	
	// 请求异常拦截
	function handleAjaxError(xhr, textStatus, error) {
		jacommon.error('用户会话已失效或已被强制下线！', function(){
			parent.location.reload();
		});
	} 
	
	// 绑定table内操作事件
	_tableObj.on("click",".btn-kill", function() {
		//点击删除按钮
		var _id = $(this).attr('data-id');
		var _name = $(this).attr('data-name');
		jacommon.confirm('确认强制下线' + _name + '用户当前会话吗？', function() {
			var postUrl = WEB_ROOT + '/admin/online/kickout?t=' + new Date().getTime();
			var postData = {'sessionId' : _id};
			// 提交请求
			jacommon.ajaxPost(postUrl, postData, function(res) {
				jacommon.success('下线成功', function() {
					refresh();
				});
			}, function(res) {
				jacommon.error('下线失败，' + res.message + '(错误代码：' + res.code + ')');
			}, function() {
				 
			});
		});
	});
	 
	// 列表数据操作监听
	$(document).on('click','#btn-kill-all', function(){
		var ids = pageTable.getIds($('#dataTable'), 'data-id');
		if(ids == null || ids == ''){
			layer.msg('至少选择一行吆！');
		} else {
			jacommon.confirm('确认下线选中的用户吗，此操作不可逆？', function() {
				var postUrl = WEB_ROOT + '/admin/user/delete?t=' + new Date().getTime();
				var postData = {'ids' : ids};
				// 提交请求
				jacommon.ajaxPost(postUrl, postData, function(res) {
					jacommon.success('下线成功', function() {
						refresh();
					});
				}, function(res) {
					jacommon.error('下线失败，' + res.message + '(错误代码：' + res.code + ')');
				}, function() {
					refresh();
				});
			});
		}
	}).on('click','#btn-refresh-all', function(){
		refresh();
	});
});

/**
 * 刷新当前窗口
 */
function refresh() {
	_datatable.draw();
}