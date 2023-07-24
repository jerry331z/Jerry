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
                <div class="col-xs-2">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" id="userCookie" > 로그인유지
                        </label>
                    </div>
                </div>

                <div class="col-xs-2">
                    <div class="checkbox icheck">
                        <input type="checkbox" id="saveIdBox" > 아이디저장
                    </div>
                </div>

                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="button" id="loginButton" class="btn btn-primary btn-block btn-flat">
                        <i class="fa fa-sign-in"></i> 로그인
                    </button>
                </div>
                <!-- /.col -->
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