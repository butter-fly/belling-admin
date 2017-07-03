<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>百灵权限管理系统</title>
	<#include "/widget/common-css.html">
</head>
<body class="body">

	<div class="layui-collapse" lay-accordion="" lay-filter="collapse">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">系统信息及版本</h2>
			<div class="layui-colla-content layui-show">
				<table class="layui-table">
					<tr>
						<td width="40%">软件名称</td>
						<td width="60%"><@getDict key="site_name"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict> v1.0.1</td>
					</tr>
					<tr>
						<td>系统作者</td>
						<td><@getDict key="site_copyright"><#if val??>${val}<#else>蝴蝶飞飞  Sunny Chen</#if></@getDict>（Sunny）  &nbsp;&nbsp;<i class="fa fa-qq"></i> QQ:1056264044</td>
					</tr>
					<tr>
						<td>反馈邮箱</td>
						<td>cjx1717#163.com</td>
					</tr>
					<tr>
						<td>LayUI官网</td>
						<td><a href="javascript:parent.location.href='http://www.layui.com/';">layui</a></td>
					</tr>
					<tr>
						<td>前端框架及图标样式</td>
						<td>LayerUI、Ztree、Jquery、jquery.dataTables、font-awesome等</td>
					</tr>
					<tr>
						<td>后端技术</td>
						<td>Spring、SpringMVC、MyBatis、Shiro、Ehcache、Freemarker、Druid、Lombok、Guava等</td>
					</tr>
					<tr>
						<td>功能模块</td>
						<td>账号登陆、用户管理、角色管理、权限管理、在线用户列表、下线用户、登陆日志、修改密码、注销用户、系统设置等</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">服务器信息</h2>
			<div class="layui-colla-content">
				<table class="layui-table">
					<tr>
						<td width="40%">服务器域名</td>
						<td width="60%"></td>
					</tr>
					<tr>
						<td>服务器标识</td>
						<td></td>
					</tr>
					<tr>
						<td>服务器操作系统</td>
						<td></td>
					</tr>
					<tr>
						<td>服务器解译引擎</td>
						<td></td>
					</tr>
					<tr>
						<td>服务器语言</td>
						<td></td>
					</tr>
					<tr>
						<td>服务器端口</td>
						<td></td>
					</tr>
					<tr>
						<td>服务器主机名</td>
						<td></td>
					</tr>
					<tr>
						<td>站点名称</td>
						<td></td>
					</tr>
					<tr>
						<td>目录物理路径</td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">数据库信息</h2>
			<div class="layui-colla-content">
				<table class="layui-table">
					<tr>
						<td width="40%">数据库版本</td>
						<td width="60%">Mysql 5.7</td>
					</tr>
					<tr>
						<td>数据库名称</td>
						<td>badmin</td>
					</tr>
					<tr>
						<td>数据库大小</td>
						<td>10k</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">系统参数</h2>
			<div class="layui-colla-content">
				<table class="layui-table">
					<tr>
						<td>上传文件最大限制</td>
						<td>2M</td>
					</tr>
					<tr>
						<td>脚本占用最大内存</td>
						<td>100KB</td>
					</tr>
					<tr>
						<td>POST提交最大限制</td>
						<td>1000QPS</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use([ 'layer', 'element' ], function() {
			var layer = layui.layer, element = layui.element();
			element.on('collapse(collapse)', function(data) {
					
			});
		});
	</script>
</body>
</html>