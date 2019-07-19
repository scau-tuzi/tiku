package io.swagger.controller;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.pojo.dto.PermissionDto;
import io.swagger.service.WebPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/permission")
public class WebPermissionController {

    @Autowired
    private WebPermissionService webPermissionService;

    /**
     * 增加权限
     * @param permission
     * @return
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody Permission permission) {
        BasicResponse basicResponse = new BasicResponse();

        Long createBy = 1L;
        try {
            webPermissionService.add(permission, createBy);
            basicResponse.setData("权限添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("权限添加失败: " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 删除权限
     * @param idList
     * @return
     */
    @DeleteMapping("/delete")
    public BasicResponse delete(@RequestBody List<Long> idList) {
        BasicResponse basicResponse = new BasicResponse();
        try {
            webPermissionService.deleteAll(idList);
            basicResponse.setData("权限删除成功");
        } catch (Exception e) {
            basicResponse.setData("权限删除失败:" + e.getMessage());
            basicResponse.setCode(BasicResponse.ERRORCODE);
        }
        return basicResponse;
    }

    /**
     * 查询权限列表
     *
     * @return
     */
    @RequestMapping("/list")
    public BasicResponse list() {
        BasicResponse basicResponse = new BasicResponse();

        try {
            List<PermissionDto> permissionDtoList= webPermissionService.list();
            basicResponse.setData(permissionDtoList);
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("权限列表查询失败: " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 更新权限
     * @param permission
     * @return
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody Permission permission) {
        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {

            webPermissionService.update(permission, updateBy);
            basicResponse.setData("更改权限成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("更改权限失败: " + e.getMessage());
        }
        return basicResponse;

    }
}
