<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 읽기</title>
</head>

<body>
	<input type="button" onclick="writeForm()" value = "글쓰기"><br><br>
	
	
  <c:forEach var="article" items="${articleList}">
 	글번호 : ${article.articleNum}<br>
 	아이디 : ${article.id}<br>
 	제목 : <a href="/bbs/content.bbs?articleNum= ${article.articleNum}"> ${article.title}</a><br>
 	내용 : ${article.content}<br>
 	날짜 : ${article.writeDate}<br>
 	<hr/>
  		
  	</c:forEach>

	<script type="text/javascript">
	
		function writeForm(){
			location.href="/bbs/write.bbs"
		}
	
	</script>

</body>
</html>