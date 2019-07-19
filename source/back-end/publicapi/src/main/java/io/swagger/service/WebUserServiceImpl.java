package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.dao.User;
import io.swagger.pojo.dao.repos.RoleRepository;
import io.swagger.pojo.dao.repos.UserRepository;
import io.swagger.pojo.dto.UserDto;
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
public class WebUserServiceImpl extends BasicService<User> implements WebUserService {
    @Autowired
    private UserRepository userRepository;


    //
    //    @Autowired
    //    private WebRoleServiceImpl webRoleService;

    //    /**
    //     * 返回角色名角色ID映射表
    //     *
    //     * @return
    //     */
    //    public Map<Long, String> listRole() {
    //        return webRoleService.selectRole();
    //
    //    }

    @Autowired
    private RoleRepository roleRepository;


    /**
     * 密码加密
     */
    public String passwordMD5(String password) {
        return password;
    }


    /**
     * 增加一个用户
     *
     * @param userDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(UserDto userDto, Long createdBy) throws Exception {
        //判断用户名是否为空
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new Exception("用户名不可为空！");
        } else if (userDto.getRoleId() == null) {
            throw new Exception("角色不可为空！");
        } else if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new Exception("密码不可为空！");
        }
        //判断用户名是否已存在
        else if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new Exception("该用户名已存在！");
        } else {

            User user = new User();
            user.setPasswordSaltMd5(passwordMD5(userDto.getPassword()));
            beforeAdd(user, createdBy);
            BeanUtils.copyProperties(userDto, user);
            userRepository.save(user);
        }

    }

    /**
     * 批量删除用户
     *
     * @param idList
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Long> idList) throws Exception {
        for (Long id : idList) {
            this.delete(id);
        }
    }

    /**
     * 删除一个用户
     */
    @Override
    public void delete(Long id) throws Exception {
        //判断id是否正确
        User user = userRepository.findByIdEquals(id);
        if (user == null || user.getIsDel() == Boolean.TRUE)
            throw new Exception("用户id不存在！");
        else {
            this.deleteBasicInfo(id);
        }
    }

    @Override
    public int deleteBasicInfo(Long id) {
        return userRepository.updateIsDelById(id, Boolean.TRUE);
    }

    /**
     * 查找出所有的user
     */
    @Override
    public Map<String, Object> list(Integer pageNumber, Integer pageSize) {
        Page<User> page = userRepository.findAllByIsDel(PageRequest.of(pageNumber, pageSize), Boolean.FALSE);

        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : page.getContent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            //todo 不传密码到前端
            userDto.setPassword(null);

            userDtoList.add(userDto);
        }

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userDtoList", userDtoList);
        resultMap.put("pagination", pagination);
        return resultMap;
    }


    /**
     * 更改用户信息
     *
     * @param userDto
     * @param updateBy
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(UserDto userDto, Long updateBy) throws Exception {

        //判断传入参数的正确

        if (userRepository.findById(userDto.getId()).get() == null) {
            throw new Exception("该用户id不存在！");
        }
        //        else if(userRepository.findById(userDto.getId()).get().getUsername().equals(userDto.getUsername())){
        //            throw new Exception("用户名不可与更改前相同！");
        //        }
        else if (userDto.getRoleId() == null) {
            throw new Exception("角色不可为空");
        } else if (userDto.getPassword() == null) {

            if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {

                throw new Exception("密码不可为空");
            } else if (userDto.getRoleId() == null || roleRepository.findByIdEquals(userDto.getRoleId()) == null) {
                throw new Exception("角色不可为空");
            } else if (userDto.getId() == null || userRepository.findByIdEquals(userDto.getId()) == null) {
                throw new Exception("用户id不存在！");
            } else if (userDto.getUsername() == null || userRepository.findByUsername(userDto.getUsername()) == null) {
                throw new Exception("用户名已存在！");
            } else {
                User user = userRepository.findById(userDto.getId()).get();


                BeanUtils.copyProperties(userDto, user);
                beforeUpdate(user, updateBy);

                //todo MD5加密

                user.setPasswordSaltMd5(passwordMD5(userDto.getPassword()));

                userRepository.save(user);
            }

        }


    }
}
