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
			<form class="layui-form layui-form-pane" action="${base}/admin/role/save" method="post">
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<input type="hidden" name="id" value="${vo.id}" lay-verify="id" autocomplete="off" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" value="${vo.name}" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色标志</label>
					<div class="layui-input-inline">
						<input type="text" name="code" value="${vo.code}" lay-verify="code" placeholder="请输入角色标志" autocomplete="off" class="layui-input" />
					</div>
					<div class="layui-form-mid layui-word-aux">比如：admin，test</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序号</label>
					<div class="layui-input-inline">
						<input type="number" name="sort" value="${vo.sort}" lay-verify="required" placeholder="请输入整数排序号" autocomplete="off" class="layui-input" />
					</div>
					<div class="layui-form-mid layui-word-aux">请填写整数排序号</div>
				</div>
				
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">角色描述</label>
					<div class="layui-input-block">
						<textarea name="description" lay-verify="required" placeholder="请输入角色描述" class="layui-textarea">${vo.description}</textarea>
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
		<script type="text/javascript" src="${base}/static/js/app/role/edit.js"></script>
	</body>
</html>