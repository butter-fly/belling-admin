<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码</title>
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
		<form  action="${base}/admin/pwd/reset" method="post" class="layui-form layui-form-pane">
			<div class="layui-form-item none">
				<div class="layui-input-block dx-input-required">
					<@shiro.user>
					<input type="hidden" name="uId" value="${user.id}" autocomplete="off" class="layui-input" />
					</@shiro.user>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">旧密码</label>
				<div class="layui-input-inline">
					<input type="password" name="oldPassword" lay-verify="oldPassword" autocomplete="off" placeholder="请输入旧密码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新密码</label>
				<div class="layui-input-inline">
					<input type="password" name="password" id="password" lay-verify="password"
						autocomplete="off" placeholder="请输入新密码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-inline">
					<input type="password" name="confirmPassword" lay-verify="confirmPassword"
						autocomplete="off" placeholder="请确认密码" class="layui-input">
				</div>
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
	//-->
	</script>
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="${base}/static/js/app/reset-pwd.js"></script>
</body>
</html>