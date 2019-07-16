package io.swagger.service;

import io.swagger.pojo.dao.PaperItem;

import java.util.List;

public interface WebPaperItemService {
    int deleteByProblemId(Long problemId);

    int deleteByPaperId(Long paperId);

    List<PaperItem> addAll(List<PaperItem> paperItemList, Long createBy);
}
