/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 9월 10일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-09-10
작성시간 : 오전 5:18
작성용도 : Catogry Value Object
*/

package com.example.jerry.board.domain;

public class CategoryVo {
    private int category_no; // 카테고리 번호
    private String category_name; // 카테고리 이름

    public CategoryVo() {
        super();
    }

    public CategoryVo(int category_no, String category_name) {
        this.category_no = category_no;
        this.category_name = category_name;
    }

    public int getCategory_no() {
        return category_no;
    }

    public void setCategory_no(int category_no) {
        this.category_no = category_no;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "category_no=" + category_no +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}