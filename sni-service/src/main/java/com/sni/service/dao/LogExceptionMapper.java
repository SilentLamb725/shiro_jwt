package com.sni.service.dao;

import com.sni.service.model.LogException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogExceptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogException record);

    int insertSelective(LogException record);

    LogException selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogException record);

    int updateByPrimaryKeyWithBLOBs(LogException record);

    int updateByPrimaryKey(LogException record);
}