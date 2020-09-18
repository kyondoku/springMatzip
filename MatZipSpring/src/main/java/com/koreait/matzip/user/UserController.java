package com.koreait.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.user.model.UserDTO;
import com.koreait.matzip.user.model.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//bean이 등록된 애들중에 들어갈수 있는 애를 찾는다.
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(Const.TITLE,"로그인");
		model.addAttribute(Const.VIEW,"user/login");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserDTO param) {
		int result = service.login(param);
		
		if(result == 1) {
			return "redirect:/rest/map";
		}
		return "redirect:/user/login?err=" + result;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
							//					required가 true이면 쿼리스트링에 err값이 반드시 존재해야한다. 
	public String join(Model model, @RequestParam(defaultValue="0") int err) {
		System.out.println("err: " + err);
		
		if(err > 0) {
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		model.addAttribute(Const.TITLE,"회원가입");
		model.addAttribute(Const.VIEW,"user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO param) {
		int result = service.join(param);
		
		if(result == 1) {
			return "redirect:/user/login";
		} 
		
		return "redirect:/user/join?err=" + result;
	}
	
	
}
