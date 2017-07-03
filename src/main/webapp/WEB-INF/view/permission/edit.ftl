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
			<fieldset class="layui-elem-field layui-field-title none" style="margin-top: 20px;">
				<legend>填写用户信息</legend>
			</fieldset>
			<form class="layui-form layui-form-pane" action="${base}/admin/permission/save" method="post">
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="hidden" name="id" value="${vo.id}" lay-verify="id" autocomplete="off" class="layui-input" />
						<input type="hidden" name="pId" value="${vo.PId}" lay-verify="pId" autocomplete="off" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">权限名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" value="${vo.name}" lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">权限标志</label>
					<div class="layui-input-inline">
						<input type="text" name="permission" value="${vo.permission}"  placeholder="请输入权限标志" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">格式为：（例如：sys:user:view）</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">图标</label>
					<div class="layui-input-inline">
						<input type="text" name="icon" value="${vo.icon}" placeholder="请输入图标" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">图标名（例如：fa-user）</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序号</label>
					<div class="layui-input-inline">
						<input type="number" name="sort" value="${vo.sort}" lay-verify="required" placeholder="请输入整数排序号" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请填写整数排序号</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">URL地址</label>
					<div class="layui-input-inline">
						<input type="text" name="url" value="${vo.url}" lay-verify="required" placeholder="请输入URL" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item"  pane="">
					<label class="layui-form-label">是否菜单</label>
					<div class="layui-input-block">
					 	<#if vo.isMenu == true>
						<input type="checkbox" checked="" name="isMenu" lay-skin="switch" lay-filter="switchOn" title="是否菜单">
						<#else>
						<input type="checkbox" name="isMenu" lay-skin="switch" lay-filter="switchOn" title="是否菜单">
						</#if>
					</div>
				</div>
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">是否启用</label>
					<div class="layui-input-block">
						<#if vo.isEnable == true>
						<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchOn" title="是否启用">
						<#else>
						<input type="checkbox" name="open" lay-skin="switch" lay-filter="switchOn" title="是否启用">
						</#if>
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
						<button type="button" class="layui-btn layui-btn-primary" id="btnBack" lay-filter="btnBack">
							<i class="layui-icon">&#x1006;</i> 取 消
						</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
		<script type="text/javascript" src="${base}/static/js/app/permission/edit.js"></script>
	</body>
</html>