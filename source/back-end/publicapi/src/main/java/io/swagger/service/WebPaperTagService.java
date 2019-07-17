package io.swagger.service;

import io.swagger.pojo.dao.PaperTag;

import java.util.List;

public interface WebPaperTagService {
    int deleteByTagId(Long tagId);

    int deleteByPaperId(Long paperId);

    List<PaperTag> addAll(List<PaperTag> paperTagList, Long createBy);
}
