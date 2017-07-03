package com.belling.admin.controller.houtai;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belling.admin.model.User;
import com.belling.admin.service.UserService;
import com.belling.base.controller.BaseController;
import com.belling.base.exception.ValidateException;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.belling.base.provider.PasswordProvider;
import com.google.common.base.Strings;


/**
 * @author Sunny
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	/**
	 * 用户业务对象
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 默认密码
	 */
	@Value("${system.init.password}")
	private String password;

	/**
	 * 用户列表首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/user/list";
	}

	/**
	 * 编辑用户
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		User user;
		if (id == null) {
			user = new User();
		} else {
			user = userService.get(id);
		}
		model.addAttribute("vo", user);
		return "/user/edit";
	}
	
	/**
	 * 用户分页
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public TablePageResult page(HttpServletRequest req, Integer draw) {
		String kw = req.getParameter("kw");
		String curpage = req.getParameter("start");
		if (Strings.isNullOrEmpty(curpage)) {
			curpage = "0";
		}
		int start = Integer.parseInt(curpage);
		if (start <= 0) {
			start = 0;
		}
		String curlg = req.getParameter("length");
		if (Strings.isNullOrEmpty(curlg)) {
			curlg = "1";
		}
		int length = Integer.parseInt(curlg);
		if (length <=  0) {
			length = 8;
		}
		
		Pagination<User> page = new Pagination<User>();
		page = userService.findPaginationByAccount(kw, null, new Pagination<User>((start / length) + 1, length));
		return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
	}
	
	/**
	 * 保存或更新用户
	 * 
	 * @param id
	 * @param account
	 * @param password
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseResult save(Integer id, String account, String password, Boolean isEnable) {
		User user;
		if (id == null) {
			if (Strings.isNullOrEmpty(password)) {
				throw new ValidateException("密码不能为空");
			}
			user = new User();
			user.setCreateTime(new Date());
		} else {
			user = userService.get(id);
		}
		user.setAccount(account);
		if (!Strings.isNullOrEmpty(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setIsEnable(isEnable);
		userService.save(user);
		return ResponseResult.createSuccessResult();
	}
	
	
	/**
	 * 重置密码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody ResponseResult resetPassword(String ids) {
		userService.resetPassword(PasswordProvider.encrypt(password), getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}
	
	/**
	 * 是否启用
	 * 
	 * @param ids
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody ResponseResult enable(String ids, Boolean isEnable) {
		userService.enable(isEnable, getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}

 
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(String ids) {
		userService.deleteById(getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}
}