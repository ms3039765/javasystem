package manager;

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

@WebServlet("/UserManagerServlet")
public class UserManagerServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;
    RequestDispatcher dispatch;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    }

    /**
     * 登録・更新・削除の実施。
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String DBOperation = request.getParameter("registration");
        UserDao dao = new UserDaoImpl();
        String userId = request.getParameter("user_id");
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        User user = new User(userId, userName, password);
        switch(DBOperation) {
        	case "登録":
        		dao.createUser(user);
        		break;

        	case "更新":
        		dao.updateUSer(user);
        		break;

        	default :	//削除
        		dao.deleteUser(user);
        		break;
        }

        //利用者管理システム画面の再表示
    	List<User> userList = dao.getAllUser();
    	session.setAttribute("userList", userList);
        dispatch = request.getRequestDispatcher("UserManager.jsp");
        dispatch.forward(request, response);

    }

}