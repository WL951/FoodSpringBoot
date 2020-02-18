package com.wl.foodspringboot.Filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {""},filterName = "LoginFilter") //urlPatterns：拦截规则，支持正则
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("启动容器时，过滤器初始化。");
    }

    /**
     *请求被拦截时被调用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("容器销毁时，过滤器销毁。");
    }
}
