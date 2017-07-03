/**
 * Created by Administrator on 2017/6/27 0027.
 * @desc:  JS公共方法封装模块
 * @author: Chenjx
 */
layui.define(['layer'], function (exports) {
	"use strict";
	var $ = layui.jquery ,jacommon = {};
	
	function _empty() {}
	
	// 需要确认弹出框
	jacommon.confirm = function (text, callback, title) {
		layer.confirm(text, {
            title: title || '操作提醒',
            btnAlign: 'c',
            resize: false,
            icon: 3,
            btn: ['确定', '取消'],
            yes: callback
        });
	}
	
	// 成功弹出提醒
	jacommon.alertSuccess = function (text, title) {
        layer.alert(text, { title: title || '温馨提示', icon: 1, time: 1000, resize: false, zIndex: layer.zIndex, anim: Math.ceil(Math.random() * 6) });
    }
	
    // 错误弹出提示
	jacommon.alertError = function (text, title) {
        layer.alert(text, { title: title || '操作提醒', icon: 2, time: 2000, resize: false, zIndex: layer.zIndex, anim: Math.ceil(Math.random() * 6) });
    }
	
	// 成功提醒
	jacommon.success = function (text, callback) {
		layer.msg(text, { icon: 1, time: 500 }, callback || _empty); 
	}
	
	// 错误提示
	jacommon.error = function (text, callback) {
		layer.msg(text, { icon: 2, time: 2000 }, callback || _empty); 
	}
	
	// 弹出一个错误提示
	jacommon.msgError = function(msg) {
		layer.msg(msg, {icon: 5});
	}
	
	//正上方提示
	jacommon.msgOffsetError = function(msg, callback) {
		//正上方
		layer.msg(msg, {
			offset: 't',
			anim: 6,
			icon: 5
		}, callback || _empty);
	}
	
	//正上方提示
	jacommon.msgOffsetSuccess = function(msg, callback) {
		//正上方
		layer.msg(msg, {
			offset: 't',
			anim: 6,
			icon: 1
		}, callback || _empty);
	}
	
	// 抛出一个异常错误信息
	jacommon.throwError = function(msg) {
		throw new Error(msg);
	}
	
    // ajax请求
	jacommon.ajax = function(url, type, data, success, fail, complete) {
		if ($.isFunction(data)) complete = fail, fail = success, success = data, data = undefined;
    	$.ajax({
            url: url,
            type: type,
            dataType: 'json',
            data: data,
            success: function (res) {
            	res.code == 1 ? (success && success(res)) : (fail && fail(res));
            },
            error: function(xhr, status, error) {
            	(fail && fail({errorCode:-1, msg:error}, xhr, status, error));
            },
            complete: complete
        });
    }
	
	// ajax get请求
	jacommon.ajaxGet = function(url, data, success, error, complete) {
		jacommon.ajax(url, 'get', data, success, error, complete);
	}
	
	// ajax post请求
	jacommon.ajaxPost = function(url, data, success, error, complete) {
		jacommon.ajax(url, 'post', data, success, error, complete);
	}
	
	// 数组元素下标
	jacommon.indexOf = function(array, val) {
		for (var i = 0; i < array.length; i++) {
			if (array[i] == val) return i;
		}
		return -1;
	}
	
	// 移除数组某一个元素
	jacommon.remove = function(array, val) {
		var index = jacommon.indexOf(array, val);
		if (index > -1) {
			array.splice(index, 1);
		}
	}
	
	
	// 字符串日期转时间戳
	jacommon.parseStr2TimeStamp = function(stringTime) {
		var timestamp = Date.parse(new Date(stringTime));
		return (timestamp / 1000);
	}
	
	exports("jacommon", jacommon);
});