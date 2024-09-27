<%@page import="java.Dto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글제목, 제목, 작성자 <hr>

<%

Dao dao = new Dao();
ArrayList<Dto> posts = dao.list();
for(int i=0; i<posts.size(); i=i+1){
	
%>	
<%=posts.get(i).no %>
<a href = "read.jsp? no=<%=posts.get(i).no %>"><%=posts.get(i).title %></a>
<%=posts.get(i).id %>
<hr>

<%

}
%>
<a href = "write.jsp">쓰기</a>
</body>
</html>