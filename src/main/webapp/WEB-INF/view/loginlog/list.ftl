<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>Data-Table 表格</title>
	<#include "/widget/common-css.html">
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			登录日志列表
		</legend>
	</fieldset>
	<#-- 操作按钮  -->
	<div class="my-btn-box">
		<span class="fl">
			<@shiro.hasPermission name="sys:loginlog:delete">  
			<form class="layui-form" action="">
				<div class="layui-form-pane">
					<label class="layui-form-label">截至时间：</label>
					<div class="layui-input-inline">
						 <select id="clear-date" name="clear-date" lay-verify="required">
							<option value="">选择清理的时间范围</option>
							<option value="1">最近1天内</option>
							<option value="2">最近2天内</option>
							<option value="7">最近一周内</option>
							<option value="15">最近半个月内</option>
							<option value="30">最近一个月内</option>
							<option value="182">最近半年内</option>
							<option value="365">最近一年内</option>
						</select>
					</div>
					<div class="layui-btn-group">
						<a class="layui-btn layui-btn-primary radius" id="btn-delete-all">
							 <i class="layui-icon">&#xe640;</i> 清理
						</a>
	 				</div>
				</div>
 			</form>
 			</@shiro.hasPermission> 
		</span>
		<span class="fr">
			<div class="layui-form-pane">
				<span class="layui-form-label">时间范围：</span>
				<div class="layui-input-inline">
					<#--  -->
					<input type="text" autocomplete="off" name="startTime" id="startTime" lay-verify="date" placeholder="开始时间"  class="layui-input" onclick="layui.laydate({elem: this, max: laydate.now(-1),  istime: true, festival: true,istoday: true, format: 'YYYY-MM-DD hh:mm'})" />
				</div>
				~
				<div class="layui-input-inline">
					<input type="text" autocomplete="off" name="endTime" id="endTime" lay-verify="date" placeholder="结束时间"  class="layui-input" onclick="layui.laydate({elem: this, max: laydate.now(),  istime: true, festival: true,istoday: true, format: 'YYYY-MM-DD hh:mm'})"/>
				</div>
				<button class="layui-btn mgl-20" id="btn-search">
					<i class="layui-icon">&#xe615;</i>&nbsp;查询
				</button>
			</div>
		</span>
	</div>
	
	<#-- Table  -->
	<table id="dataTable" class="layui-table">
		<thead>
			<tr>
				<th width="100">登录账号</th>
				<th width="50" >登录时间</th>
				<th  width="100">登录IP</th>
				<th  width="100">登录方式</th>
				<th  width="100">备注</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script type="text/javascript">
	<!--
		var U_ID = null;
		<@shiro.lacksRole name="admin">  
			U_ID = '${user.account}';
		</@shiro.lacksRole>
	//-->
	</script>
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<#-- 包含分页插件 -->
	<#include "/widget/paging-js.ftl"> 
	<script type="text/javascript" src="${base}/static/js/app/loginlog/list.js"></script>
</body>
</html>