/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 9월 26일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-09-26
작성시간 : 오후 7:45
작성용도 : Related Bulletin Javascript Files
*/

function writing(category_no) {
    const form = $("form[name='writeForm']");
    $("#category").attr("value", category_no);
    form.attr("action", "../board/write");
    form.attr("method", "get");
    form.submit();
}

const formObj = $("form[role='form']");

function postingList(category_no) {
    const form = $("form[id='list']");
    $("#category_no").attr("value", category_no);
    form.attr("action", "../board/list");
    form.attr("method", "get");
    form.submit();
}

function goPage(board_no) {
    const form = $("form[name='readForm']");
    $("#boardNo").attr("value", board_no);
    form.attr("action", "../board/read");
    form.attr("method", "post");
    form.submit();
}

window.addEventListener("DOMContentLoaded", function () {

    /* 게시글 작성 */
    $("#writePostingButton").click(function () {
        $.ajax({
            type: "post",
            url: "../board/writePostingProcess",
            data: {
                category_no: $("#categoryList").val(),
                board_title: $("#board_title").val(),
                board_content: $("#board_content").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "error") {
                    location.reload();
                } else {
                    alert("게시글 작성에 성공 하였습니다.");
                    location.href = "../board/list";
                }
            }
        });
    });


    $(".listBtn").click(function () {
        formObj.attr("action", "../board/list");
        formObj.attr("method", "get");
        formObj.submit();
    });
});