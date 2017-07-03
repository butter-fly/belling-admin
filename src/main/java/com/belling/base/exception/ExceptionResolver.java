package com.belling.base.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.belling.admin.dto.UserOnlineDTO;
import com.belling.base.contants.ResponseCode;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.belling.base.util.LoggerUtils;
import com.belling.base.util.ServletUtil;

/**
 * 统一异常处理
 * 
 * @author Sunny
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		Object result = null;
		String url = request.getRequestURI();//请求URL
		ModelAndView mv = new ModelAndView();
		boolean isJson = ServletUtil.isJSONResponse(request);//是否需要返回json格式数据
		if(exception instanceof org.apache.shiro.authz.UnauthorizedException){
			//没有访问权限
			System.out.println("***没有访问权限:" + url + "  ***" + exception.getMessage());
			mv.addObject("message", "抱歉，您没有当前的操作权限！");//没有操作权限
			LoggerUtils.fmtError(ExceptionResolver.class, exception, exception.getMessage());
		} else if (exception instanceof BaseException) {
			BaseException ae = (BaseException) exception;
			result = ResponseResult.create(ae.getCode()).setMessage(ae.getMessage());
		} else if (exception instanceof ExpiredSessionException) {
			// 捕获因Session会话失效被shiro过滤器拦截异常处理响应数据格式不对，导致tabels js报错问题
			result = TablePageResult.createSuccessResult(new ArrayList<UserOnlineDTO>(), 0, 1);
		} else {
			mv.addObject("message", exception.getMessage());//没有操作权限
			result = ResponseResult.create(ResponseCode.ERROR).setMessage("未知错误");
			LoggerUtils.fmtError(ExceptionResolver.class, exception, exception.getMessage());
		}
		//最后返回错误提示信息
		if(isJson){
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpStatus.OK.value());
			try {
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSONString(result));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				LoggerUtils.fmtError(ExceptionResolver.class, e, "Failed to serialize the object to json for exception resolver!");
			}
		} else{
			//不需要返回json格式，直接返回错误提示页面
			mv.setViewName("exception");
		}
		return mv;
	}
}
