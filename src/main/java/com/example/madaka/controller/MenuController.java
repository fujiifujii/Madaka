package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/madaka")
public class MenuController {
    @GetMapping("/menu")
    public String main(){
        return "menu";
    }
}