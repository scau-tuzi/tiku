package io.swagger.controller;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/permission")
public class WebPermissionController extends WebBasicController {

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

        Long createBy = super.getUserId();
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
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public BasicResponse list(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        BasicResponse basicResponse = new BasicResponse();

        pageNumber = (pageNumber < 0 ? 0 : pageNumber);
        pageSize = (pageSize < 1 || pageSize > 100 ? 100 : pageSize);
        try {
            Map<String, Object> resultMap = webPermissionService.list(pageNumber, pageSize);
            basicResponse.setData(resultMap);
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

        Long updateBy = super.getUserId();
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
