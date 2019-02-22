package com.wzy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 类功能说明: 注解扫描的filter
 * 类修改者	创建日期2019/2/13
 * 修改说明
 * <p>Title: FirstFilter.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("进入filter");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("离开filter");

    }

    @Override
    public void destroy() {

    }
}
