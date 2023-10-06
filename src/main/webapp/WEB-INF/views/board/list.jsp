<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023-2023년 8월 28일 JerryDEV All rights reserved.   │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--

작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:20
작성용도 : Post main view page jsp file
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

    <%-- Content Wrapper. Contains page content --%>
    <div class="content-wrapper">
        <%-- Content Header (Page header) --%>
        <section class="content-header">
            <h1>

            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> HOME</a></li>
            </ol>
        </section>

        <%-- Main content --%>
        <section class="content container-fluid">

            <form name="writeForm" role="form" method="post">
                <input type="hidden" id="category" name="category_no" value="">
            </form>

            <form name="listForm" role="form" method="post">
                <input type="hidden" id="CATEGORY_NO" name="category_no" value="">
            </form>

            <form id="list" role="form" method="get">
                <input type="hidden" id="category_no" name="category_no" value="">
            </form>

            <%--------------------------
              | Your Page Content Here |
              --------------------------%>
            <div class="box-header with-border">

            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-xs-1">글 번호</th>
                        <th class="col-xs-1">카테고리</th>
                        <th class="col-xs-2">제목</th>
                        <th class="col-xs-2">작성자</th>
                        <th class="col-xs-2">작성일</th>
                        <th class="col-xs-1">조회수</th>
                        <th class="col-xs-1">좋아요</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dataList }" var="data">
                        <tr>
                            <td>${data.boardVo.board_no}</td>
                            <td>${data.categoryVo.category_name}</td>
                            <td><a
                                    href="javascript:goPage(${data.boardVo.board_no});">${data.boardVo.board_title }</a>
                                <span class="badge bg-teal"><i class="fa fa-comment-o"></i> + ${data.totalCommentCount}</span>
                            </td>
                            <td>${data.userVo.user_nickname}</td>
                            <td><fmt:formatDate value="${data.boardVo.board_write_date }"
                                                pattern="yyyy:MM:dd: HH:mm:ss"/></td>
                            <td><span class="badge bg-red">${data.boardVo.board_view_count}</span></td>
                            <td><span class="badge bg-teal">${data.totalLikeCount}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="box-footer">
                <div class="pull-right">
                    <a class="btn btn-success btn-flat" href="javascript:writing(${category_no});">
                        <i class="fa fa-pencil"></i> 글쓰기
                    </a>
                </div>
            </div>
        </section>
    </div>
    <%-- /.content --%>
</div>
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