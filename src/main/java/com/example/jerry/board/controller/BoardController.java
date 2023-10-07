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

import com.example.jerry.board.domain.BoardVo;
import com.example.jerry.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String lustPage(@RequestParam(value = "category_no", defaultValue = "0") int category_no, Model model) {

        ArrayList<HashMap<String, Object>> dataList = boardService.getBoardList(category_no);
        model.addAttribute("category_no", category_no);
        model.addAttribute("dataList", dataList);

        return "board/list";
    }

    @GetMapping(value = "/write")
    public String writePosting(@RequestParam(value = "category_no", defaultValue = "0") int category_no, @ModelAttribute("boardVo") BoardVo param, Model model) {
        model.addAttribute("category_no", category_no);
        model.addAttribute("list", boardService.getCategoryList());
        return "board/write";
    }

    @PostMapping(value = "read")
    public String detailsPosting(@RequestParam(value = "board_no", defaultValue = "0") int board_no, Model model) {


        model.addAttribute("data", boardService.getBoard(board_no));
        return "board/read";

    }

}
