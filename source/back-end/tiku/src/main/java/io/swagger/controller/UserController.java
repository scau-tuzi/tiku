package io.swagger.controller;

import io.swagger.pojo.dto.BasicResponse;
import io.swagger.pojo.dto.UserDto;
import io.swagger.service.WebStatusService;
import io.swagger.service.WebUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private WebUserServiceImpl webUserService;


    /**
     * 增加新用户
     *
     * @param userDto
     * @return
     */
    @RequestMapping("/add")
    public BasicResponse add(@RequestBody UserDto userDto) {
        BasicResponse basicResponse = new BasicResponse();

        Long createBy = 1L;
        try {
            webUserService.add(userDto, createBy);
            basicResponse.setData("用户添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("用户添加失败: " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 删除用户
     *
     * @param idList
     * @return
     */
    @RequestMapping("/delete")
    public BasicResponse delete(@RequestBody List<Long> idList) {
        BasicResponse basicResponse = new BasicResponse();
        try {
            webUserService.deleteAll(idList);
            basicResponse.setData("用户删除成功");
        } catch (Exception e) {
            basicResponse.setData("用户删除失败:"+e.getMessage());
            basicResponse.setCode(BasicResponse.ERRORCODE);
        }
        return basicResponse;
    }

    /**
     * 列出用户列表
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
            Map<String, Object> resultMap = webUserService.list(pageNumber, pageSize);
            basicResponse.setData(resultMap);
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("query error : " + e.getMessage());
        }
        return basicResponse;
    }

    /**
     * 更改用户信息
     *
     * @param userDto
     * @return
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody UserDto userDto) {
        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {

            webUserService.update(userDto, updateBy);
            basicResponse.setData("更改用户成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("更改用户失败: " + e.getMessage());
        }
        return basicResponse;

    }

}
