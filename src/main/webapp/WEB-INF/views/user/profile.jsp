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
