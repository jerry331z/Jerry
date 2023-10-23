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

import com.example.jerry.board.domain.*;
import com.example.jerry.board.persistance.BoardDAO;
import com.example.jerry.comment.persistance.CommentDAO;
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

    private final CommentDAO commentDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO, UserDAO userDAO, CommentDAO commentDAO) {
        this.boardDAO = boardDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    @LogException
    //  게시글 리스트
    public ArrayList<HashMap<String, Object>> getBoardList(int category_no, int search_category_no, String keyword, int pageNum) {

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        List<BoardVo> boardVoList;

        if (category_no != 0) {
            boardVoList = boardDAO.getBoardByCategoryList(category_no, search_category_no, keyword, pageNum);
        } else {
            boardVoList = boardDAO.getBoardList(search_category_no, keyword, pageNum);
        }
        for (BoardVo boardVo : boardVoList) {
            int userNo = boardVo.getUser_no();
            UserVo userVo = userDAO.getUserByNo(userNo);
            CategoryVo categoryVo = boardDAO.getCategoryByNo(boardVo.getCategory_no());
            int totalLikeCount = boardDAO.getTotalLikeCount(boardVo.getBoard_no());
            int totalCommentCount = commentDAO.getTotalCommentCount(boardVo.getBoard_no());

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("boardVo", boardVo);
            map.put("userVo", userVo);
            map.put("categoryVo", categoryVo);
            map.put("totalLikeCount", totalLikeCount);
            map.put("totalCommentCount", totalCommentCount);

            data.add(map);
        }
        return data;
    }

    //  게시글 카테고리 목록
    @Override
    @LogException
    public List<CategoryVo> getCategoryList() {
        return boardDAO.getCategoryList();
    }

    //  게시글 작성
    @Override
    @LogException
    public void insertWrite(BoardVo param) {
        boardDAO.insertWrite(param);
    }

    //  게시글 상세보기
    @Override
    @LogException
    public HashMap<String, Object> getBoard(int board_no) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        BoardVo boardVo = boardDAO.getBoardByNo(board_no);
        UserVo userVo = userDAO.getUserByNo(boardVo.getUser_no());
        int totalLikeCount = boardDAO.getTotalLikeCount(boardVo.getBoard_no());

        map.put("userVo", userVo);
        map.put("boardVo", boardVo);
        map.put("totalLikeCount", totalLikeCount);

        return map;
    }

    //  게시글 조회수 증가 중복 방지
    @Override
    @LogException
    public void insertViewPage(ViewPageVo viewPageVo) {
        boardDAO.insertViewPage(viewPageVo);
    }

    //  게시글 조회수 증가 중복 방지 조회
    @Override
    @LogException
    public List<ViewPageVo> getViewPageList(int boardNo) {
        return boardDAO.getViewPageList(boardNo);
    }

    //  게시글 조회한 아이피 조회 쿼리
    @Override
    @LogException
    public boolean isSelectByLockupIp(String lockup_ip) {
        return boardDAO.selectByLockupIp(lockup_ip) > 0;
    }

    //  게시글 중복 증가 방지 게시글 조회
    @Override
    @LogException
    public boolean isSelectByViewByBoardNo(int boardNo) {
        return boardDAO.selectByViewByBoardNo(boardNo) > 0;
    }

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    @Override
    @LogException
    public boolean isSelectByViewPage(ViewPageVo viewPageVo) {
        return boardDAO.selectByViewPage(viewPageVo) > 0;
    }

    //  게시글 조회수 증가 쿼리
    @Override
    @LogException
    public void increaseReadCount(int boardNo) {
        boardDAO.increaseReadCount(boardNo);
    }

    @Override
    @LogException
    public void updateViewPage(ViewPageVo param) {
        boardDAO.updateViewPage(param);
    }

    //  게시글 조회수 중복 증가 삭제
    @Override
    @LogException
    public void deleteViewPage(int boardNo) {
        boardDAO.deleteViewPage(boardNo);
    }

    //  게시글 수정
    @Override
    @LogException
    public void modifyBoard(BoardVo param) {
        boardDAO.modifyBoard(param);
    }

    //  게시글 삭제
    @Override
    @LogException
    public void deletePosting(int boardNo) {

        //  게시글 조회수 중복 체크 삭제
        boardDAO.deleteViewPage(boardNo);

        //  게시글 삭제
        boardDAO.deletePosting(boardNo);
    }

    //  게시글 좋아요
    @Override
    @LogException
    public void doLike(BoardLikeVo likeVo) {
        if (getMyLikeCount(likeVo) < 1) {
            boardDAO.doLike(likeVo);
        } else {
            boardDAO.deleteLike(likeVo);
        }
    }

    //  게시글 좋아요 상태
    @Override
    @LogException
    public int getMyLikeCount(BoardLikeVo likeVo) {
        return boardDAO.getMyLikeCount(likeVo);
    }

    //  게시글 좋아요 총 갯수
    @Override
    @LogException
    public int getTotalLikeCount(int board_no) {
        return boardDAO.getTotalLikeCount(board_no);
    }

    //  게시글 검색 카테고리 목록
    @Override
    @LogException
    public List<SearchCategoryVo> getBoardSearchCategoryList() {
        return boardDAO.getBoardSearchCategoryList();
    }

    //  게시글 총 갯수
    @Override
    @LogException
    public int getBoardCount(int category_no, int search_category_no, String keyword) {
        return boardDAO.getBoardCount(category_no, search_category_no, keyword);
    }
}
