package io.swagger.controller;

import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dao.User;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.pojo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/api/")
public class LoginController {
    @RequestMapping("/login")
    public BasicResponse login(@RequestBody UserDto userDto) throws Exception{


        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(),userDto.getPassword());

        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "password error!");
            return null;
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "username error!");
            return null;
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "times error");
            return null;
        }
//        User user = userService.findByUsername(username);
//        subject.getSession().setAttribute("user", user);

        return null;
    }
}
