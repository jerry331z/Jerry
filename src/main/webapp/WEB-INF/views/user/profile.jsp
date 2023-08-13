<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 7월 31일 JerryDEV All rights reserved.        │
  └───────────────────────────────────────────────────────────────────┘
--%>

<%--

작성자 : Min Woo Song
작성일 : 2023-07-31
작성시간 : 오전 2:31
작성용도 : 
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%--head.jsp--%>
<%@ include file="../include/head.jsp" %>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">
<div class="wrapper">

    <%--main_header.jsp--%>
    <%-- Main Header --%>
    <%@ include file="../include/main_header.jsp" %>

    <%--left_column.jsp--%>
    <%-- Left side column. contains the logo and sidebar --%>
    <%@ include file="../include/left_column.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">
            <div class="row">
                <div class="col-md-5">
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <img class="profile-user-img img-responsive img-circle"
                                 src="/resources/dist/img/profile/${sessionUser.user_image}" alt="User profile picture">

                            <h3 class="profile-username text-center">${sessionUser.user_nickname}</h3>
                            <div class="text-center">
                                <a class="btn btn-primary btn-xs" data-toggle="modal"
                                   data-target="#userPhotoModal">
                                    <i class="fa fa-photo"> 프로필사진 수정</i>
                                </a>
                            </div>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>아이디</b> <a class="pull-right">${sessionUser.user_id}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>이메일</b> <a class="pull-right">${sessionUser.user_email}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>가입일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_join_date}"
                                                        pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <b>최근 로그인 일자</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_last_connection_date}"
                                                        pattern="yyyy-MM-dd a hh:mm"/>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <b>생년월일</b>
                                    <a class="pull-right">
                                        <fmt:formatDate value="${sessionUser.user_birth}" pattern="yy년MM월dd일"/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="box-footer text-center">
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userInfoModal">
                                <i class="fa fa-info-circle"> 회원정보 수정</i>
                            </a>
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userPwModal">
                                <i class="fa fa-question-circle"> 비밀번호 수정</i>
                            </a>
                            <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#userOutModal">
                                <i class="fa fa-user-times"> 회원 탈퇴</i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="../include/main_footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>

<div class="modal fade" id="userInfoModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">회원정보 수정</h4>
            </div>

            <div class="model-body" align="center">
                <div class="row mt-2">
                    <div class="col-xs-2">
                        <label for="inputUserId">아이디</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-5"><input type="text" class="form-control" id="inputUserId"
                                                 value="${sessionUser.user_id}" readonly>
                    </div>
                    <div class="col my-auto" style="color:#6667ab; font-size:10px;">아이디는 한 번 정하면 변경 하실수없습니다.</div>
                </div>

                <div class="row mt-2">
                    <div class="col-xs-2">
                        <label for="userNickName">닉네임</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-5">
                        <input type="text" id="userNickName" class="form-control" name="user_nickname"
                               value="${sessionUser.user_nickname}">
                    </div>

                    <div class="col-xs-3">
                        <button type="button" class="btn btn-primary pull-right"
                                id="checkNickNameButton">닉네임 중복확인
                        </button>
                    </div>

                    <div class="col my-auto" style="font-size:10px;color:#6667ab;" id="alertNickName"></div>
                </div>

                <div class="row mt-2">
                    <div class="col-xs-2">
                        <label for="userPhone">휴대폰번호</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-5">
                        <input type="text" id="userPhone" class="form-control" name="user_phone"
                               value="${sessionUser.user_phone}">
                    </div>

                    <div class="col-xs-3">
                        <button type="button" class="btn btn-primary pull-right"
                                id="checkPhoneNumber">휴대폰 중복확인
                        </button>
                    </div>
                    <div class="col-xs-6" style="font-size:10px;color:#6667ab;" id="alertPhone"></div>
                </div>

                <div class="row mt-2">
                    <div class="col-xs-2">
                        <label for="userEmail">이메일</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-4">
                        <input type="text" id="userEmail" class="form-control" name="user_email"
                               value="${sessionUser.user_email}">
                    </div>
                    <div class="col-xs-3">
                        <button type="button" id="checkEmailButton"
                                class="btn btn-primary pwModBtn">이메일 중복체크
                        </button>
                    </div>

                    <div class="col my-auto" id="alertEmail" style="font-size:10px;color:#6667ab;"></div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-4">
                        <input type="text" id="userCertified" class="form-control" disabled="disabled">
                    </div>

                    <div class="col-xs-3">
                        <button type="button" id="CertifiedEmailButton"
                                class="btn btn-primary pwModBtn" disabled="disabled">인증번호 발송
                        </button>
                    </div>
                    <div class="col my-auto" style="font-size:10px; color:#6667ab;" id="alertCertified"></div>
                </div>

                <div class="row mt-2">
                    <div class="col-xs-6">
                        <label for="userQuestion">비밀번호 찾기 질문</label>
                    </div>
                </div>

                <div class="row mt-1">
                    <div class="col-xs-6">
                        <select class="form-select" name="question_no" id="userQuestion"
                                aria-label="Default select example">
                            <c:forEach items="${data }" var="question">
                                <option value="${question.question_no }" <c:if
                                        test="${question.question_no eq sessionUser.question_no}">selected="selected"</c:if>>
                                        ${question.question_content }</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row mt-2">
                    <div class="col-xs-5">
                        <input type="text" id="userFindAnswer" class="form-control"
                               value="${sessionUser.user_findAnswer}">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-primary infoModBtn" id="updateInfo" disabled="disabled">수정
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>