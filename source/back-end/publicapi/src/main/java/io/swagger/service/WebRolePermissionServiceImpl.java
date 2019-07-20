package io.swagger.service;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dao.RolePermission;
import io.swagger.pojo.dao.repos.PermissionRepository;
import io.swagger.pojo.dao.repos.RolePermissionRepository;
import io.swagger.pojo.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class WebRolePermissionServiceImpl extends BasicService<RolePermission> implements WebRolePermissionSevice {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public int detect(List<Long> permissionList) throws Exception {
        for (Long permission : permissionList) {
            if (permissionRepository.findByIdEquals(permission) == null) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int deletePermission(Long id) {
        return rolePermissionRepository.updateIsDelByRoleId(id, Boolean.TRUE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleDto roleDto, Long createBy) throws Exception {

        if (roleDto.getPermissionList().size() == 0) {
            throw new Exception("权限列表不可为空");
        } else if (detect(roleDto.getPermissionList()) == 0) {
            throw new Exception("权限不存在");
        } else {
            deletePermission(roleDto.getId());
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleDto.getId());
            for (Long permissionId : roleDto.getPermissionList()) {

                rolePermission.setPermissionId(permissionId);

                beforeAdd(rolePermission, createBy);
//                rolePermission.setUpdateAt(new Date());
//                rolePermission.setUpdateBy(createBy);
//                rolePermission.setIsDel(Boolean.FALSE);
                rolePermissionRepository.save(rolePermission);
            }

        }
    }

    /**
     * 列出该用户所有权限
     * @param roleDto
     * @return
     */
    @Override
    public List<String> list(RoleDto roleDto) {

        List<Long> perimissionList = rolePermissionRepository.findPermissionIdsByRoleIdEqualsAndIsNotDel(roleDto.getId(),Boolean.FALSE);

        List<String> permissionNameList = new ArrayList<>();
        for (Long permission : perimissionList) {
            String permissionName = permissionRepository.findByIdEquals(permission).getName();
            permissionNameList.add(permissionName);
        }
        return permissionNameList;
    }


}
