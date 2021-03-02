package com.sni.web.aspect;

import com.google.gson.Gson;
import com.sni.service.api.LogExceptionService;
import com.sni.service.api.LogOperationService;
import com.sni.service.constant.SymbolConstant;
import com.sni.service.model.LogException;
import com.sni.service.model.LogOperation;
import com.sni.web.annotation.OperationLog;
import com.sni.web.utils.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 操作日志记录切面类
 *
 * @author Gaoyang
 * @date 2021/2/8 14:04
 */
@Aspect
@Component
public class OperationLogAspect {

    @Value("${project.version}")
    private String projectVersion;

    @Autowired
    private LogOperationService logOperationService;

    @Autowired
    private LogExceptionService logExceptionService;

    /**
     * 设置切入点，在有此注解的位置切入代码
     *
     * @return
     * @params
     * @date 2021/2/9 14:56
     */
    @Pointcut("@annotation(com.sni.web.annotation.OperationLog)")
    public void operateLogPointCut() {

    }

    /**
     * 设置异常切入点
     */
    @Pointcut("execution(* com.sni.web.controller..*.*(..))")
    public void operateExceptionLogPointCut() {

    }

    /**
     * 正常返回时的通知，拦截用户操作日志，连接点正常执行完成后执行，如果连接点抛出异常，则不会执行
     *
     * @param joinPoint
     * @param returnParam
     */
    @AfterReturning(value = "operateLogPointCut()", returning = "returnParam")
    public void saveOperationLog(JoinPoint joinPoint, Object returnParam) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Map<String, String> paramsMap = convertToMap(request.getParameterMap());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Optional<OperationLog> operationLog = Optional.ofNullable(method.getAnnotation(OperationLog.class));
        String className = joinPoint.getTarget().getClass().getName();
        Gson toJson = new Gson();

        LogOperation logOperation = new LogOperation();
        logOperation.setOpMethod(className + SymbolConstant.Punctuation.COMMA + method.getName());
        logOperation.setOpVersion(projectVersion);
        logOperation.setOpReqParam(toJson.toJson(paramsMap));
        logOperation.setOpRespParam(toJson.toJson(returnParam));
        logOperation.setOpUri(request.getRequestURI());
        logOperation.setOpIp(IpUtil.getIpAddress(request));
        logOperation.setOpRespTime(new Date());
        operationLog.ifPresent(s -> {
            logOperation.setOpModule(s.operationModule());
            logOperation.setOpType(s.operationType());
            logOperation.setOpDesc(s.operationDesc());
        });

        logOperationService.insertLogOperation(logOperation);
    }

    /**
     * 连接点抛出异常后执行，拦截异常日志信息
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "operateExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        Gson gson = new Gson();

        LogException logException = new LogException();
        logException.setExcMethod(className + SymbolConstant.Punctuation.COMMA + method.getName());
        logException.setExcReqTime(new Date());
        logException.setExcReqParam(gson.toJson(convertToMap(request.getParameterMap())));
        logException.setExcUri(request.getRequestURI());
        logException.setExcIp(IpUtil.getIpAddress(request));
        logException.setExcName(e.getClass().getName());
        logException.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
        logException.setExcVersion(projectVersion);

        logExceptionService.insert(logException);
    }

    private Map<String, String> convertToMap(Map<String, String[]> paramsMap) {
        Map<String, String> rtnMap = new HashMap<>();
        for (String key : paramsMap.keySet()) {
            rtnMap.put(key, paramsMap.get(key)[0]);
        }
        return rtnMap;
    }

    private String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : elements) {
            sb.append(element + SymbolConstant.Formatter.ENTER);
        }

        return exceptionName + SymbolConstant.Punctuation.COLON + exceptionMessage +
                  SymbolConstant.Formatter.ENTER + SymbolConstant.Formatter.INDENT + sb.toString();
    }
}
