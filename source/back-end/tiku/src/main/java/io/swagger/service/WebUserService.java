package io.swagger.service;

import io.swagger.pojo.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface WebUserService {

    String passwordMD5(String password);

    void add(UserDto userDto, Long createdBy) throws Exception;

    int deleteBasicInfo(Long id);

    void delete(Long id) throws Exception;

    void deleteAll(List<Long> idList) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(UserDto userDto, Long updateBy) throws Exception;
}

