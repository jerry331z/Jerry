/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 24일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-24
작성시간 : 오후 6:30
작성용도 : Find ID Password related JavaScript files
*/


window.addEventListener("DOMContentLoaded", function () {
    $("#findId").click(function () {
        $("#userIdFindModel").modal('show');
    });

    $("#findPassword").click(function () {
        $("#userPwFindModel").modal('show');
    });

    /* 아이디 찾기 */
    $("#findIdButton").click(function () {
        $.ajax({
            type: "post",
            url: "../user/getUserIdByNickNameAndEmail",
            data: {
                user_nickname: $("#user_nickname").val(),
                user_email: $("#user_email").val()
            },
            dataType: "json",
            // contentType : "application/x-www-form-urlencoded", // post
            success: function (data) {
                if (data.result == 'fail') {
                    $("#answerLine").css({
                        "color": "red",
                        "text-align": "center",
                        "text-size": "10px"
                    });
                    $("#answerLine").text("일치하는 아이디가 없습니다. 다시 확인해주세요.");
                } else {
                    $("#answerLine").css({
                        "color": "green",
                        "text-align": "center",
                        "text-size": "10px"
                    });
                    $("#answerLine").text('찾으시는 ID는 "' + data.userInfo.USER_ID + '" 입니다.');
                }
            }
        });
    });
});