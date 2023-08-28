/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 16일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-16
작성시간 : 오전 3:46
작성용도 : Bulletin board web page client request processing controller file
*/

package com.example.jerry.board.controller;

import com.example.jerry.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String lustPage(Model model) {

        ArrayList<HashMap<String,Object>> dataList = boardService.getBoardList();
        model.addAttribute("dataList", dataList);

        return "board/list";
    }
}
