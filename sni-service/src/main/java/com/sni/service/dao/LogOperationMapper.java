package com.sni.service.dao;

import com.sni.service.model.LogOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogOperationMapper {

    List<LogOperation> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(LogOperation record);

    int insertSelective(LogOperation record);

    LogOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogOperation record);

    int updateByPrimaryKey(LogOperation record);
}