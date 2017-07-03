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
		<link rel="stylesheet" href="${base}/static/plugins/zTree/css/metroStyle/metroStyle.css?v=1" />
		<link rel="stylesheet" href="${base}/static/plugins/zTree/css/metroStyle/metroStyle.custom.css" />
		<style type="text/css">
			.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
			.ztree li ul.level0 {padding:0; background:none;}
		</style>
	</head>
	<body>
		<div style="margin: 15px;">
			<fieldset class="layui-elem-field layui-field-title none">
				<legend>角色授权</legend>
			</fieldset>
			<form class="layui-form" action="${base}/admin/permission/allocateSave" method="post">
				<div class="layui-form-item">
					<table class="layui-table tree-table" lay-skin="line">
						<colgroup>
							<col>
						</colgroup>
						<tbody>
							<input type="hidden" name="roleId" value="${roleId}" autocomplete="off" class="layui-input" />
							<input type="hidden" id="permissionIds" name="permissionIds" value="" autocomplete="off" class="layui-input" />
							<ul id="_tree" class="ztree" style="height:360px; overflow:auto;"></ul>
						</tbody>
					</table>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="btnSub">
							<i class="layui-icon">&#xe605;</i> 提 交
						</button>
						<button type="button" class="layui-btn layui-btn-primary" id="btnBack" lay-filter="btnBack">
							<i class="layui-icon">&#x1006;</i> 取 消
						</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript">
		<!--
			var WEB_ROOT = '${base}';
			var permissions = new Array();
			<#if permissions?? && permissions?size gt 0>
				<#list permissions as vo>
					permissions.push(${vo.permissionId});
				</#list>
			</#if>
		//-->
		</script>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${base}/static/plugins/datatables/1.10.13/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${base}/static/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="${base}/static/plugins//zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
		<script type="text/javascript" src="${base}/static/js/app/role/permission-acclocate.js"></script>
	</body>
</html>