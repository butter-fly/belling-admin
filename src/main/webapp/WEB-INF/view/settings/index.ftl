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
	<style type="text/css">
		.layui-form-label {width:120px!important;}
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
			</ul>
			<#-- Tab -->
			<div class="layui-tab-content">
				<#-- 网站设置 -->
				<div class="layui-tab-item layui-show">
					 <form class="layui-form layui-form-pane" action="${base}/admin/settings/save" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label">网站名称</label>
							<div class="layui-input-inline">
								<@getDict key="site_name">
								<#if val??>
								<input type="text" name="site_name" value="${val}" lay-verify="required" placeholder="请输入网站名称" autocomplete="off" class="layui-input" />
								<#else>
								<input type="text" name="site_name" lay-verify="required" placeholder="请输入网站名称" autocomplete="off" class="layui-input" />
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（网站名称，将显示在页面Title处）</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">版权所有</label>
							<div class="layui-input-inline">
								<@getDict key="site_copyright">
								<#if val??>
								<input type="text" name="site_copyright" value="${val}" lay-verify="required" placeholder="site_copyright" autocomplete="off" class="layui-input" />
								<#else>
								<input type="text" name="site_copyright" lay-verify="required" placeholder="请输入版权所有" autocomplete="off" class="layui-input" />
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（比如：浙江XX股份科技有限公司）</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">备案编号</label>
							<div class="layui-input-inline">
								<@getDict key="site_icp">
								<#if val??>
								<input type="text" name="site_icp" value="${val}" lay-verify="required" placeholder="请输入网站备案编号" autocomplete="off" class="layui-input">
								<#else>
								<input type="text" name="site_icp" lay-verify="required" placeholder="请输入网站备案编号" autocomplete="off" class="layui-input">
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（格式类似“京ICP证030173号”，它将显示在页面底部，没有请留空）</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">SEO关键词</label>
							<div class="layui-input-inline">
								<@getDict key="site_keywords">
								<#if val??>
								<input type="text" name="site_keywords" value="${val}" lay-verify="required" placeholder="请输入网站关键词" autocomplete="off" class="layui-input">
								<#else>
								<input type="text" name="site_keywords" lay-verify="required" placeholder="请输入网站关键词" autocomplete="off" class="layui-input">
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（比如百灵权限管理系统）</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">SEO描述</label>
							<div class="layui-input-inline">
								<@getDict key="meta_description">
								<#if val??>
								  <textarea class="layui-textarea" name="meta_description" lay-verify="content">${val}</textarea>
								<#else>
								  <textarea class="layui-textarea" name="meta_description" lay-verify="content"></textarea>	
								 </#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（网站别名，将显示在页面Title处,作用于首页，目的seo优化）</div>
						</div>
						
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="btn1">
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
					 <form class="layui-form layui-form-pane" action="${base}/admin/settings/save" method="post">
						<div class="layui-form-item">
							<div class="layui-form-item">
							<label class="layui-form-label">是否多地登录</label>
							<div class="layui-input-inline">
								<@getDict key="mult_login">
								<#if val?? && val == '1'>
									<input type="checkbox" checked="" name="mult_login" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
								<#else>
									<input type="checkbox" name="mult_login" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（请开启是否允许一个账号多地登录）</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">是否踢出登录</label>
							<div class="layui-input-inline">
								<@getDict key="kill_login">
								<#if val?? && val == '1'>
									<input type="checkbox" checked="" name="kill_login" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
								<#else>
									<input type="checkbox" name="kill_login" lay-skin="switch" lay-filter="switchTest" lay-text="是|否"/>
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（是否要保证一个账号登录挤掉之前的登录账号）</div>
						</div>
						<div class="layui-form-item none">
							<label class="layui-form-label">出错次数限制</label>
							<div class="layui-input-inline">
								<@getDict key="error_times_login">
								<#if val??>
								<input type="number" name="error_times_login" value="${val}" lay-verify="required" placeholder="请输入登陆次数限制" autocomplete="off" class="layui-input">
								<#else>
								<input type="number" value="0" name="error_times_login" lay-verify="required" placeholder="请输入登陆次数限制" autocomplete="off" class="layui-input">
								</#if>
								</@getDict>
							</div>
							<div class="layui-form-mid layui-word-aux">（用户登录错误次数后、禁用用户！比如小于1的不做限制）</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="btn2">
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