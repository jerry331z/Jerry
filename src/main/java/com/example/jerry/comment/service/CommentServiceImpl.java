/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 22일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.comment.service;

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.persistance.BoardDAO;
import com.example.jerry.comment.domain.CommentLikeVo;
import com.example.jerry.comment.domain.CommentVo;
import com.example.jerry.comment.persistance.CommentDAO;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.persistance.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BoardDAO boardDAO;

    //  댓글 목록
    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getCommentList(int board_no) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<CommentVo> commentVoList = commentDAO.getCommentList(board_no);

        for (CommentVo commentVo : commentVoList) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");

            UserVo userVo = userDAO.getUserByNo(commentVo.getUser_no());
            int totalCommentLikeCount = commentDAO.getTotalCommentLikeCount(commentVo.getComment_no());

            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("commentVo", commentVo);
            map.put("userVo", userVo);
            map.put("totalCommentLikeCount", totalCommentLikeCount);

            dataList.add(map);
        }
        return dataList;
    }

    //  댓글 총 갯수
    @Override
    @LogException
    public int getTotalCommentCount(int board_no) {
        return commentDAO.getTotalCommentCount(board_no);
    }

    //  댓글 작성
    @Override
    @LogException
    public void writeComment(CommentVo param) {
        commentDAO.writeComment(param);
    }

    //  댓글 수정
    @Override
    @LogException
    public void updateComment(CommentVo commentVo) {
        commentDAO.updateComment(commentVo);
    }

    //  댓글 삭제
    public void deleteComment(int comment_no) {
        commentDAO.deleteComment(comment_no);
    }

    //  댓글 좋아요
    @Override
    @LogException
    public void doCommentLike(CommentLikeVo param) {
        if (getMyCommentLikeCount(param) < 1) {
            commentDAO.doCommentLike(param);
        } else {
            commentDAO.deleteCommentLike(param);
        }
    }

    //  댓글 좋아요 상태
    @Override
    @LogException
    public int getMyCommentLikeCount(CommentLikeVo param) {
        return commentDAO.getMyCommentLikeCount(param);
    }

    //  댓글 좋아요 총 갯수
    @Override
    @LogException
    public int getTotalCommentLikeCount(int comment_no) {
        return commentDAO.getTotalCommentLikeCount(comment_no);
    }

    @Override
    @LogException
    public ArrayList<HashMap<String, Object>> getMyCommentList(int user_no) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<CommentVo> commentVoList = commentDAO.getMyCommentList(user_no);

        for (CommentVo commentVo : commentVoList) {
            BoardVo boardVo = boardDAO.getBoardByNo(commentVo.getBoard_no());
            UserVo userVo = userDAO.getUserByNo(user_no);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("commentVo", commentVo);

            dataList.add(map);
        }
        return dataList;
    }
}

