/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:11
작성용도 : Bulletin board Repository layer (Data Access Object) Interface Class File
*/

package com.example.jerry.board.persistance;

import com.example.jerry.board.domain.*;

import java.util.List;
import java.util.Map;

public interface BoardDAO {

    //  게시글 목록
    //  게시글 목록
    public List<BoardVo> getBoardList(int search_category_no, String keyword, int pageNum);

    //  게시글 목록 (카테고리별 정렬)
    public List<BoardVo> getBoardByCategoryList(int category_no, int search_category_no, String keyword, int pageNum);

    //  게시글 카테고리 정보
    public CategoryVo getCategoryByNo(int category_no);

    //  게시글 카테고리 목록
    public List<CategoryVo> getCategoryList();

    //  게시글 쓰기
    public void insertWrite(BoardVo param);

    //  글씨기 상세보기
    public BoardVo getBoardByNo(int board_no);


    //  게시글 조회수 증가 중복 방지
    public void insertViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 중복 방지 조회
    public List<ViewPageVo> getViewPageList(int boardNo);

    //  게시글 조회한 아이피 조회 쿼리
    public int selectByLockupIp(String lockup_ip);

    //  게시글 중복 증가 방지 게시글 조회
    public int selectByViewByBoardNo(int boardNo);

    //  게시글 조회 중복 증가 방지 조회 (게시글번호, 아이피로 조회)
    public int selectByViewPage(ViewPageVo viewPageVo);

    //  게시글 조회수 증가 쿼리
    public void increaseReadCount(int boardNo);

    public void updateViewPage(ViewPageVo param);

    //  게시글 조회수 중복 증가 삭제
    public void deleteViewPage(int boardNo);

    //  게시글 수정
    public void modifyBoard(BoardVo param);

    //  게시글 삭제
    public void deletePosting(int boardNo);

    //  게시글 좋아요
    public void doLike(BoardLikeVo likeVo);

    //  게시글 좋아요 상태
    public int getMyLikeCount(BoardLikeVo likeVo);

    //  게시글 좋아요 취소
    public void deleteLike(BoardLikeVo likeVo);

    //  게시글 좋아요 총 갯수
    public int getTotalLikeCount(int board_no);

    //  게시글 검색 카테고리 목록
    public List<SearchCategoryVo> getBoardSearchCategoryList();

    //  게시글 총 갯수
    public int getBoardCount(int category_no, int search_category_no, String keyword);

    //  내가 작성한 글목록
    public List<BoardVo> getMyPostList(int user_no);

    public int createBoardPk();

    public void insertFile(FileVo file);

    public List<Map<String, Object>> selectFileList(int bno);
}
