/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:29
작성용도 : Board Data Access Object Request Handling
*/

package com.example.jerry.board.persistance;

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.domain.CategoryVo;
import com.example.jerry.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO  {

    private static final String NAMESPACE = "mappers.board.BoardSQLMapper";

    private  final SqlSession sqlSession;

    @Autowired
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    //  게시글 목록
    @Override
    @LogException
    public List<BoardVo> getBoardList() {

        return sqlSession.selectList(NAMESPACE + ".getBoardList");
    }

    //  게시글 목록 (카테고리별 정렬)
    @Override
    @LogException
    public List<BoardVo> getBoardByCategoryList(int category_no) {
        return sqlSession.selectList(NAMESPACE + ".getBoardByCategoryList", category_no);
    }

    //  카테고리리 정보
    @Override
    @LogException
    public CategoryVo getCategoryByNo(int category_no) {
        return sqlSession.selectOne(NAMESPACE + ".getCategoryByNo", category_no);
    }

    //  카테고리 목록
    @Override
    @LogException
    public List<CategoryVo> getCategoryList() {
        return sqlSession.selectList(NAMESPACE + ".getCategoryList");
    }

    @Override
    @LogException
    //  게시글 작성
    public void insertWrite(BoardVo param) {
        sqlSession.insert(NAMESPACE + ".insertWrite", param);
    }
}
