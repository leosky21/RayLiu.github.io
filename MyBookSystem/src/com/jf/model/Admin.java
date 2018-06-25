package com.jf.model;

import java.io.Serializable;

/**
 * 管理员类
 */
public class Admin implements Serializable {
    private Integer id; // 编号
    private String username; // 用户名【用户登录】
    private String name; // 管理员姓名【可以理解为昵称】
    private String password; // 密码
    private String phone; // 联系方式
    private Authorization authorization;// 权限
    private Integer state; // 删除状态 【0刪除】

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Admin() {

    }

    public Admin(Integer id) {
        this.id = id;
    }
}
