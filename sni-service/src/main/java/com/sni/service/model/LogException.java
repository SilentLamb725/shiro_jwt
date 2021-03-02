package com.sni.service.model;

import java.io.Serializable;
import java.util.Date;

public class LogException implements Serializable {

    private static final long serialVersionUID = -8272117305933401221L;

    private Integer id;

    private String excMethod;

    private Date excReqTime;

    private String excReqParam;

    private String excName;

    private String excIp;

    private Integer excUserId;

    private String excUserName;

    private String excUri;

    private String excVersion;

    private String excMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExcMethod() {
        return excMethod;
    }

    public void setExcMethod(String excMethod) {
        this.excMethod = excMethod == null ? null : excMethod.trim();
    }

    public Date getExcReqTime() {
        return excReqTime;
    }

    public void setExcReqTime(Date excReqTime) {
        this.excReqTime = excReqTime;
    }

    public String getExcReqParam() {
        return excReqParam;
    }

    public void setExcReqParam(String excReqParam) {
        this.excReqParam = excReqParam == null ? null : excReqParam.trim();
    }

    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName == null ? null : excName.trim();
    }

    public String getExcIp() {
        return excIp;
    }

    public void setExcIp(String excIp) {
        this.excIp = excIp == null ? null : excIp.trim();
    }

    public Integer getExcUserId() {
        return excUserId;
    }

    public void setExcUserId(Integer excUserId) {
        this.excUserId = excUserId;
    }

    public String getExcUserName() {
        return excUserName;
    }

    public void setExcUserName(String excUserName) {
        this.excUserName = excUserName == null ? null : excUserName.trim();
    }

    public String getExcUri() {
        return excUri;
    }

    public void setExcUri(String excUri) {
        this.excUri = excUri == null ? null : excUri.trim();
    }

    public String getExcVersion() {
        return excVersion;
    }

    public void setExcVersion(String excVersion) {
        this.excVersion = excVersion == null ? null : excVersion.trim();
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage == null ? null : excMessage.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", excMethod=").append(excMethod);
        sb.append(", excReqTime=").append(excReqTime);
        sb.append(", excReqParam=").append(excReqParam);
        sb.append(", excName=").append(excName);
        sb.append(", excIp=").append(excIp);
        sb.append(", excUserId=").append(excUserId);
        sb.append(", excUserName=").append(excUserName);
        sb.append(", excUri=").append(excUri);
        sb.append(", excVersion=").append(excVersion);
        sb.append(", excMessage=").append(excMessage);
        sb.append("]");
        return sb.toString();
    }
}