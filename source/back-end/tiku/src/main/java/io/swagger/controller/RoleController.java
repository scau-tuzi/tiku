package io.swagger.controller;

import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebRoleServiceImpl;
import io.swagger.service.WebTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private WebRoleServiceImpl webRoleService;

    @RequestMapping("/selectRole")
    public BasicResponse selectRole() {
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setData(webRoleService.selectRole());

        return basicResponse;
    }

    /**
     * 增加新角色
     *
     * @param role
     * @return
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody Role role) {
        BasicResponse basicResponse = new BasicResponse();

        Long createBy = 1L;
        try {
            webRoleService.add(role, createBy);
            basicResponse.setData("角色添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("角色添加失败: " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 删除角色
     *
     * @param idList
     * @return
     */
    @DeleteMapping("/delete")
    public BasicResponse delete(@RequestBody List<Long> idList) {
        BasicResponse basicResponse = new BasicResponse();
        try {
            webRoleService.deleteAll(idList);
            basicResponse.setData("角色删除成功");
        } catch (Exception e) {
            basicResponse.setData("角色删除失败:" + e.getMessage());
            basicResponse.setCode(BasicResponse.ERRORCODE);
        }
        return basicResponse;
    }

    /**
     * 列出角色列表
     *
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
            Map<String, Object> resultMap = webRoleService.list(pageNumber, pageSize);
            basicResponse.setData(resultMap);
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("query error : " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 更改角色信息
     *
     * @param role
     * @return
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody Role role) {
        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {

            webRoleService.update(role, updateBy);
            basicResponse.setData("更改角色成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("更改角色失败: " + e.getMessage());
        }
        return basicResponse;

    }
}
