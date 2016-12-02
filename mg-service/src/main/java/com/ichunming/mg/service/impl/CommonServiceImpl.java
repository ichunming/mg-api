/**
 * Common Service Impl
 * create by ming 2016/11/25
 */
package com.ichunming.mg.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ichunming.mg.common.constant.ErrorCode;
import com.ichunming.mg.common.util.DateUtil;
import com.ichunming.mg.dao.VerifyInfoDao;
import com.ichunming.mg.entity.vo.BaseResult;
import com.ichunming.mg.model.VerifyInfo;
import com.ichunming.mg.service.ICommonService;

@Service
public class CommonServiceImpl implements ICommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private VerifyInfoDao verifyDao;
	
	@Override
	public BaseResult verifyCode(String receiver, int type, String code) {
		// verify code
		logger.debug("verify code[" + code + "]");
		VerifyInfo verifyInfo = new VerifyInfo();
		verifyInfo.setReceiver(receiver);
		verifyInfo.setType(type);
		verifyInfo.setContent(code);
		
		// get information
		logger.debug("get information about code...");
		verifyInfo = verifyDao.getByContent(verifyInfo);
		
		BaseResult result = new BaseResult(ErrorCode.SUCCESS);
		
		// check code
		logger.debug("check code...");
		if(null == verifyInfo) {
			// invalid
			logger.debug("code invalid.");
			result.setCode(ErrorCode.ERR_USER_VERIFY_CODE_INVALID);
		} else if(DateUtil.isDateTimeAfter(DateUtil.current(), verifyInfo.getExpireDate())) {
			// overdue
			logger.debug("code overdue.");
			result.setCode(ErrorCode.ERR_USER_VERIFY_CODE_OVERDUE);
		} else {
			// set code overdue
			logger.debug("set code overdue...");
			verifyInfo.setExpireDate(DateUtil.current());
			verifyDao.update(verifyInfo);
		}
		
		return result;
	}
}
