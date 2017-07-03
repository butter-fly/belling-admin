<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>表单</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<#include "/widget/common-css.html">
	</head>
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title none">
				<legend>授权用户对应角色</legend>
			</fieldset>
			<form class="layui-form" action="${base}/admin/userRole/allocate" method="post">
				 <div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="hidden" name="userId" value="${userId}" />
					</div>
				</div>
				 <div class="layui-form-item">
				 	<label class="layui-form-label">角色列表</label>
				 	<#if roleList?size == 0>
				 		<div class="layui-input-block">
				 			暂无角色可分配
				 		</div>
				 	<#else>
				 		<#list roleList as vo>
				 		<div class="layui-input-block">
				 			<#if vo.isChecked == true>
							<input type="checkbox" data-id="${vo.id}" name="ids" value="${vo.id}" lay-filter="role_cbx_tree" lay-skin="primary" title="${vo.name }" checked="">
				 			<#else>
				 			<input type="checkbox" data-id="${vo.id}" name="ids" value="${vo.id}" lay-filter="role_cbx_tree" lay-skin="primary" title="${vo.name }">
				 			</#if>
				 			<#-- 
				 			<#if vo.isChecked == true>
							<input type="checkbox" lay-verify="userRole" data-id="${vo.id}" name="role[${vo.id}]" title="${vo.name}" checked="" />
							<#else>
							<input type="checkbox" lay-verify="userRole" data-id="${vo.id}" name="role[${vo.id}]" title="${vo.name}" />
							</#if> -->
						</div>
						</#list>
				 	</#if>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="btnSub">
							<i class="layui-icon">&#xe605;</i> 提 交
						</button>
						<button type="button" class="layui-btn layui-btn-primary" id="btnBack" lay-filter="btnBack">
							<i class="layui-icon">&#x1006;</i> 取 消
						</button>
						<#-- 
						<button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${base}/static/js/app/user/role-allocate.js"></script>
	</body>
</html>