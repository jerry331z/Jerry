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

import com.example.jerry.board.domain.*;
import com.example.jerry.commons.annotation.LogException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private static final String NAMESPACE = "mappers.board.BoardSQLMapper";

    private final SqlSession sqlSession;

    @Autowired
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    //  게시글 목록
    @Override
    @LogException
    public List<BoardVo> getBoardList(int search_category_no, String keyword, int pageNum) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("search_category_no", search_category_no);
        param.put("keyword", keyword);
        param.put("pageNum", pageNum);

        return sqlSession.selectList(NAMESPACE + ".getBoardList", param);
    }


    //  게시글 목록 (카테고리별 정렬)
    @Override
    @LogException
    public List<BoardVo> getBoardByCategoryList(int category_no, int search_category_no, String keyword, int pageNum) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("category_no", category_no);
        param.put("search_category_no", search_category_no);
        param.put("keyword", keyword);
        param.put("pageNum", pageNum);

        return sqlSession.selectList(NAMESPACE + ".getBoardByCategoryList", param);
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

    //  게시글 상세보기
    @Override
    @LogException
    public BoardVo getBoardByNo(int board_no) {
        return sqlSession.selectOne(NAMESPACE + ".getBoardByNo", board_no);
    }

    //  게시글 조회수 증가 중복 방지 조회
    @Override
    @LogException
    public List<ViewPageVo> getViewPageList(int boardNo) {
        return sqlSession.selectList(NAMESPACE + ".getViewPageList", boardNo);
    }

    //  게시글 조회수 증가 중복 방지
    @Override
    @LogException
    public void insertViewPage(ViewPageVo viewPageVo) {
        sqlSession.insert(NAMESPACE + ".insertViewPage", viewPageVo);
    }

    //  게시글 조회한 아이피 조회 쿼리
    @Override
    @LogException
    public int selectByLockupIp(String lockup_ip) {
        return sqlSession.selectOne(NAMESPACE + ".selectByLockupIp", lockup_ip);
    }

    //  게시글 중복 증가 방지 게시글 조회
    @Override
    @LogException
    public int selectByViewByBoardNo(int boardNo) {
        return sqlSession.selectOne(NAMESPACE + ".selectByViewByBoardNo", boardNo);
    }

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    @Override
    @LogException
    public int selectByViewPage(ViewPageVo viewPageVo) {
        return sqlSession.selectOne(NAMESPACE + ".selectByViewPage", viewPageVo);
    }

    //  게시글 조회수 증가 쿼리
    @Override
    @LogException
    public void increaseReadCount(int boardNo) {
        sqlSession.update(NAMESPACE + ".increaseReadCount", boardNo);
    }

    //  게시글 조회수 증가 쿼리
    @Override
    @LogException
    public void updateViewPage(ViewPageVo param) {
        sqlSession.update(NAMESPACE + ".updateViewPage", param);
    }

    //  게시글 조회수 중복 증가 삭제
    @Override
    @LogException
    public void deleteViewPage(int boardNo) {
        sqlSession.delete(NAMESPACE + ".deleteViewPage", boardNo);
    }

    //  게시글 수정
    @Override
    @LogException
    public void modifyBoard(BoardVo param) {
        sqlSession.update(NAMESPACE + ".modifyBoard", param);
    }

    //  게시글 삭제
    @Override
    @LogException
    public void deletePosting(int boardNo) {
        sqlSession.delete(NAMESPACE + ".deletePosting", boardNo);
    }

    //  게시글 좋아요
    @Override
    @LogException
    public void doLike(BoardLikeVo likeVo) {
        sqlSession.insert(NAMESPACE + ".doLike", likeVo);
    }

    //  게시글 좋아요 상태
    @Override
    @LogException
    public int getMyLikeCount(BoardLikeVo likeVo) {
        return sqlSession.selectOne(NAMESPACE + ".getMyLikeCount", likeVo);
    }

    //  게시글 좋아요 취소
    @Override
    @LogException
    public void deleteLike(BoardLikeVo likeVo) {
        sqlSession.delete(NAMESPACE + ".deleteLike", likeVo);
    }

    //  게시글 좋아요 총 갯수
    @Override
    @LogException
    public int getTotalLikeCount(int board_no) {
        return sqlSession.selectOne(NAMESPACE + ".getTotalLikeCount", board_no);
    }

    //  게시글 검색 카테고리 목록
    @Override
    @LogException
    public List<SearchCategoryVo> getBoardSearchCategoryList() {
        return sqlSession.selectList(NAMESPACE + ".getBoardSearchCategoryList");
    }

    //  게시글 총 갯수
    @Override
    @LogException
    public int getBoardCount(int category_no, int search_category_no, String keyword) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("category_no", category_no);
        param.put("search_category_no", search_category_no);
        param.put("keyword", keyword);

        return sqlSession.selectOne(NAMESPACE + ".getBoardCount", param);
    }

    //  내가 작성한 게시글
    @Override
    @LogException
    public List<BoardVo> getMyPostList(int user_no) {
        return sqlSession.selectList(NAMESPACE + ".getMyPostList", user_no);
    }
}
