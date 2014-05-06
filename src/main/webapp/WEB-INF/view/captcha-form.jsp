<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<title>Captcha</title>
	
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-captcha {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-captcha .form-captcha-heading,
      .form-captcha input[type="text"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
</head>
<body>
	<div class="container">
	
		<form:form class="form-captcha" commandName="captchaForm" action="captcha" method='POST'>
			<h2>${ captchaForm.question }</h2>
			<form:input path="answer" /><br/>
			<form:errors path="answer" cssClass="alert alert-error" />
			<br />
			<br />
			<input name="submit" type="submit" value="submit" class="btn btn-large btn-primary" />
			<form:hidden path="id" />
		</form:form>
	
	</div>
	
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
