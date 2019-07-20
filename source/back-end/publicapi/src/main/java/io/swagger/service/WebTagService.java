package io.swagger.service;

import io.swagger.pojo.dao.Role;
import io.swagger.pojo.dao.Tag;
import io.swagger.pojo.dao.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WebTagService {
    Tag add(Tag tag, Long createBy) throws Exception;

    Integer findIfUsed(Long tagId);

    void delete(Long id) throws Exception;

    void deleteAll(List<Long> idList) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(Tag tag, Long updateBy) throws Exception;

    int deleteBasicInfo(Long id);

    List<Tag> getTagsByValueList(List<String> values);


}
