/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 22일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.comment.persistance;

import com.example.jerry.comment.domain.CommentVo;
import com.example.jerry.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private final String NAMESPACE = "mappers.comment.CommentSQLMapper";

    @Autowired
    SqlSession sqlSession;

    //  댓글 목록
    @Override
    @LogException
    public List<CommentVo> getCommentList(int board_no) {
        return sqlSession.selectList(NAMESPACE + ".getCommentList", board_no);
    }

    //  댓글 총 갯수
    @Override
    @LogException
    public int getTotalCommentCount(int board_no) {
        return sqlSession.selectOne(NAMESPACE + ".getTotalCommentCount", board_no);
    }

    //  댓글 작성
    @Override
    @LogException
    public void writeComment(CommentVo commentVo) {
        sqlSession.insert(NAMESPACE + ".writeComment", commentVo);
    }

    //  댓글 수정
    @Override
    @LogException
    public void updateComment(CommentVo commentVo) {
        sqlSession.update(NAMESPACE + ".updateComment", commentVo);
    }

    //  댓글 삭제
    @Override
    @LogException
    public void deleteComment(int comment_no) {
        sqlSession.delete(NAMESPACE + ".deleteComment", comment_no);
    }

    //  댓글 전체 삭제
    @Override
    @LogException
    public void deleteAllComment(int boardNo) {
        sqlSession.delete(NAMESPACE + ".deleteAllComment", boardNo);
    }
}