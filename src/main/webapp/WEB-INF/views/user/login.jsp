<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 7월 3일 JerryDEV All rights reserved.         │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--

작성자 : Min Woo Song
작성일 : 2023-07-03
작성시간 : PM 5:28
작성용도 : Login Web View Page Jsp File
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>
<style>
    .find-btn {
        text-align: left;
    }

    .find-btn1 {
        display: inline-block;
    }
</style>
<body class="hold-transition skin-blue login-page" oncontextmenu="return false" oncopy="return false"
      oncut="return false" onpaste="return false" onselect="return false">
<div class="wrapper">

    <%--main_header.jsp--%>
    <%-- Main Header --%>
    <%@ include file="../include/main_header.jsp" %>

    <%--left_column.jsp--%>
    <%-- Left side column. contains the logo and sidebar --%>
    <%@ include file="../include/left_column.jsp" %>

    <%-- Content Wrapper. Contains page content --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <div class="login-box-body">

            <div class="form-group has-feedback">
                <input type="text" id="inputId" name="uid" class="form-control" placeholder="아아디">
                <span class="glyphicon glyphicon-exclamation-sign form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="inputPw" name="upw" class="form-control" placeholder="비밀번호">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <!-- /.col -->
            <div class="row">
                <div class="find-btn">
                    <input type="checkbox" class="find-btn1" id="saveIdBox"> 아이디저장
                    <input type="checkbox" class="find-btn1" id="userCookie"> 로그인유지
                </div>
            </div>

            <div class="row mt-1">
                <div class="col-xs-8">
                    <button type="button" id="loginButton" class="btn btn-primary btn-block btn-flat">
                        <i class="fa fa-sign-in"></i> 로그인
                    </button>
                </div>
            </div>

            <div class="row mt-2">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <button type="button" id="findId" class="btn btn-primary btn-block btn-flat">ID 찾기</button>
                    </div>
                </div>
            </div>

            <div class="row mt-1">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <button type="button" id="findPassword" class="btn btn-primary btn-block btn-flat">PW 찾기
                        </button>
                    </div>
                </div>
            </div>

            <!-- /.col -->
        </div>
        <%--------------------------
          | Your Page Content Here |
          --------------------------%>
    </div>
    <%-- /.content --%>
    <%-- /.content-wrapper --%>

    <%--main_footer.jsp--%>
    <%-- Main Footer --%>
    <%@ include file="../include/main_footer.jsp" %>

</div>
<%-- ./wrapper --%>

<%--plugin_js.jsp--%>
<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>

<div class="modal fade" id="userIdFindModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">아이디 찾기</h4>
            </div>
            <div class="modal-body" align="center">
                <form action="./user/findIdProcess">
                    <div class="row">
                        <div class="col">

                            <div class="row mt-2">
                                <div class="col-xs-11">
                                    <input type="text" class="form-control" name="user_nickname" id="user_nickname"
                                           placeholder="닉네임을 입력해주세요.">
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col-xs-11">
                                    <input type="text" class="form-control" name="user_email" id="user_email"
                                           placeholder="이메일을 입력해주세요.">
                                </div>
                            </div>

                            <div class="row mt-2">
                                <div class="col-xs-11" id="answerLine"></div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-right" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-info pull-right" id="findIdButton">검색</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userPwFindModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">비밀번호 찾기</h4>
            </div>
            <div class="modal-body" align="center">
                <form id="findPw" action="../user/findPwProcess">
                    <div class="row">
                        <div class="row mt-1">
                            <div class="col-xs-11">
                                <label for="findIdInput">아이디</label>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-xs-11">
                                <input type="text" class="form-control" name="user_id" placeholder="아이디를 입력해주세요"
                                       id="findIdInput">
                                <button type="button" class="btn btn-info pull-right btn-xs" id="findQuestionButton">
                                    힌트조회
                                </button>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-xs-11">
                                <input type="text" class="form-control" name="user_findAnswer" placeholder="힌트답을 입력해주세요"
                                       id="findAnswerInput">
                                <button type="button" class="btn btn-info pull-right btn-xs" id="findButton">답변확인
                                </button>
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-xs-11" id="question_content"></div>
                            <div class="col-xs-11" id="answerLine2"></div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-xs-11">
                                <input type="password" class="form-control" id="findPwInput" name="user_pw"
                                       placeholder="사용하실 비밀번호를 입력해주세요" disabled="disabled">
                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col-xs-11" id="answerLine3"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-xs-2">
                        <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button>
                    </div>
                    <div class="col-xs-2">
                        <button type="button" class="btn btn-info pull-right" id="updatePW" disabled="disabled">수정
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>