package com.ichunming.mg.dao;

import com.ichunming.mg.model.VerifyInfo;

public interface VerifyInfoDao extends GenericDao<VerifyInfo, Integer> {
    public VerifyInfo getByContent(VerifyInfo verifyInfo);
    
    public VerifyInfo getRecent(String receiver);
    
    public void insertToHis(VerifyInfo verifyInfo);
}