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
	<style>
		.layui-form-label {width:90px;}
	</style>
</head>
<body>
	<div style="margin: 15px;">
		<fieldset class="layui-elem-field layui-field-title none"
			style="margin-top: 20px;">
			<legend>默认风格的Tab</legend>
		</fieldset>
		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li class="layui-this">
					<i class="fa fa-life-buoy"></i>&nbsp;站点设置
				</li>
				<li>
					<i class="fa fa-user"></i>&nbsp;登录设置
				</li>
				<li>
					<i class="fa fa-envelope-open"></i>&nbsp;邮箱设置
				</li>
			</ul>
			
			<div class="layui-tab-content">
				<#-- 网站设置 -->
				<div class="layui-tab-item layui-show">
					 <form class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label">网站名称</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入网站名称" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">网站名称，将显示在页面Title处</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">网站SEO</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入网站名称" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">网站别名，将显示在页面Title处,作用于首页，目的seo优化</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">备案编号</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入网站备案编号" autocomplete="off" class="layui-input">
							</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="btnSub">
									<i class="layui-icon">&#xe605;</i> 提 交
								</button>
								<button type="reset" class="layui-btn layui-btn-normal">
									<i class="layui-icon">&#xe60e;</i> 重置
								</button> 
							</div>
						</div>
					</form>
				</div>
				
				<#-- 登陆设置 -->
				<div class="layui-tab-item">
					 <form class="layui-form" action="">
						<div class="layui-form-item">
							<div class="layui-form-item">
							<label class="layui-form-label">允许多地登录</label>
							<div class="layui-input-inline">
								<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
							</div>
							<div class="layui-form-mid layui-word-aux">（请开启是否允许一个账号多地登录）</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">覆盖登录</label>
							<div class="layui-input-inline">
								<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
							</div>
							<div class="layui-form-mid layui-word-aux">（是否要保证一个账号登录挤掉之前的登录账号）</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">次数限制</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">登录账号</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="btnSub">
									<i class="layui-icon">&#xe605;</i> 提 交
								</button>
								<button type="reset" class="layui-btn layui-btn-normal">
									<i class="layui-icon">&#xe60e;</i> 重置
								</button> 
							</div>
						</div>
					</form>
				</div>
				
				<#-- 邮件设置 -->
				<div class="layui-tab-item">
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<label class="layui-form-label">邮箱地址</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">当发送邮件不指定邮件来源时，默认使用此地址作为邮件来源</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">邮箱密码</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入SMTP 端口" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">SMTP的身份验证密码</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">SMTP 服务器</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="SMTP 服务器" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">设置 SMTP 服务器的地址</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">SMTP 端口</label>
							<div class="layui-input-inline">
								<input type="text" name="username" lay-verify="required" placeholder="请输入SMTP 端口" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">设置 SMTP 服务器的端口，默认为 25</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="btnSub">
									<i class="layui-icon">&#xe605;</i> 提 交
								</button>
								<button type="reset" class="layui-btn layui-btn-normal">
									<i class="layui-icon">&#xe60e;</i> 重置
								</button> 
							</div>
						</div>
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript" src="${base}/static/js/app/settings/index.js"></script>
</body>
</html>