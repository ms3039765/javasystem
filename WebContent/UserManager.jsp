<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User,java.util.List,java.util.ArrayList" %>
<%
	@SuppressWarnings("unchecked")
	List<User> userList = (List<User>) session.getAttribute("userList");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>社員一覧</title>
</head>
<body>
社員一覧<br/>
<table border="1">
   <tr bgcolor="#afeeee">
		<th>id</th>
		<th>名前</th>
		<th>パスワード</th>
		<th>処理</th>
	</tr>
	<tr>
		<form method="POST" action="UserManagerServlet">
			<td><input name="user_id" type="text" /></td>
			<td><input name="user_name" type="text" /></td>
			<td><input name="password" type="password" /></td>
			<td><input name="registration" type="submit" value="登録" /></td>
		</form>
	</tr>
	 <% for(User user: userList) { %>
		<tr>
			<form method="POST" action="UserManagerServlet">
				<td><input name="user_id" type="text" value="<%= user.getUser_id() %>" /></td>
				<td><input name="user_name"  type="text" value="<%= user.getUser_name() %>" /></td>
				<td><input name="password"  type="password" value="<%= user.getPassword() %>" /></td>
				<td>
					<input  name="registration" type="submit" value="更新"/>
					<input  name="registration" type="submit" value="削除"/>
				</td>
			</form>
		</tr>
	<% } %>
</table>
<form method="GET" action="LoginServlet">
    <input type="submit" value="ログイン画面へ">
</form>
</body>
</html>