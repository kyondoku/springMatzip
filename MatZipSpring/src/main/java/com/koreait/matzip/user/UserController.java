package com.koreait.matzip.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.user.model.UserPARAM;
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
	public String login(UserPARAM param, HttpSession hs, RedirectAttributes ra) {
		int result = service.login(param);
		
		if(result == Const.SUCCESS) {
			hs.setAttribute(Const.LOGIN_USER, param);
			return "redirect:/rest/map";
		}
		
		String msg = null;
		if(result == Const.NO_ID) {
			msg = "아이디를 확인해 주세요.";
		} else if(result == Const.NO_PW) {
			msg = "비밀번호를 확인해 주세요.";
		}
		
		param.setMsg(msg);
		ra.addFlashAttribute("data", param); // 세션에 박히고 쓰자마자(응답할때)지운다
		return "redirect:/user/login";
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
	
	@RequestMapping(value="/join", method=RequestMethod.POST)	 // 주소이동
	public String join(UserVO param, RedirectAttributes ra) {
		int result = service.join(param);
		
		if(result == 1) {
			return "redirect:/user/login";
		} 
		ra.addAttribute("err", result);
		// addAttribute로 하면 쿼리스트링 / FlashAttribute는 세션에 박아준다.
		return "redirect:/user/join";
	}
	
	@RequestMapping(value="/ajaxIdChk", method=RequestMethod.POST)	// 응답
	@ResponseBody
	public String ajaxIdChk(@RequestBody UserPARAM param) {
		int result = service.login(param);
		return String.valueOf(result);
	}
	
	
}
