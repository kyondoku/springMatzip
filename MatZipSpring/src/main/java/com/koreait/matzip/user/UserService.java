package com.koreait.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserDTO;
import com.koreait.matzip.user.model.UserVO;

@Service
public class UserService {
	//mapper에 주소값을 받도록 해준다.
	@Autowired
	private UserMapper mapper;
	
	//1번 로그인 성공, 2번 아이디 없음, 3번 비번 틀림
	public int login(UserDTO param) {
		int result;

		if(param.getUser_id().equals("")) {
			return Const.NO_ID;
		}
		
		UserDMI dbUser = mapper.selUser(param);	
		System.out.println("user_id : " + dbUser.getUser_id());
		System.out.println("user_pw : " + dbUser.getUser_pw());
		
		String salt = dbUser.getSalt();
		String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt);
		
		if(encryptPw.equals(dbUser.getUser_pw())) {
			param.setUser_pw(null);
			param.setI_user(dbUser.getI_user());
			param.setNm(dbUser.getNm());
			param.setProfile_img(dbUser.getProfile_img());
			
			result = 1;
		} else {
			result = 3;
		}
		return result;
	}
	
	public int join(UserVO param) {
		
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setSalt(salt);
		param.setUser_pw(cryptPw);
		
		return mapper.insUser(param);
	}
	
}
