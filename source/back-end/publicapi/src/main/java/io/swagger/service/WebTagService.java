package io.swagger.service;

import io.swagger.pojo.dao.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface WebTagService {
    Tag add(Tag tag, Long createBy) throws Exception;

    Integer findIfUsed(@RequestBody Tag tag);

    void delete(Long id) throws Exception;

    Map<String, Object> list(Integer pageNumber, Integer pageSize);

    void update(Tag tag, Long updateBy) throws Exception;

    int deleteBasicInfo(Long id);
    List<Tag> getTagsByValueList(List<String> values);
}
