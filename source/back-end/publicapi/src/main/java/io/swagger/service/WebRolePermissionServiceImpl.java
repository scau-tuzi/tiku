package io.swagger.service;

import io.swagger.pojo.dao.repos.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebRolePermissionServiceImpl extends BasicService implements WebRolePermissionSevice{

    @Autowired
    private RolePermissionRepository rolePermissionRepository;


}
