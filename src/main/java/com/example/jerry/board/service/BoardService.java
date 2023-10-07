/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:33
작성용도 : Bulletin board business layer (service layer) Interface Class File
*/

package com.example.jerry.board.service;

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.domain.CategoryVo;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BoardService {

    //  게시글 목록
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no);

    //  게시글 카테고리 목록
    public List<CategoryVo> getCategoryList();

    //  게시글 작성
    public void insertWrite(BoardVo param);

    //  게시물 상세보기
    public HashMap<String, Object> getBoard(int board_no);

}
