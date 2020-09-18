package com.koreait.matzip;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.koreait.matzip.user.model.UserVO;



public class SecurityUtils {
	
	public static int getLoginUserPk(HttpServletRequest request) {
		return getLoginUser(request).getI_user();
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
		
	}
	
	public static boolean isLogout(HttpServletRequest request) {
		return getLoginUser(request) == null; // null이면 true !null이면 false 
	}

	public static String generateSalt() {
		return BCrypt.gensalt();
	}

	public static String getEncrypt(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}




}
