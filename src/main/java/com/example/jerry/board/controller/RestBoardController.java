/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) $originalComment.match("Copyright \(c\) (\d+)", 1 + "-1")2023년 9월 27일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-09-27
작성시간 : 오후 10:28
작성용도 : board web page application data Json conversion requests Handling
*/

package com.example.jerry.board.controller;

import com.example.jerry.board.domain.BoardLikeVo;
import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.service.BoardService;
import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/board/*")

public class RestBoardController {

    private final BoardService boardService;

    @Autowired
    public RestBoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    private static final HashMap<String, Object> data = new HashMap<String, Object>();

    @PostMapping(value = "writePostingProcess")
    @LogException
    public HashMap<String, Object> writePostingProcess(@Valid BoardVo param, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            data.put("result", "error");
            return data;
        }

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        param.setUser_no(sessionUser.getUser_no());
        boardService.insertWrite(param);

        data.put("result", "success");

        return data;
    }

    //  게시글 수정 프로시져
    @PostMapping(value = "modifyPostingProcess")
    @LogException
    public HashMap<String, Object> modifyPostingProcess(@Valid BoardVo param, BindingResult result) {

        if (result.hasErrors()) {
            data.put("result", "error");
        }

        boardService.modifyBoard(param);
        data.put("result", "success");

        return data;
    }

    //  게시글 삭제 프로시저
    @PostMapping(value = "deletePosting")
    @LogException
    public HashMap<String, Object> deletePosting(int boardNo) {

        data.put("result", "success");
        boardService.deletePosting(boardNo);

        return data;
    }

    //  게시글 좋아요 및 좋아요 취소
    @PostMapping(value = "doLike")
    @LogException
    public HashMap<String, Object> doLike(BoardLikeVo param, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        int myLikeCount = boardService.getMyLikeCount(param);

        data.put("result", "success");

        if (myLikeCount > 0) {
            data.put("status", "like");
        } else {
            data.put("status", "unlike");
        }

        int userNo = sessionUser.getUser_no();
        param.setUser_no(userNo);

        boardService.doLike(param);

        data.put("result", "success");

        return data;
    }

    //  게시글 좋아요 상태
    @PostMapping("getMyLikeStatus")
    @LogException
    public HashMap<String, Object> getMyLikeStatus(BoardLikeVo param, HttpSession session) {
        HashMap<String, Object> data = new HashMap<>();

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            data.put("result", "error");
            data.put("reason", "로그인이 필요합니다.");
            return data;
        }

        param.setUser_no(sessionUser.getUser_no());

        int myLikeCount = boardService.getMyLikeCount(param);

        data.put("result", "success");

        if (myLikeCount > 0) {
            data.put("status", "like");
        } else {
            data.put("status", "unlike");
        }

        return data;
    }

    //  게시글 좋아요 총 갯수
    @PostMapping(value = "getTotalLikeCount")
    @LogException
    public HashMap<String, Object> getTotalLikeCount(int board_no){
        HashMap<String, Object> data = new HashMap<String, Object>();

        int totalLikeCount = boardService.getTotalLikeCount(board_no);
        data.put("totalLikeCount", totalLikeCount);
        return data;
    }
}
