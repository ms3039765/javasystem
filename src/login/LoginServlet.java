package login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RequestDispatcher dispatch;

    /**
     * ログイン失敗画面に遷移する。
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        dispatch = request.getRequestDispatcher("Login.jsp");
        dispatch.forward(request, response);

    }

    /**
     * 利用者IDが登録されていれば利用者管理画面に遷移する。
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loginUserName = request.getParameter("user_name");
        String loginPassWord = request.getParameter("password");
        User loginInfomation = new User(loginUserName, loginPassWord);
        UserDao dao = new UserDaoImpl();
        User loginUser = dao.getLoginUser(loginInfomation);
        if(loginUser == null) {
            dispatch = request.getRequestDispatcher("LoginNG.jsp");
            dispatch.forward(request, response);

        }else {
        	List<User> userList = dao.getAllUser();
        	session.setAttribute("userList", userList);
            dispatch = request.getRequestDispatcher("UserManager.jsp");
            dispatch.forward(request, response);

        }

    }

}