<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 6월 29일 JerryDEV All rights reserved.        │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
작성자 : Min Woo Song
작성일 : 2023-06-29
작성시간 : PM 3:22
작성용도 : <head> tag setting jsp file
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<%--head.jsp--%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>JerryDEV | Spring MVC Web Framework Web Bulletin Walkthrough Example</title>
    <%-- Tell the browser to be responsive to screen width --%>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <%-- Font Awesome --%>
    <link rel="stylesheet" href="/bower_components/font-awesome/css/font-awesome.min.css">
    <%-- Ionicons --%>
    <link rel="stylesheet" href="/bower_components/Ionicons/css/ionicons.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/plugins/iCheck/square/blue.css">
    <%-- Theme style --%>
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <%-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. --%>
    <link rel="stylesheet" href="/dist/css/skins/skin-blue.min.css">

    <%--fileupload--%>
    <link rel="stylesheet" media="screen" href="/bower_components/fileupload/css/jasny-bootstrap.min.css">
    <%--lightbox--%>
    <link rel="stylesheet" href="/bower_components/lightbox/css/lightbox.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
    <%-- WARNING: Respond.js doesn't work if you view the page via file:// --%>
    <%--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]--%>

    <%-- Google Font --%>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
