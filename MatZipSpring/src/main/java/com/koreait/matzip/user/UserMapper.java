package com.koreait.matzip.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserPARAM;
import com.koreait.matzip.user.model.UserVO;

// 매핑되는 xml파일을 찾는다.
@Mapper
public interface UserMapper {
	public int insUser(UserVO p);
	public int insFavorite(UserPARAM param);
	
	public UserDMI selUser(UserPARAM p);
	public List<UserDMI> selFavoriteList(UserPARAM param);
	
	public int delFavorite(UserPARAM param);
}
