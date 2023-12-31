/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 6월 29일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-06-29
작성시간 : PM 2:39
작성용도 : main web page client request processing controller file
*/

package com.example.jerry.main.controller;

import com.example.jerry.commons.annotation.LogException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @LogException
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "/main/main", method = RequestMethod.GET)
    @LogException
    public String homePage() {

        return "index";
    }
}
