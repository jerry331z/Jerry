/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 17일 JerryDEV All rights reserved.       │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.bookmark.service;
import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.persistance.BoardDAO;
import com.example.jerry.bookmark.domain.BookMarkVo;
import com.example.jerry.bookmark.persistance.BookMarkDAO;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.persistance.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    BookMarkDAO bookMarkDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BoardDAO boardDAO;

    //  게시글 북마크 등록
    @Override
    @LogException
    public void doBookMark(BookMarkVo param) {
        int bookMarkStatus = getMyBookMarkStatus(param);
        if (bookMarkStatus > 0) {
            bookMarkDAO.deleteBookMark(param.getBoard_no(), param.getUser_no());
        } else {
            bookMarkDAO.doBookMark(param);
        }
    }

    //  게시글 북마크 상태 조회
    @Override
    @LogException
    public int getMyBookMarkStatus(BookMarkVo param) {
        return bookMarkDAO.getMyBookMarkStatus(param);
    }

    //  내가 북마크한 글 리스트 불러오기
    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getBookMarkList(int user_no) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<BookMarkVo> bookMarkVoList = bookMarkDAO.getBookMarkList(user_no);
        for (BookMarkVo bookMarkVo : bookMarkVoList) {
            int boardNo = bookMarkVo.getBoard_no();
            BoardVo boardVo = boardDAO.getBoardByNo(boardNo);
            UserVo userVo = userDAO.getUserByNo(bookMarkVo.getUser_no());
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("bookMarkVo", bookMarkVo);

            dataList.add(map);
        }
        return dataList;
    }
}