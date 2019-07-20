package io.swagger.service;

import io.swagger.pojo.dao.Permission;
import io.swagger.pojo.dao.repos.PermissionRepository;
import io.swagger.pojo.dto.PermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WebPermissionServiceImpl extends BasicService implements WebPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;


    /**
     * 列出权限id对应权限的名称
     *
     * @return
     */
    public Map<Long, String> selectPermission() {
        List<Long> permissionIdList = permissionRepository.selectPermissionId();
        Map<Long, String> permissionIdPermissionName = new HashMap<>();
        for (Long id : permissionIdList) {
            permissionIdPermissionName.put(id, permissionRepository.findByIdEquals(id).getName());
        }
        return permissionIdPermissionName;
    }


    /**
     * 增加权限
     *
     * @param permission
     * @param createBy
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Permission permission, Long createBy) throws Exception {
        if (permission.getName() == null) {
            throw new Exception("权限名不可为空");
        } else if (permission.getMethod() == null) {
            throw new Exception("方法名不可为空");
        } else if (permission.getUrl() == null) {
            throw new Exception("url不可为空");
        } else if (permission.getParentPermission() == null) {
            throw new Exception("父权限不可为空");
        } else if (!permissionRepository.selectParentPermissons().contains(permissionRepository.findByIdEquals(permission.getParentPermission()))) {
            throw new Exception("父权限不存在");
        } else if (permissionRepository.findByName(permission.getName()) != null) {
            throw new Exception("权限名已存在");
        } else {
            beforeAdd(permission, createBy);
            Permission dbPermission = new Permission();
            BeanUtils.copyProperties(permission, dbPermission);
            permissionRepository.save(dbPermission);
        }
    }

    @Override
    public int deleteBasicInfo(Long id) {
        return permissionRepository.updateIsDelById(id, Boolean.TRUE);
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
     * 删除一个权限
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(Long id) throws Exception {
        if (id != null && permissionRepository.findByIdEquals(id) != null) {
            deleteBasicInfo(id);
        } else {
            throw new Exception("该角色id不存在");
        }
    }


    private boolean checkIsNotHadParent(PermissionDto permission){
        return permission.getParentPermission() == null || permission.getParentPermission()==(0L);
    }

    /**
     * 列出权限列表，包括权限的父id
     *
     * @return
     */
    public List<PermissionDto> list() {

        List<Permission> all = permissionRepository.findAll();

        HashMap<Long,PermissionDto> tree=new HashMap<>();

        all.forEach((p)->{
            PermissionDto pdto = new PermissionDto();
            BeanUtils.copyProperties(p, pdto);
            tree.put(pdto.getId(),pdto);
        });

        tree.values().forEach((p)->{

            if(!checkIsNotHadParent(p)){
                PermissionDto parent = tree.getOrDefault(p.getParentPermission(), null);
                if(parent==null){
                    log.error("权限对象的父权限记录不存在，对象{}",p.toString());
                    return;
                }

                if(parent.getChildPermissions()==null){
                    parent.setChildPermissions(new ArrayList<>());
                }
                parent.getChildPermissions().add(p);
            }
        });


        List<PermissionDto> parentPermissionDtos = new ArrayList<>();

        tree.values().forEach((p)->{
            if(checkIsNotHadParent(p)){
                parentPermissionDtos.add(p);
            }
        });

        return parentPermissionDtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    /**
     * 更新权限
     */
    public void update(Permission permission, Long updateBy) throws Exception {
        if (permission.getId() == null || permissionRepository.findByIdEquals(permission.getId()) == null) {
            throw new Exception("该权限id不存在");
        } else if (permission.getName() == null) {
            throw new Exception("权限名不可为空");
        } else if (permission.getUrl() == null) {
            throw new Exception("url不可为空");
        } else if (permission.getMethod() == null) {
            throw new Exception("方法不可为空");
        } else if (permission.getParentPermission() == null) {
            throw new Exception("父权限不可为空");
        } else if (!permissionRepository.selectParentPermissons().contains(permissionRepository.findByIdEquals(permission.getParentPermission()))) {
            throw new Exception("父权限不存在");
        } else if (permissionRepository.findByName(permission.getName()) != null) {
            throw new Exception("新权限名已存在");
        } else {
            Permission dbPermission = new Permission();
            BeanUtils.copyProperties(permission, dbPermission);
            beforeUpdate(dbPermission, updateBy);

            permissionRepository.save(dbPermission);
        }

    }


}
