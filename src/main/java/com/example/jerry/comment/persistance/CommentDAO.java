/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 22일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.comment.persistance;

import com.example.jerry.comment.domain.CommentLikeVo;
import com.example.jerry.comment.domain.CommentVo;

import java.util.List;

public interface CommentDAO {

    //  댓글 목록
    public List<CommentVo> getCommentList(int board_no);

    //  댓글 총 갯수
    public int getTotalCommentCount(int board_no);

    //  댓글 작성
    public void writeComment(CommentVo commentVo);

    //  댓글 수정
    public void updateComment(CommentVo commentVo);

    //  내가 작성한 댓글
    public List<CommentVo> getMyCommentList(int user_no);

    //  댓글 삭제
    public void deleteComment(int comment_no);

    //  댓글 전체 삭제
    public void deleteAllComment(int boardNo);

    //  댓글 좋아요
    public void doCommentLike(CommentLikeVo like);

    //  댓글 좋아요 상태
    public int getMyCommentLikeCount(CommentLikeVo like);

    //  댓글 좋아요 삭제
    public void deleteCommentLike(CommentLikeVo like);

    //  댓글 좋아요 총 갯수
    public int getTotalCommentLikeCount(int comment_no);

}
