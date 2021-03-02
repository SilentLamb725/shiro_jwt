package com.sni.service.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 2181268384681606467L;

    private Integer id;

    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Date lastLogInTime;

    private Byte isDisabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLogInTime() {
        return lastLogInTime;
    }

    public void setLastLogInTime(Date lastLogInTime) {
        this.lastLogInTime = lastLogInTime;
    }

    public Byte getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Byte isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "User{" +
                  "id=" + id +
                  ", username='" + username + '\'' +
                  ", password='" + password + '\'' +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
                  ", lastLogInTime=" + lastLogInTime +
                  ", isDisabled=" + isDisabled +
                  '}';
    }
}