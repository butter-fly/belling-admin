<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title><@getDict key="site_name"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict></title>
		<meta name="description" content="<@getDict key="meta_description"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
		<meta name="keywords" content="<@getDict key="site_keywords"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
		<#include "/widget/common-css.html">
		<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
		<link rel="stylesheet" href="${base}/static/css/animate.min.css" />
		<link rel="stylesheet" href="${base}/static/css/login.css" />
	</head>

	<body class="beg-login-bg" bgcolor="#FFFFFF">
		<div class="beg-login-box animated bounce">
			<header>
				<h1>百灵权限管理系统</h1>
			</header>
			<div class="beg-login-main">
				<form action="${base}/login/do" class="layui-form" method="post">
					<input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="fa fa-user" aria-hidden="true"></i>
                    </label>
						<input type="text" value="admin" name="account" lay-verify="required" autocomplete="off" placeholder="账号" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon">
                         <i class="fa fa-key" aria-hidden="true"></i>
                    </label>
						<input type="password" value="123456" name="password" lay-verify="password" autocomplete="off" placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="beg-pull-left beg-login-remember">
							<label>记住帐号？</label>
							<input type="checkbox" name="rememberMe" checked="" lay-skin="switch" title="记住帐号" />
						</div>
						<div class="beg-pull-right">
							<button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
								<i class="fa fa-arrow-circle-right" aria-hidden="true"></i> 登录
							</button>
						</div>
						<div class="beg-clear"></div>
					</div>
				</form>
			</div>
			<footer>
				<p>2017 © <@getDict key="site_copyright"><#if val??>${val}<#else>蝴蝶飞飞  Sunny Chen</#if></@getDict></p>
			</footer>
		</div>
		<div class="part" style="z-index:-1;position:fixed;height:100%;width:100%;top:0;left:0"></div>
		<script type="text/javascript">
		<!--
			var WEB_ROOT = '${base}';
		//-->
		</script>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script type="text/javascript" charset="utf8" src="${base}/static/plugins/datatables/1.10.13/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="${base}/static/js/app/login.js"></script>
		<script type="text/javascript" src="${base}/static/js/jump2.js"></script> 
	</body>

</html>