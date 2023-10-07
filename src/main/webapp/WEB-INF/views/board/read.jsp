<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 10월 7일 JerryDEV All rights reserved.        │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
  Created by IntelliJ IDEA.
  User: hanbyeols
  Date: 2023-10-07
  Time: 오전 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/head.jsp" %>
<html>
<body class="hold-transition skin-green-light sidebar-mini" oncopy="return false" oncut="return false"
      onpaste="return false">
<div class="wrapper">

    <%@ include file="../include/main_header.jsp" %>

    <%@ include file="../include/left_column.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">
            <div class="col-lg-12">
                <div class="box box-primary">

                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${data.boardVo.board_title}</h3>
                        <ul class="list-inline pull-right">
                            <li><a href="#" class="link-black text-lg" id="likeCount">좋아요 수(${data.totalLikeCount})</a>
                            </li>
                            <li><a href="#" class="link-black text-lg"><i class="fa fa-eye"></i>조회수
                                (${data.boardVo.board_view_count})</a></li>
                        </ul>
                    </div>

                    <%--게시글 내용 영역--%>
                    <div class="box-body" style="height: 700px">
                        ${fn:replace(fn:replace(fn:escapeXml(data.boardVo.board_content), newLine, "<br/>") , " ", "&nbsp;")}
                    </div>

                    <%--작성자 정보 영역--%>
                    <div class="box-footer">
                        <div class="user-block">
                            <ul class="navbar-custom-menu">
                                <li class="dropdown messages-menu">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <img class="img-circle img-bordered-sm"
                                             src="${path}/resources/dist/img/profile/${data.userVo.user_image}"
                                             alt="user image">
                                        <span>${data.userVo.user_nickname}</span>
                                        <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm"
                                                                                  value="${data.boardVo.board_write_date}"/></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">작성 글보기</a></li>
                                        <li><a href="#">작성 댓글보기</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" id="boardNo" name="board_no" value="${data.boardVo.board_no}">
                            <input type="hidden" id="userNo" name="user_no" value="${sessionUser.user_no}">
                            <input type="hidden" id="categoryNo" name="category_no" value="${data.boardVo.category_no}">
                        </form>
                        <button class="btn btn-primary" onclick="location.href='list?category_no=${data.boardVo.category_no}'"><i
                                class="fa fa-list"></i> 목록
                        </button>
                        <c:if test="${!empty sessionUser}">
                            <button type="button" class="btn btn-info text-lg-center" id="boardLike"> 좋아요</button>
                            <button type="button" class="btn btn-info text-lg-center" id="postingBookMark"> 북마크</button>
                        </c:if>
                        <c:if test="${sessionUser.user_no == data.boardVo.user_no}">
                            <div class="pull-right">
                                <button type="button" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정
                                </button>
                                <button type="button" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제
                                </button>
                            </div>
                        </c:if>
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