/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 22일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.comment.service;

import com.example.jerry.comment.domain.CommentVo;

import java.util.ArrayList;
import java.util.HashMap;

public interface CommentService {

    //  댓글 목록
    public ArrayList<HashMap<String, Object>> getCommentList(int board_no);

    //  댓글 총 갯수
    public int getTotalCommentCount(int board_no);

    //  댓글 작성
    public void writeComment(CommentVo param);

    //  댓글 수정
    public void updateComment(CommentVo commentVo);

    //  댓글 삭제
    public void deleteComment(int comment_no);
}
