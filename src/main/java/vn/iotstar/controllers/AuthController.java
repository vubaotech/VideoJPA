package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;
import vn.iotstar.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/login", "/register", "/logout" })
public class AuthController extends HttpServlet {

	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(req.getContextPath() + "/home");

		} else if (url.contains("login")) {
			RequestDispatcher rq = req.getRequestDispatcher("/views/web/login.jsp");
			rq.forward(req, resp);

		} else if (url.contains("register")) {
			RequestDispatcher rq = req.getRequestDispatcher("/views/web/register.jsp");
			rq.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("login")) {
			login(req, resp);
		} else if (url.contains("register")) {
			register(req, resp);
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			User user = userService.login(email, password);

			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("acc", user);

				// Phân quyền
				if (user.isAdmin()) {
					// Nếu là Admin, chuyển đến trang admin
					resp.sendRedirect(req.getContextPath() + "/admin/home");
				} else {
					// Nếu là User, chuyển về trang chủ
					resp.sendRedirect(req.getContextPath() + "/home");
				}
			} else {
				// Đăng nhập thất bại
				req.setAttribute("error", "Email hoặc mật khẩu không đúng!");
				req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Đã xảy ra lỗi, vui lòng thử lại.");
			req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User user = new User();
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			user.setFullName(req.getParameter("fullName"));

			userService.register(user);

			resp.sendRedirect(req.getContextPath() + "/login");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
		}
	}
}
