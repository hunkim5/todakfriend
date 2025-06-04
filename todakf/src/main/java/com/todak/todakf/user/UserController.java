package com.todak.todakf.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todak.todakf.common.dto.PageDTO;
import com.todak.todakf.user.dto.UserDto;
import com.todak.todakf.user.service.UserService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	private String contextView="user/";

	@GetMapping("/login")
	public String login(Model model) {
		return contextView+"login";
	}

	@GetMapping("/list")
	public String glist(Model model,@ModelAttribute UserDto param) {
		return getList(model,param);
	}
	@PostMapping("/list")
	public String list(Model model,@ModelAttribute UserDto param) {
		return getList(model,param);
	}
	private String getList(Model model,UserDto param) {
		int cnt = service.userCount(param);
		PageDTO page = new PageDTO(cnt,param.getPage());
		param.setOffset(page.getOffset());
		param.setPageSize(page.getPageSize());
		List<UserDto> list = service.userList(param);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		return contextView+"list";
	}
	@GetMapping("/registerForm")
	public String registerForm(Model model) {
		return contextView+"register";
	}
	@PostMapping("/register")
    public ResponseEntity<Map<String,String>> saveRegister(@ModelAttribute UserDto param) {
        return new ResponseEntity<>(service.register(param),HttpStatus.OK);
    }
}
