<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title><@getDict key="site_name"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict></title>
		<meta name="description" content="<@getDict key="meta_description"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
		<meta name="keywords" content="<@getDict key="site_keywords"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
		<#include "/widget/common-css.html">
	</head>
	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="http://beginner.zhengjinfan.cn/demo/beginner_admin/">
							<span style="font-size: 20px;"><@getDict key="site_name"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict></span>
						</a>
						<div class="admin-side-toggle">
							<i class="fa fa-exchange" aria-hidden="true"></i>
						</div>
						<div class="admin-side-full">
							<i class="fa fa-arrows-alt" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">
						 	<@shiro.user> 
							<a href="javascript:;" class="admin-header-user">
								<img src="${base}/static/images/0.jpg" /> 
								<span><@shiro.principal /></span>
							</a>
							</@shiro.user>  
							<dl class="layui-nav-child">
								<dd id="reset-pwd">
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 修改密码 </a>
								</dd>
								<dd class="none">
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								<dd id="lock" class="none">
									<a href="javascript:;">
										<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)
									</a>
								</dd>
								<dd>
									<@shiro.user> 
										<a href="${base}/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 退出</a>
									</@shiro.user>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<@shiro.user> 
							<a href="${base}/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
							</@shiro.user>
						</li>
					</ul>
				</div>
			</div>
			
			<#--  Left Menu -->
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>

			<#--  Center body -->
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>控制面板</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="${base}/welcome"></iframe>
						</div>
					</div>
				</div>
			</div>
			
			<#-- footer -->
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2017 © <@getDict key="site_copyright"><#if val??>${val}<#else>蝴蝶飞飞  Sunny Chen</#if></@getDict>
						  LGPL license
					</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
			<#--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="${base}/static/images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">刘德华</div>
					<input type="text" class="admin-header-lock-input"  placeholder="输入密码解锁.." value="" name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			
			<#--锁屏模板 end -->
			<script type="text/javascript">
			<!--
				var WEB_ROOT = '${base}', U_ID = '${user.id}';
			//-->
			</script>
			<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
			<script type="text/javascript" src="${base}/static/datas/nav.js"></script>
			<script src="${base}/static/js/app/index.js"></script>
			<script src="${base}/static/js/tab.js"></script>
			
			<#--
			<script type="text/javascript"  src="${base}/static/plugins/datatables/1.10.13/jquery-1.10.2.min.js"></script>
			<script src="${base}/static/plugins/sockjs/sockjs.js"></script>
			<script src="${base}/static/plugins/hotkeys/jquery.hotkeys.js"></script>
			<script src="${base}/static/js/app/sklogin.js"></script> -->
		</div>
	</body>
</html>