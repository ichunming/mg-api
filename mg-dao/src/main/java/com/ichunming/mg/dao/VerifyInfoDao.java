package com.ichunming.mg.dao;

import com.ichunming.mg.model.VerifyInfo;

public interface VerifyInfoDao extends GenericDao<VerifyInfo, Integer> {
    public VerifyInfo getByContent(VerifyInfo content);
}