package vn.iotstar.controllers.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.entity.User;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        User user = (User) session.getAttribute("acc");
        
        if (user != null && user.isAdmin()) {
            chain.doFilter(request, response);
            
        } else if (user != null && !user.isAdmin()) {
            resp.sendRedirect(req.getContextPath() + "/home");
            
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
