package io.swagger.controller;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.pojo.dto.RoleDto;
import io.swagger.service.WebRolePermissionSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rolePermission")
public class WebRolePermissionController extends WebBasicController{
    @Autowired
    private WebRolePermissionSevice webRolePermissionSevice;

    /**
     * 增加权限
     * @param
     * @return
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody RoleDto roleDto) {
        BasicResponse basicResponse = new BasicResponse();

        Long createBy = super.getUserId();
        try {
            webRolePermissionSevice.add(roleDto, createBy);
            basicResponse.setData("给角色添加权限成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("给角色添加权限失败: " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 列出该用户已有权限
     * @param roleDto
     * @return
     */
    @RequestMapping("/list")
    public BasicResponse list(RoleDto roleDto){
        BasicResponse basicResponse = new BasicResponse();
        try {
            List<String> permissionName=webRolePermissionSevice.list(roleDto);
            basicResponse.setData("列出用户权限成功"+permissionName);
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("列出用户权限失败: " + e.getMessage());
        }
        return basicResponse;
    }
}
