package cn.ray.web.formbean;

import java.util.HashMap;
import java.util.Map;

public class RegisterForm {
    /*
     * 表单提交过来的全部是字符串
     */
    private String username;
    private String password;
    private String password2;
    

    private Map errors = new HashMap();

    public Map getErrors() {
        return errors;
    }
    public void setErrors(Map errors) {
        this.errors = errors;
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
        this.password = password;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
   

    /*
     * 校验规则：
     * 
     * 用户名不能为空，并且要是3-8位字母
     * 密码不能为空，并且要是3-8位数字
     * 确认密码不能为空，并且要和一次密码一致
     */
    public boolean validate() {
        boolean isOk = true;
        if(this.username == null || this.username.trim().equals("")) {
            isOk = false;
            errors.put("username", "用户名不能为空！！！");
        } else {
            if(!this.username.matches("[A-Za-z]{3,8}")) {
                isOk = false;
                errors.put("username", "用户名必须是3~8位字母！！！");
            }
        }

        if(this.password == null || this.password.trim().equals("")) {
            isOk = false;
            errors.put("password", "密码不能为空！！！");
        } else {
            if(!this.password.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("password", "密码必须是3~8位数字！！！");
            }
        }

        if(this.password2 == null || this.password2.trim().equals("")) {
            isOk = false;
            errors.put("password2", "确认密码不能为空！！！");
        } else {
            if(!this.password.equals(this.password2)) {
                isOk = false;
                errors.put("password2", "两次密码要一致！！！");
            }
        }


        return isOk;
    }
}
