package com.sni.service.impl;

import com.sni.service.api.LogExceptionService;
import com.sni.service.dao.LogExceptionMapper;
import com.sni.service.model.LogException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("logExceptionService")
public class LogExceptionServiceImpl implements LogExceptionService {

    @Resource
    private LogExceptionMapper logExceptionMapper;

    @Override
    public void insert(LogException logException) {
        logExceptionMapper.insert(logException);
    }
}
