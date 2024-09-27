package top.soft.bookonline.servlet;

import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.Impl.UserServiceImpl;
import top.soft.bookonline.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password  = req.getParameter("password");
        try {
            int user = userService.register(account,password);
            if(user == 1) {
                req.getRequestDispatcher("login.html").forward(req,resp);
            }else {
                resp.setContentType("text/html;charset=utf-8");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("<script>alert('注册失败');location.href='/redisterPageServlet';</script>");
            }
        }catch (Exception e) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('账户已经存在');location.href='/redisterPageServlet';</script>");
        }
    }
}

