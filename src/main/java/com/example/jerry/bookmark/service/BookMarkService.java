/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 17일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.bookmark.service;
import com.example.jerry.bookmark.domain.BookMarkVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookMarkService {

    //  게시글 북마크 등록
    public void doBookMark(BookMarkVo param);

    //  게시글 북마크 상태
    public int getMyBookMarkStatus(BookMarkVo param);

    //  내가 북마크한 게시글
    public ArrayList<HashMap<String, Object>> getBookMarkList(int user_no);
}
