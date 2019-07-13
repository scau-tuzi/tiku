package io.swagger.service;

import io.swagger.pojo.dao.Paper;
import io.swagger.pojo.dao.PaperTag;
import io.swagger.pojo.dao.repos.PaperTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPaperTagServiceImpl extends BasicService<PaperTag> implements WebPaperTagService{
    @Autowired
    private PaperTagRepository paperTagRepository;


    @Override
    public int deleteByTagId(Long tagId) {
        return paperTagRepository.updateIsDelByTagId(tagId, Boolean.TRUE);
    }
}
