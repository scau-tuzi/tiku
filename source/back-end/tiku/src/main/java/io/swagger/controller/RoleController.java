package io.swagger.controller;

import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebRoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private WebRoleServiceImpl webRoleService;

    @RequestMapping("/selectRole")
    public BasicResponse selectRole(){
        BasicResponse basicResponse = new BasicResponse();

        basicResponse.setData( webRoleService.selectRole());

        return basicResponse;
    }
}
