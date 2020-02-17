package cn.com.smer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.com.smer.dtoform.UsersForm;
import cn.com.smer.service.UsersService;
/*
 * 	��¼����
 * 		1.���ڵ�¼�û������Ϣ��֤�Ĺ���
 */
@Controller
@SessionAttributes("userName")
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	private UsersService userservice;

	@RequestMapping(value = "/list", params = { "form" })
	public String list(Model model) {
		model.addAttribute("flag", "");
		return "/login";
	}

	// home��ť����main������
	@RequestMapping(value = "/list", params = { "main" })
	public String listMain(Model model, @ModelAttribute("userName") String userName) {
		model.addAttribute("userName", userName);
		return "/main";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listForm(UsersForm form, Model model) {
		if (userservice.select(form)) {
			model.addAttribute("userName", form.getUserName());
			return "redirect:/login/list?main";
		} else {
			model.addAttribute("flag", "flag");
			return "/login";
		}
	}
}
