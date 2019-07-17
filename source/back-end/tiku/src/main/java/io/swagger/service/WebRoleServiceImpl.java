package io.swagger.service;

import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dao.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebRoleServiceImpl extends BasicService<Role> implements WebRoleService {

    @Autowired
    private RoleRepository roleRepository;

    public String selectRolenameByroleId(Long id) {
        return roleRepository.selectRolenameByRoleId(id);
    }

    public Map<Long, String> selectRole() {
        List<Long> roleIdList = roleRepository.selectRoleId();
        Map<Long, String> roleIdRolename = new HashMap<>();
        for (Long id : roleIdList) {
            roleIdRolename.put(id, roleRepository.findById(id).get().getRoleName());
        }
        return roleIdRolename;
    }
}
