package br.com.dcm.common;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmartinez on 11/05/2017.
 */
@Component
public class HttpInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("ops");
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("ops");
    }
}
