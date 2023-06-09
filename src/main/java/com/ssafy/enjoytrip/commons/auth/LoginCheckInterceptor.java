//package com.ssafy.enjoytrip.commons.auth;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Slf4j
//public class LoginCheckInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//            Object handler) throws Exception {
//        String url = request.getRequestURI();
//        if (url.contains("swagger") || url.contains("api-docs") || url.contains("webjars")) {
//            return true;
//        }
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("loginUser") == null) {
//            log.info("로그인 미인증 유저");
//            return false;
//        }
//        return true;
//    }
//}


