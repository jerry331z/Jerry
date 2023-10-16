/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 17일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.bookmark.persistance;

import com.example.jerry.bookmark.domain.BookMarkVo;

import java.util.List;

public interface BookMarkDAO {

    //  게시글 북마크 등록
    public void doBookMark(BookMarkVo param);

    //  게시글 북마크 상태
    public int getMyBookMarkStatus(BookMarkVo param);

    //  게시글 북마크 취소
    public void deleteBookMark(int board_no, int user_no);

    //  내가 북마크한 게시글
    public List<BookMarkVo> getBookMarkList(int user_no);
}
