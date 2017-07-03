# 百灵权限管理系统

## 简介

百灵权限管理系统，是一款构建在用户、角色、权限、日志、在线用户、用户下线、系统设置、日志管理之基础上等开源权限管理系统。 当前系统允许通过设置角色对应的权限，而权限通过Shiro可以控制到相应的操作菜单、操作按钮，并通过角色授权给用户在细化后权限范围内进行相关操作；同时，系统支持通过登录参数相关设置，支持一个账号多地登录和单地登录，同时可以对已登录账号，进行在线查看和强制下线某地登录账号的支持。

## 内置功能

1.	用户管理：用户（删除、添加、启用、禁用、重置密码、分配角色、编辑修改）。
2.	角色管理：角色（删除、添加、启用、禁用、角色授权、编辑修改）。
3.	权限管理：权限（添加、删除、修改），可以控制权限到系统菜单，操作权限，按钮权限标识等。
4.  在线用户： 在线用户（刷新、强制下线）。

5.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
6. 系统参数：网站设置和登录相关参数设置。

## 技术选型

1、后端

* 核心框架：Spring Framework 4.2.5
* 安全框架：Apache Shiro 1.2.2
* 视图框架：Spring MVC 4.2.5
* 持久层框架：MyBatis 3.2.5
* 数据库连接池：Alibaba Druid 1.0
* 缓存框架：Ehcache 2.6
* 日志管理：Log4j
* 工具类：Apache Commons、Guava

2、前端

* JS框架：LayerUI。
* 客户端验证：Layer form验证。
* 富文本在线编辑：Layer
* 动态页签：Freemarker
* 数据表格：jquery.dataTables
* 树结构控件：jQuery zTree
* 日期控件： Layer Date

4、平台

* 服务器中间件：在Java EE 5规范（Servlet 2.5、JSP 2.1）下开发，支持应用服务器中间件
有Tomcat 8+、 JDK8 + ；
* 数据库支持：目前仅提供MySq 5.7；
* 开发环境：Java、Eclipse Java EE 4.3、Maven 3.1、Git；

5、演示配图

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/1.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/2.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/3.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/4.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/5.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/6.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/7.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/8.png)

![image](https://github.com/butter-fly/belling-admin/blob/master/src/main/webapp/peitu/9.png)


