/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 17일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.bookmark.persistance;

import com.example.jerry.bookmark.domain.BookMarkVo;
import com.example.jerry.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BookMarkDAOImpl implements BookMarkDAO {

    private static final String NAMESPACE = "mappers.bookmark.BookMarkMapper";

    @Autowired
    SqlSession sqlSession;

    //  게시글 북마크 등록
    @Override
    @LogException
    public void doBookMark(BookMarkVo param) {
        sqlSession.insert(NAMESPACE + ".doBookMark", param);
    }

    //  게시글 북마크 상태
    @Override
    @LogException
    public int getMyBookMarkStatus(BookMarkVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getMyBookMarkStatus", param);
    }

    //  게시글 북마크 취소
    @Override
    @LogException
    public void deleteBookMark(int board_no, int user_no) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("boardNo", board_no);
        data.put("userNo", user_no);
        sqlSession.delete(NAMESPACE + ".deleteBookMark", data);
    }

    //  내가 북마크한 게시글
    @Override
    @LogException
    public List<BookMarkVo> getBookMarkList(int user_no) {
        return sqlSession.selectList(NAMESPACE + ".getBookMarkList", user_no);
    }
}