package io.swagger.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to io.swagger api documentation
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:io.swagger-ui.html";
    }
}
