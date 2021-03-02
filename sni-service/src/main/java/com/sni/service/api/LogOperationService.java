package com.sni.service.api;

import com.sni.service.model.LogOperation;

import java.util.List;

public interface LogOperationService {

    void insertLogOperation(LogOperation logOperation);

    /**
     * 查询所有日志
     * @return
     */
    List<LogOperation> findAll();
}
