package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dao.RolePermission;
import io.swagger.pojo.dao.repos.PermissionRepository;
import io.swagger.pojo.dao.repos.RolePermissionRepository;
import io.swagger.pojo.dao.repos.RoleRepository;
import io.swagger.pojo.dto.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebRoleServiceImpl extends BasicService<Role> implements WebRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    /**
     * 根据id选出rolename
     *
     * @param id
     * @return
     */
    @Override
    public String selectRolenameByRoleId(Long id) {
        return roleRepository.selectRolenameByRoleId(id);
    }

    @Override
    public int deleteBasicInfo(Long id) {
        return roleRepository.updateIsDelById(id, Boolean.TRUE);
    }

    @Override
    public int deletePermission(Long id) {
        return rolePermissionRepository.updateIsDelByRoleId(id, Boolean.TRUE);
    }

    /**
     * 列出角色列表，角色的id以及对应的角色名称
     *
     * @return
     */
    @Override
    public Map<Long, String> selectRole() {
        List<Long> roleIdList = roleRepository.selectRoleId();
        Map<Long, String> roleIdRolename = new HashMap<>();
        for (Long id : roleIdList) {
            roleIdRolename.put(id, roleRepository.findById(id).get().getRoleName());
        }
        return roleIdRolename;
    }

    /**
     * 角色的权限列表中的权限是否存在
     *
     * @param permissionList
     * @return 1：存在  0：不存在
     * @throws Exception
     */
    @Override
    public int detect(List<Long> permissionList) throws Exception {
        for (Long permission : permissionList) {
            if (permissionRepository.findByIdEquals(permission) == null) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 新增角色
     *
     * @param roleDto
     * @param createBy
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleDto roleDto, Long createBy) throws Exception {
        if (roleDto.getRoleName() == null || roleDto.getRoleName().isEmpty()) {
            throw new Exception("角色名不可为空");
        } else if (roleRepository.findByRoleName(roleDto.getRoleName()) != null) {
            throw new Exception("该角色名已存在");
        } else {
            Role role = new Role();
            BeanUtils.copyProperties(roleDto, role);
            beforeAdd(role, createBy);

            roleRepository.save(role);

        }

    }

    /**
     * 删除一个列表
     *
     * @param idList
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAll(List<Long> idList) throws Exception {
        for (Long id : idList) {
            this.delete(id);
        }
    }

    /**
     * 删除一个角色
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Long id) throws Exception {
        if (id != null && roleRepository.findByIdEquals(id) != null) {
            deletePermission(id);

            deleteBasicInfo(id);
        } else {
            throw new Exception("该角色id不存在");
        }
    }

    /**
     * 查找出所有角色
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> list(Integer pageNumber, Integer pageSize) {

        Page<Role> page = roleRepository.findAllByIsDel(PageRequest.of(pageNumber, pageSize), Boolean.FALSE);

        //角色信息
        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : page.getContent()) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            //获取用户的权限
            roleDto.setPermissionList(rolePermissionRepository.findPermissionIdsByRoleIdEqualsAndIsNotDel(role.getId(), Boolean.FALSE));

            roleDtoList.add(roleDto);
        }

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleDtoList", roleDtoList);
        resultMap.put("pagination", pagination);

        return resultMap;
    }

    /**
     * 更改已有角色
     *
     * @param role
     * @param updateBy
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RoleDto roleDto, Long updateBy) throws Exception {
        //List<Long> dbPermissionList = rolePermissionRepository.findPermissionIdsByRoleIdEquals(roleDto.getId());
        if (roleDto.getId() == null || roleRepository.findByIdEquals(roleDto.getId()) == null) {
            throw new Exception("该用户id不存在");
//        } else if (roleDto.getPermissionList().size() == 0) {
//            throw new Exception("权限列表不可为空");
//        } else if (detect(roleDto.getPermissionList()) == 0) {
//            throw new Exception("权限不存在");
        } else if (roleRepository.findByRoleName(roleDto.getRoleName()) != null) {
            throw new Exception("该角色已存在");
        } else {
            Role dbRole = roleRepository.findByIdEquals(roleDto.getId());
            BeanUtils.copyProperties(roleDto, dbRole);
            beforeUpdate(dbRole, updateBy);
            roleRepository.save(dbRole);

        }
    }


//            deletePermission(roleDto.getId());

//        } else if (roleDto.getPermissionList().size() != 0 && detect(roleDto.getPermissionList()) == 1) {
//
//            //删除该角色已有权限
//            deletePermission(roleDto.getId());
//            Role dbRole = roleRepository.findByIdEquals(roleDto.getId());
//            BeanUtils.copyProperties(roleDto, dbRole);
//            beforeUpdate(dbRole, updateBy);
//            roleRepository.save(dbRole);
//            RolePermission rolePermission = new RolePermission();
//            rolePermission.setRoleId(roleDto.getId());
//            for (Long permissionId : roleDto.getPermissionList()) {
//
//                rolePermission.setPermissionId(permissionId);
//
//                rolePermission.setUpdateAt(dbRole.getUpdateAt());
//                rolePermission.setUpdateBy(dbRole.getUpdateBy());
//                rolePermission.setIsDel(Boolean.FALSE);
//                rolePermissionRepository.save(rolePermission);
//            }
//        }
//
//    }

}
