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

}
