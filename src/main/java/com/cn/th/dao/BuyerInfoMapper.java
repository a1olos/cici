package com.cn.th.dao;


import org.springframework.stereotype.Repository;

import com.cn.th.bean.BuyerInfo;

@Repository
public interface BuyerInfoMapper {
	
    int deleteByPrimaryKey(Integer userid);

    int insert(BuyerInfo record);

    int insertSelective(BuyerInfo record);

    BuyerInfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(BuyerInfo record);

    int updateByPrimaryKey(BuyerInfo record);
}