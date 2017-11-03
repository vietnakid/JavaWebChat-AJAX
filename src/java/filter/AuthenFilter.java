/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;

/**
 *
 * @author MyPC
 */
public class AuthenFilter implements Filter{
    UserModel userModel = new UserModel();
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        return;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String requestPath = req.getRequestURI();
        if(requestPath.endsWith("/Chat/") || requestPath.endsWith("login.jsp") || requestPath.endsWith("register.jsp") || requestPath.endsWith("login"))
        {
            if (userModel.getUserIdFromCookie(request) != 0) {
                ((HttpServletResponse)response).sendRedirect("chat.jsp");
                return;
            }
            else
            {
                chain.doFilter(request, response);
            }
        } else if (requestPath.contains("chat.jsp")){
            if (userModel.getUserIdFromCookie(request) == 0) {
                ((HttpServletResponse)response).sendRedirect("login.jsp");
                return;
            }
            else
            {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
       return;
    }
    
}
