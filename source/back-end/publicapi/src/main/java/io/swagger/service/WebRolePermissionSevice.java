package io.swagger.service;

import io.swagger.pojo.dto.RoleDto;

import java.util.List;

public interface WebRolePermissionSevice {
    int detect(List<Long> permissionList) throws Exception;

    int deletePermission(Long id);

    void add(RoleDto roleDto, Long createBy) throws Exception;

    List<String> list(RoleDto roleDto);

}
