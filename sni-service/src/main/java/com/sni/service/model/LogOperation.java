package com.sni.service.model;

import java.io.Serializable;
import java.util.Date;

public class LogOperation implements Serializable {

    private static final long serialVersionUID = -4024331473017414989L;

    private Integer id;

    private String opModule;

    private String opType;

    private String opDesc;

    private String opMethod;

    private Date opReqTime;

    private String opReqParam;

    private Date opRespTime;

    private String opRespParam;

    private String opIp;

    private Integer opUserId;

    private String opUserName;

    private String opUri;

    private String opVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpModule() {
        return opModule;
    }

    public void setOpModule(String opModule) {
        this.opModule = opModule == null ? null : opModule.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc == null ? null : opDesc.trim();
    }

    public String getOpMethod() {
        return opMethod;
    }

    public void setOpMethod(String opMethod) {
        this.opMethod = opMethod == null ? null : opMethod.trim();
    }

    public Date getOpReqTime() {
        return opReqTime;
    }

    public void setOpReqTime(Date opReqTime) {
        this.opReqTime = opReqTime;
    }

    public String getOpReqParam() {
        return opReqParam;
    }

    public void setOpReqParam(String opReqParam) {
        this.opReqParam = opReqParam == null ? null : opReqParam.trim();
    }

    public Date getOpRespTime() {
        return opRespTime;
    }

    public void setOpRespTime(Date opRespTime) {
        this.opRespTime = opRespTime;
    }

    public String getOpRespParam() {
        return opRespParam;
    }

    public void setOpRespParam(String opRespParam) {
        this.opRespParam = opRespParam == null ? null : opRespParam.trim();
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp == null ? null : opIp.trim();
    }

    public Integer getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Integer opUserId) {
        this.opUserId = opUserId;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName == null ? null : opUserName.trim();
    }

    public String getOpUri() {
        return opUri;
    }

    public void setOpUri(String opUri) {
        this.opUri = opUri == null ? null : opUri.trim();
    }

    public String getOpVersion() {
        return opVersion;
    }

    public void setOpVersion(String opVersion) {
        this.opVersion = opVersion == null ? null : opVersion.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", opModule=").append(opModule);
        sb.append(", opType=").append(opType);
        sb.append(", opDesc=").append(opDesc);
        sb.append(", opMethod=").append(opMethod);
        sb.append(", opReqTime=").append(opReqTime);
        sb.append(", opReqParam=").append(opReqParam);
        sb.append(", opRespTime=").append(opRespTime);
        sb.append(", opRespParam=").append(opRespParam);
        sb.append(", opIp=").append(opIp);
        sb.append(", opUserId=").append(opUserId);
        sb.append(", opUserName=").append(opUserName);
        sb.append(", opUri=").append(opUri);
        sb.append(", opVersion=").append(opVersion);
        sb.append("]");
        return sb.toString();
    }
}