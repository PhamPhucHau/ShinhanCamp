package com.example.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class OfficeServiceInterceptor implements HandlerInterceptor {
    private final Logger logger= LoggerFactory.getLogger(OfficeServiceInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        logger.info("Pre Handle method is Calling");

        long requestTime=(new Date()).getTime();
        request.setAttribute("startTime",requestTime);
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long requestTime=(long) request.getAttribute("startTime");
        long responseTime= (new Date()).getTime();
        logger.info("Execution: "+(responseTime-requestTime)+"ms" );
       logger.info("Post Handle method is Calling");
       HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception{
        logger.info("Request and Response is completed");
        HandlerInterceptor.super.afterCompletion(request,response,handler,exception);
    }
}
