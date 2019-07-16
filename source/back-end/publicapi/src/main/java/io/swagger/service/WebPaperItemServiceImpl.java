package io.swagger.service;

import io.swagger.pojo.dao.PaperItem;
import io.swagger.pojo.dao.repos.PaperItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebPaperItemServiceImpl extends BasicService<PaperItem> implements WebPaperItemService {

    @Autowired
    private PaperItemRepository paperItemRepository;

    @Override
    public int deleteByProblemId(Long problemId) {
        return paperItemRepository.updateIsDelByProblemId(problemId, Boolean.TRUE);
    }

    @Override
    public int deleteByPaperId(Long paperId) {
        return paperItemRepository.updateIsDelByPaperId(paperId, Boolean.TRUE);
    }

    @Override
    public List<PaperItem> addAll(List<PaperItem> paperItemList, Long createBy) {
        for (PaperItem paperItem : paperItemList) {
            super.beforeAdd(paperItem, createBy);
        }
        return paperItemRepository.saveAll(paperItemList);
    }
}
