<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String aname = request.getParameter("aname");
session.setAttribute("aname",aname);
String redirectURL = "http://localhost:8080/PAF/shipprofile.jsp";
response.sendRedirect(redirectURL);
%>

</body>
</html>