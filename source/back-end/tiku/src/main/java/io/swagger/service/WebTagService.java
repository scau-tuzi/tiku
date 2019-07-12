package io.swagger.service;

import io.swagger.pojo.dao.Tag;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface WebTagService {
    void add(Tag tag,Long createBy)throws Exception;
    Integer findIfUsed(@RequestBody Tag tag);
    void delete(Long id)throws Exception;
    List<Tag> select(Integer pageNumber, Integer pageSize)throws Exception;
    void update(Tag tag,Long updateBy)throws Exception;
}
