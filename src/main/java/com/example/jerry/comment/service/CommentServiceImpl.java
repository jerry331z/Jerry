/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 10월 22일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

package com.example.jerry.comment.service;

import com.example.jerry.board.persistance.BoardDAO;
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
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("commentVo", commentVo);
            map.put("userVo", userVo);

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
}

