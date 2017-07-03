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
	page_table : 'page_table',
	jacommon : 'jacommon'
});

// Table变量
var _tableObj, _datatable;
// 日期节点
var startTime, endTime;
// 加载所需模块
layui.use(['form','layer', 'laytpl', 'page_table', 'laydate', 'jacommon', 'tab'], function(){
	//  操作对象
	var form = layui.form(),
		layer = layui.layer , 
		laytpl = layui.laytpl,
		pageTable = layui.page_table,
		laydate = layui.laydate,
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
			"url": WEB_ROOT + "/admin/loginlog/page",
			"type": "POST",
			"data" : function(d) {
				var startTime = $.trim($("#startTime").val());
				if (startTime != '') {
					startTime = jacommon.parseStr2TimeStamp(startTime);
				} else {
					startTime = null;
				}
				d.startTime = startTime;
				
				var endTime = $.trim($("#endTime").val());
				if (endTime != '') {
					endTime = jacommon.parseStr2TimeStamp(endTime);
				} else {
					endTime = null;
				}
				console.log('endTime:' + endTime);
				d.endTime = endTime;
				d.uId = U_ID;
				d.v = "1.0.1";
			},
			"error": handleAjaxError // this sets up jQuery to give me errors  
		},
		"pagingType": "full_numbers",         // 分页样式 simple,simple_numbers,full,full_numbers
		"displayLength": 8, // 每页显示条数
		"rowId": 'id', // row加id
		"order": [[ 1, "desc" ]],               // 排序
		"columns": [                            // 自定义数据列
			{data: 'userId'},
			{data: 'loginTime'},
			{data: 'loginIp'},
			{data: function(obj) {
				if (obj.loginType == '1') {
					return 'web';
				}
				return 'app';
			}},
			{data: 'loginDesc'}
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
			 
		},
		"fnRowCallback" : function(row, data, index) {
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
	
	// 列表数据操作监听
	$(document).on('click','#btn-delete-all', function(){
		var _dateObj = $("#clear-date");
		var _val = _dateObj.val();
		if(_val == null || _val == ''){
			jacommon.msgError('请选择清理的时间范围！！');
		} else {
			var startTime = laydate.now();
			if (_val != 1)  {
				startTime = laydate.now(-_val);
			}
			startTime = jacommon.parseStr2TimeStamp(startTime + " 00:00");
			//console.log('startTime' + startTime);
			var _txt = _dateObj.find("option:selected").text();
			jacommon.confirm('确认删除' + _txt + '的登录日志吗？', function() {
				var postUrl = WEB_ROOT + '/admin/loginlog/delete?t=' + new Date().getTime();
				var postData = {'startTime' : startTime};
				// 提交请求
				jacommon.ajaxPost(postUrl, postData, function(res) {
					jacommon.success('清理成功', function() {
						refresh();
					});
				}, function(res) {
					jacommon.error('清理失败，' + res.message + '(错误代码：' + res.code + ')');
				}, function() {
					 
				});
			});
		}
	}).on('click','#btn-search', function(){
		var startTime = $.trim($("#startTime").val());
		if (startTime != '') {
			startTime = jacommon.parseStr2TimeStamp(startTime);
		} else {
			startTime = null;
		}
		
		var endTime = $.trim($("#endTime").val());
		if (endTime != '') {
			endTime = jacommon.parseStr2TimeStamp(endTime);
		} else {
			endTime = null;
		}
		
		if (null != startTime && null != endTime) {
			if (startTime >= endTime) {
				jacommon.msgError('开始时间必须小于结束时间！！');
				return false;
			}
		}
		refresh();
	});
	
	// 回车事件监听
	$(document).keyup(function(event){
		if(event.keyCode ==13){
			refresh();
		}
	});
});

/**
 * 刷新当前窗口
 */
function refresh() {
	_datatable.draw();
}