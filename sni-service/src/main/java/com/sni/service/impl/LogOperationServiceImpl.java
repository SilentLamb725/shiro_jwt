package com.sni.service.impl;

import com.sni.service.api.LogOperationService;
import com.sni.service.dao.LogOperationMapper;
import com.sni.service.model.LogOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("logOperationService")
public class LogOperationServiceImpl implements LogOperationService {

    @Resource
    private LogOperationMapper logOperationMapper;

    @Override
    public void insertLogOperation(LogOperation logOperation) {
        logOperationMapper.insert(logOperation);
    }

    @Override
    public List<LogOperation> findAll() {
        return logOperationMapper.selectAll();
    }
}
