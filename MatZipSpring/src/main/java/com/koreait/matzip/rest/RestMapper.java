package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestVO;

@Mapper
public interface RestMapper {
	public List<RestDMI> selRestList(RestPARAM p);
	public int insRest(RestVO p);
	public RestDMI selRest(RestPARAM p);
}
