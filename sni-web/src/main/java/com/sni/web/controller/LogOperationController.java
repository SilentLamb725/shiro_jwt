package com.sni.web.controller;

import com.sni.service.api.LogOperationService;
import com.sni.service.model.LogOperation;
import com.sni.web.annotation.OperationLog;
import com.sni.web.constant.OperationLogCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/logOperation")
public class LogOperationController {

    @Autowired
    private LogOperationService logOperationService;

    @ResponseBody
    @GetMapping("/list")
    @OperationLog(operationModule = OperationLogCons.OperationModule.LOG_OPERATION, operationType = OperationLogCons.OperationType.LOG_OPERATION_LIST)
    public List<LogOperation> list() {
        return logOperationService.findAll();
    }
}
