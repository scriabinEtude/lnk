package com.lnk.web.cmm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class HttpInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    //매개변수 handler : 핸들러 정보를 의미(RequestMapping, DefaultServletHandler)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //logger.info("Before Method");
        //반환이 false라면 controller로 요청을 안함
        return true;
    }

    //controller handler가 끝난후
    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response,
                            Object handler, ModelAndView modelAndView) {
        //logger.info("Method Executed");
    }

    //view까지 처리가 끝난 후
    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                          Object handler, Exception ex) {
        //logger.info("Method Completed");
    }

}
