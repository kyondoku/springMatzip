package com.koreait.matzip.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestVO;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	@Autowired
	private RestService service;
	
	@RequestMapping("/map")
	public String map(Model model) {
		model.addAttribute(Const.TITLE, "지도보기");
		model.addAttribute(Const.VIEW, "/rest/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	@RequestMapping(value="/restReg", method=RequestMethod.GET)
	public String restReg(Model model) {
		model.addAttribute(Const.TITLE, "레스토랑 등록");
		model.addAttribute(Const.VIEW, "rest/restReg");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/restReg", method=RequestMethod.POST)
	public String restReg(RestVO param) {
		int result = service.insRest(param);
		
		if(result == 1) {
			return "redirect:/rest/restMap";			
		} else {
			return "redirect:/rest/restReg";			
		}
	}

	@RequestMapping("/ajaxGetList")
	@ResponseBody public String ajaxGetList(RestPARAM param) {
		
		System.out.println("sw_lat:" + param.getSw_lat());
		System.out.println("sw_lng:" + param.getSw_lng());
		System.out.println("ne_lat:" + param.getNe_lat());
		System.out.println("ne_lng:" + param.getNe_lng());
		
		
		return service.selRestList(param);
	}
	
}
