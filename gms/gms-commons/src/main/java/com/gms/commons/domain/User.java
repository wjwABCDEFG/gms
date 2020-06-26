package com.gms.commons.domain;

public class User {

    private Integer user_id;
    private Integer role_id;
    private String username;
    private String realname;
    private String rolename;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private Integer status;
    private Integer credit;

    public User() {
    }

    public User(Integer user_id, Integer role_id, String username, String realname, String rolename, String password, String salt, String email, String phone, Integer status, Integer credit) {
        this.user_id = user_id;
        this.role_id = role_id;
        this.username = username;
        this.realname = realname;
        this.rolename = rolename;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.credit = credit;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", role_id=" + role_id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", rolename='" + rolename + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", credit=" + credit +
                '}';
    }
}
