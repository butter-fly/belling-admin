<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>用户在线列表</title>
	<#include "/widget/common-css.html">
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			在线用户
		</legend>
	</fieldset>
	
	<#-- 操作按钮  -->
	<div class="my-btn-box">
		<span class="fl">
			<div class="layui-btn-group">
				<@shiro.hasPermission name="sys:online:refresh">  
				<a class="layui-btn layui-btn-primary radius" id="btn-refresh-all">
					 <i class="fa fa-refresh" aria-hidden="true"></i> 刷新
				</a>
				</@shiro.hasPermission> 
			</div>
		</span>
	</div>
	<#-- Table  -->
	<table id="dataTable" class="layui-table">
		<thead>
			<tr>
				<#-- 
				<th width="25">
					<input type="checkbox" class="my-checkbox"/>
				</th> -->
				<th width="100">账号</th>
				<th width="150">Session ID</th>
				<th width="100">最后一次心跳时间</th>
				<th  width="100">最后一次登录IP</th>
				<th  width="100">登录时间</th>
				<th  width="80">操作</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<#-- 单选按钮   -->
	<script id="opcheckbox" type="text/html">
		<input type="checkbox" class="my-checkbox" data-id="{{ d.sessionId}}" />
	</script>
 
	
	<#-- 操作按钮   -->
	<script id="opbtn" type="text/html">
		<@shiro.hasPermission name="sys:online:kill">  
		<a class="layui-btn layui-btn-small  layui-btn-normal btn-kill" data-name="{{ d.loginAccount }}" data-id="{{ d.sessionId }}"><i class="fa fa-sign-out" aria-hidden="true"></i> 下线</a>
		</@shiro.hasPermission> 
	</script>
	
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<#-- 包含分页插件 -->
	<#include "/widget/paging-js.ftl"> 
	<script type="text/javascript" src="${base}/static/js/app/online/list.js"></script>
</body>
</html>