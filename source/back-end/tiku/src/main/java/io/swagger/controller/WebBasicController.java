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
            return ((User) principal).getUsername();
        }
        return null;
    }

    /**获取当前登录id
     * @return
     */
    public Long getUserId(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if(principal!=null && principal instanceof User){
            return ((User) principal).getId();
        }
        return null;
    }
}
