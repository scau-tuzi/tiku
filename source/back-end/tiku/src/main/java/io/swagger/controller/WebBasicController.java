package io.swagger.controller;

import io.swagger.pojo.dao.User;
import org.apache.shiro.SecurityUtils;

public class WebBasicController {

    /**获取当前登录用户名
     * @return
     */
    public String getUsername(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal!=null && principal instanceof User){
            String username = ((User) principal).getUsername();
            return username!=null?username:"unknown";
        }
        //防止抛NPE
        return "unknown";
    }

    /**获取当前登录id
     * @return
     */
    public Long getUserId(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal!=null && principal instanceof User){
            Long id = ((User) principal).getId();
            return id!=null?id:1L;
        }
        //防止抛NPE
        return 1L;
    }
}
