package io.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/web/**")
    public String getMainPage(){
        return "index.html";
    }
}
