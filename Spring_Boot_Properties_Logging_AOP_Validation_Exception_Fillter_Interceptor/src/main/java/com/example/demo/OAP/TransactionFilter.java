package com.example.demo.OAP;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Component

public class TransactionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Remote Host:"+ request.getRemoteHost());
        System.out.println("Remote  Address:"+ request.getRemoteAddr());
        HttpServletRequest _request=(HttpServletRequest) request;
        Enumeration<String > headerNames=(_request).getHeaderNames();
        String bannedUserAgent="Postman";
        String userAgent=_request.getHeader("user-agent");
        if(userAgent.contains(bannedUserAgent))
        {
            ((HttpServletResponse) response).addHeader("name","Phuc Hau");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String,String > message= new HashMap();
            message.put("message","Ban dang yeu cau tu Postman");

            message.put("timestamp",new Date().toString());
            message.put("status","403");
            ((HttpServletResponse) response).getWriter().write((message.toString()));
            return ;
        }
        filterChain.doFilter(request,response);
    }
}
