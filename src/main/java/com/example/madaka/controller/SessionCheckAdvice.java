package com.example.madaka.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class SessionCheckAdvice {
	@ModelAttribute
    public void checkSession(HttpServletRequest request, HttpSession session) {

        String path = request.getRequestURI();

        // ログイン画面は除外
        if (path.contains("/login") || path.contains("/error")) {
            return;
        }

        if (session.getAttribute("loginUser") == null) {
            throw new NoSessionException();
        }
    }
}

