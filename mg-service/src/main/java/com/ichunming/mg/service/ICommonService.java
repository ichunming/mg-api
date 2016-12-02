package com.ichunming.mg.service;

import com.ichunming.mg.entity.vo.BaseResult;

public interface ICommonService {

	public BaseResult verifyCode(String receiver, int type, String code);
}
