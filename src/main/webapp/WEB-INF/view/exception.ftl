
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>你所访问的页面不存在 (404) _ <@getDict key="site_name"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict></title>
	<meta name="description" content="<@getDict key="meta_description"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
	<meta name="keywords" content="<@getDict key="site_keywords"><#if val??>${val}<#else>百灵权限管理系统</#if></@getDict>" />
	<style type="text/css">
		body{color:#666;text-align:center;font-family:Helvetica,'microsoft yahei',Arial,sans-serif;margin:0;width:800px;margin:auto;font-size:14px}
		h1{font-size:56px;line-height:100px;font-weight:400;color:#456}
		h2{font-size:24px;color:#666;line-height:1.5em}
		h3{color:#456;font-size:20px;font-weight:400;line-height:28px}
		hr{margin:18px 0;border:0;border-top:1px solid #EEE;border-bottom:1px solid #fff}
		a{color:#17bc9b;text-decoration:none}
	</style>
</head>
<body>
	<h1>500</h1>
	<h3>${message!"很抱歉，系统内部发生异常"}.</h3>
	<hr />
	<p>
		资源不存在或者没有访问权限， <a href="${base}/logout">点击这里</a> 回到首页.
	</p>
</body>
</html>
