package com.lab221.lab221asm6.models;

import com.lab221.lab221asm6.entities.User;

public class UserModel {

    private int id;
    private String username;
    private String fullname;
    private String password;
    private int attempt;
    private boolean isLocked;
    private boolean isFirstTimeLogin;
    private boolean hasToChangePassword;

    public UserModel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public boolean isHasToChangePassword() {
        return hasToChangePassword;
    }

    public void setHasToChangePassword(boolean hasToChangePassword) {
        this.hasToChangePassword = hasToChangePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setFullname(this.getFullname());
        user.setPassword(this.getPassword());
        user.setAttempt(this.getAttempt());
        user.setLocked(this.isLocked());
        user.setFirstTimeLogin(this.isFirstTimeLogin());
        return user;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", attempt=" + attempt +
                ", isLocked=" + isLocked +
                ", isFirstTimeLogin=" + isFirstTimeLogin +
                ", hasToChangePassword=" + hasToChangePassword +
                '}';
    }
}
