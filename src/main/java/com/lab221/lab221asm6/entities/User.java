package com.lab221.lab221asm6.entities;

import com.lab221.lab221asm6.models.UserModel;

public class User {

    private int id;
    private String username;
    private String fullname;
    private String password;
    private int attempt;
    private boolean isLocked;
    private String question1;
    private String answer1;
    private String question2;
    private String answer2;
    private String question3;
    private String answer3;
    private boolean isFirstTimeLogin;

    public User() {}

    public User(int id,
                String username,
                String fullname,
                String password,
                int attempt,
                boolean isLocked,
                boolean isFirstTimeLogin) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.attempt = attempt;
        this.isLocked = isLocked;
        this.isFirstTimeLogin = isFirstTimeLogin;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public UserModel toModel() {
        UserModel userModel = new UserModel();
        userModel.setId(this.getId());
        userModel.setUsername(this.getUsername());
        userModel.setFullname(this.getFullname());
        userModel.setAttempt(this.getAttempt());
        userModel.setLocked(this.isLocked());
        userModel.setFirstTimeLogin(this.isFirstTimeLogin());
        return userModel;
    }
}
