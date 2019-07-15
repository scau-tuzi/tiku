package io.swagger.service;

import io.swagger.pojo.dao.Paper;
import io.swagger.pojo.dao.PaperTag;
import io.swagger.pojo.dao.repos.PaperTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebPaperTagServiceImpl extends BasicService<PaperTag> implements WebPaperTagService {

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Override
    public int deleteByTagId(Long tagId) {
        return paperTagRepository.updateIsDelByTagId(tagId, Boolean.TRUE);
    }

    @Override
    public int deleteByPaperId(Long paperId) {
        return paperTagRepository.updateIsDelByPaperId(paperId, Boolean.TRUE);
    }

    @Override
    public List<PaperTag> addAll(List<PaperTag> paperTagList, Long createBy) {
        for (PaperTag paperTag : paperTagList) {
            super.beforeAdd(paperTag, createBy);
        }

        return paperTagRepository.saveAll(paperTagList);
    }
}
