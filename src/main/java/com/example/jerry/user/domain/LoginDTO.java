/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 17일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-17
작성시간 : 오전 12:42
작성용도 : Web Login DTO (Data Transfer Object) File
*/

package com.example.jerry.user.domain;

public class LoginDTO {
    private String user_id; // 아이디
    private String user_pw; // 비밀번호
    private boolean useCookie; // 로그인 유지 여부

    private boolean saveCookie; // 아이디 저장 여부

    public LoginDTO() {
        super();
    }

    public LoginDTO(String user_id, String user_pw, boolean useCookie, boolean saveCookie) {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.useCookie = useCookie;
        this.saveCookie = saveCookie;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    public boolean isSaveCookie() {
        return saveCookie;
    }

    public void setSaveCookie(boolean saveCookie) {
        this.saveCookie = saveCookie;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", useCookie=" + useCookie + '\'' +
                ", saveCookie= " + saveCookie +
                '}';
    }
}
