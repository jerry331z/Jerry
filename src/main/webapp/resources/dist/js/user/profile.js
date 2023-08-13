/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 8일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-08
작성시간 : 오전 4:15
작성용도 : Javascript file associated with personal profile page
*/

window.addEventListener("DOMContentLoaded", function () {

    var code = "";
    $("#CertifiedEmailButton").click(function () {
        $.ajax({
            type: "post",
            url: "./checkUserInfo",
            data: {
                user_email: $("#userEmail").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == "error") {
                    alert("서버와 통신중 에러가 발생했습니다.");
                    $("#alertCertified").css({
                        "color": "rad",
                        "font-size": "10px"
                    });
                    $("#alertCertified").text("!  서버 통신중 에러가 발생 하였습니다.");
                } else {
                    alert("인증번호 발송이 완료되었습니다. 입력한 이메일에서 인증번호 확인을 해주세요.");
                    $("#alertCertified").text("! 인증번호를 입력해주세요.")
                    $("#alertCertified").css({
                        "color": "red",
                        "font-size": "10px"
                    });
                    code = data.code;
                    $("#userCertified").attr("disabled", false);
                    $("#CertifiedEmailButton").attr("disabled", true);
                }
            }
        });
    });

    $("#userCertified").keyup(function() {
        if ($("#userCertified").val().length != 6) {
            $("#alertCertified").text("! 인증번호가 일치하지 않습니다. 다시 확인해주시기 바랍니다.")
            $("#alertCertified").css({
                "color": "red",
                "font-size": "10px"
            });
        } else if ($("#userCertified").val() == code) {
            $("#alertCertified").text("✔ 메일인증이 완료되었습니다.")
            $("#alertCertified").css({
                "color": "green",
                "font-size": "10px"
            });
            $("#userCertified").attr("disabled", true);
            $("#updateInfo").attr("disabled", false);
        }
    });

    $("#updateInfo").click(function() {
        $.ajax({
            type: "post",
            url: "./updateInfoUser",
            data: {
                user_id: $("#inputUserId").val(),
                user_nickname: $("#userNickName").val(),
                user_phone: $("#userPhone").val(),
                user_email: $("#userEmail").val(),
                question_no: $("#userQuestion").val(),
                user_findAnswer: $("#userFindAnswer").val()
            },
            dataType: "json",
            success: function (data) {
                if (data.result == 'fail') {
                    alert("로그인을 먼저 진행한 이후에 변경을 진행해 주세요.");
                    location.href = "/main/main";
                    return;
                } else {
                    alert("변경에 성공 하였습니다 다시 로그인 해주세요.");
                    location.reload();
                }
            }
        });
    });
});