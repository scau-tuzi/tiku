package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dao.repos.RoleRepository;
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
     * 新增角色
     *
     * @param role
     * @param createBy
     * @throws Exception
     */
    @Override
    public void add(Role role, Long createBy) throws Exception {
        if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
            throw new Exception("角色名不可为空");
        } else if (roleRepository.findByRoleName(role.getRoleName()) != null) {
            throw new Exception("该角色名已存在");
        } else {
            beforeAdd(role, createBy);
            Role dbRole = new Role();
            BeanUtils.copyProperties(role, dbRole);
            roleRepository.save(dbRole);
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
            deleteBasicInfo(id);
        } else {
            throw new Exception("该角色id不存在");
        }
    }

    /**
     * 查找出所有标签
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> list(Integer pageNumber, Integer pageSize) {

        Page<Role> page = roleRepository.findAllByIsDel(PageRequest.of(pageNumber, pageSize), Boolean.FALSE);

        //角色信息
        List<Role> roleList = new ArrayList<>(page.getContent());

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList", roleList);
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
    public void update(Role role, Long updateBy) throws Exception {
        if (role.getId() == null || roleRepository.findByIdEquals(role.getId()) == null) {
            throw new Exception("该用户不存在");
        } else if (roleRepository.findByRoleName(role.getRoleName()) != null) {
            throw new Exception("新角色名已存在");
        } else {
            Role dbRole = new Role();
            BeanUtils.copyProperties(role, dbRole);
            beforeUpdate(dbRole, updateBy);

            roleRepository.save(dbRole);
        }

    }


}
