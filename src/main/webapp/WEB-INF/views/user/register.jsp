<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--

작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:06
작성용도 : user register web view page jsp file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp" %>
<html>
<body class="hold-transition skin-blue sidebar-mini layout-boxed" oncontextmenu="return false" oncopy="return false"
      oncut="return false" onpaste="return false" onselect="return false">

<div class="wrapper">

    <%@ include file="../include/main_header.jsp" %>

    <%@ include file="../include/left_column.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">
            <div class="register-box-body">
                <p class="login-box-msg">회원가입 페이지</p>
                <form:form action="${path}/user/insertUserProcess" modelAttribute="userVo" id="insertForm"
                           method="post">
                    <div class="row mt-2">
                        <div class="col">
                            <div class="row mt-1">
                                <div class="col-5">
                                    <label for="joinIdInput">아이디</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="joinIdInput" class="form-control" path="user_id"
                                                placeholder="아이디를 입력하세요"/>
                                </div>

                                <div class="col-lg-1 d-grid">
                                    <button type="button" class="btn btn-primary pull-right"
                                            id="checkIdButton">중복확인
                                    </button>
                                </div>

                                <div class="col my-auto" id="alertId"><form:errors path="user_id"/></div>
                            </div>


                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="changePassword">비밀번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="password" id="changePassword" class="form-control"
                                                placeholder="비밀번호를 입력하세요" path="user_pw"/>
                                </div>

                                <div class="col my-auto" id="alterPassword"><form:errors path="user_pw"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="confirmPassword">비밀번호 확인</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <input type="password" id="confirmPassword" class="form-control"
                                           placeholder="비밀번호를 한번 더 입력해주세요">
                                </div>
                                <div class="col my-auto" id="alterPassword2"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userNickName">닉네임</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userNickName" class="form-control"
                                                path="user_nickname"
                                                placeholder="닉네임을 입력해주세요"/>
                                </div>

                                <div class="col-lg-1 d-grid">
                                    <button type="button" class="btn btn-primary pull-right"
                                            id="checkNickNameButton">닉네임 중복확인
                                    </button>
                                </div>

                                <div class="col my-auto" id="alertNickName"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userGender">성별</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:radiobutton id="userGender" path="user_gender" value="M"/>남
                                    <form:radiobutton id="userGender" path="user_gender" value="W"/>여
                                </div>
                                <div class="col-lg-5" id="alertGender"><form:errors path="user_gender"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userBirth">생년월일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="date" id="userBirth" class="form-control" path="user_birth"
                                                placeholder="생년월일을 선택해주세요"/>
                                </div>

                                <div class="col-lg-5" id="alertBirth"><form:errors path="user_birth"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userPhone">휴대폰번호</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userPhone" class="form-control" path="user_phone"
                                                placeholder="휴대폰번호를 입력해주세요"/>
                                </div>

                                <div class="col-lg-1 d-grid">
                                    <button type="button" class="btn btn-primary pull-right"
                                            id="checkPhoneNumber">휴대폰 중복확인
                                    </button>
                                </div>

                                <div class="col my-auto" id="alertPhone"><form:errors path="user_phone"/></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <label for="userEmail">이메일</label>
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <form:input type="text" id="userEmail" class="form-control" path="user_email"
                                                placeholder="이메일주소를 입력해주세요"/>
                                </div>

                                <div class="col-lg-1 d-grid">
                                    <button type="button" id="checkEmailButton"
                                            class="btn btn-primary pull-right">메일 중복 체크
                                    </button>
                                </div>

                                <div class="col my-auto" id="alertEmail"><form:errors path="user_email" /></div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-lg-5">
                                    <input class="form-control" id="checkEmail" type="text"
                                           placeholder="인증번호를 입력해주세요." aria-label="default input example"
                                           disabled="disabled">
                                </div>

                                <div class="col-lg-1 d-grid">
                                    <button type="button" class="btn btn-primary pull-left" id="emailCheck"
                                            disabled="disabled">인증번호 발송
                                    </button>
                                </div>

                                <div class="col my-auto" id="alertCertified"></div>
                            </div>

                            <div class="row mt-1">
                                <div class="col bi bi-exclamation-square-fill deepblue">
                                    인증번호 발송은 서버 상황에따라 5초에서 10초정도 시간이 걸릴 수 있습니다.
                                </div>
                            </div>

                            <div class="row mt-1">
                                <div class="col-lg-5">
                                    <a type="button" class="btn btn-default pull-left" href="../main/main">취소</a>
                                    <button type="button" class="btn btn-primary infoModBtn pull-right" id="joinButton">
                                        가입하기
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="userImage" name="user_image" value="default-user-image.jpg">
                </form:form>
            </div>
        </section>
    </div>

    <%@ include file="../include/main_footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>