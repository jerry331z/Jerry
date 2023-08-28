/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:39
작성용도 : Bulletin board business layer (service layer) Implements Class File
*/

package com.example.jerry.board.service;

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.persistance.BoardDAO;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.persistance.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    private final UserDAO userDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO, UserDAO userDAO) {
        this.boardDAO = boardDAO;
        this.userDAO = userDAO;
    }

    @Override
    @LogException
    //  게시글 리스트
    public ArrayList<HashMap<String, Object>> getBoardList() {

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        List<BoardVo> boardVoList = boardDAO.getBoardList();

        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userDAO.getUserByNo(userNo);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);

            data.add(map);
        }
        return data;
    }
}
