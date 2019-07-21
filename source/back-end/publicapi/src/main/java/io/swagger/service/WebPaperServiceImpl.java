package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Paper;
import io.swagger.pojo.dao.PaperItem;
import io.swagger.pojo.dao.PaperTag;
import io.swagger.pojo.dao.Tag;
import io.swagger.pojo.dao.repos.PaperItemRepository;
import io.swagger.pojo.dao.repos.PaperRepository;
import io.swagger.pojo.dao.repos.PaperTagRepository;
import io.swagger.pojo.dao.repos.TagRepository;
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
import java.util.stream.Collectors;

@Service
public class WebPaperServiceImpl extends BasicService<Paper> implements WebPaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperDataService paperDataService;

    @Autowired
    private WebPaperTagService webPaperTagService;

    @Autowired
    private WebPaperItemService webPaperItemService;

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private PaperItemRepository paperItemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WebTagService webTagService;

    @Override
    public Map<String, Object> getAll(Integer pageNumber, Integer pageSize, Boolean isDeep, Boolean isDel) {
        Map<String, Object> resultMap = new HashMap<>();

        //查找符合条件的试卷id
        Page<Object> page = paperRepository.findIdList(PageRequest.of(pageNumber, pageSize), isDel);

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        //将id存储进id列表
        List<Long> paperIdList = new ArrayList<>();
        for (Object id : page.getContent()) {
            paperIdList.add(Long.parseLong(id.toString()));
        }

        //试卷具体信息
        List<PaperFullData> paperFullDataList = paperDataService.getFullDataByIds(paperIdList, isDeep);

        resultMap.put("pagination", pagination);
        resultMap.put("paperFullDataList", paperFullDataList);

        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(PaperFullData paperFullData, Long createBy) throws Exception {

        Paper paper = paperFullData.getPaper();
        List<Tag> tagList = paperFullData.getTags();
        Map<Integer, Long> serialProblemIdMap = paperFullData.getSerialProblemIdMap();

        if (paper == null) {
            throw new Exception("Param error : paper should not be null");
        }

        /**
         * 新增试卷基本信息
         */
        paper = this.addBasicInfo(paper, createBy);

        /**
         * 新增试卷包含的题目
         */
        if (serialProblemIdMap != null && serialProblemIdMap.size() > 0) {
            List<PaperItem> paperItemList = new ArrayList<>();
            for (Integer serial : serialProblemIdMap.keySet()) {
                Long problemId = serialProblemIdMap.get(serial);
                PaperItem paperItem = new PaperItem();
                paperItem.setPaperId(paper.getId());
                paperItem.setSerial(serial);
                paperItem.setProblemId(problemId);
                paperItemList.add(paperItem);
            }
            webPaperItemService.addAll(paperItemList, createBy);
        }


        /**
         * 新增试卷标签
         */
        if (tagList != null && tagList.size() > 0) {
            List<Tag> ts = getTagValues(tagList);
            List<PaperTag> paperTagList = new ArrayList<>();
            for (Tag tag : ts) {
                PaperTag paperTag = new PaperTag();
                paperTag.setTagId(tag.getId());
                paperTag.setPaperId(paper.getId());
                paperTagList.add(paperTag);
            }
            webPaperTagService.addAll(paperTagList, createBy);
        }
        return paper.getId();
    }

    private List<Tag> getTagValues(List<Tag> tagList) {
        List<String> values = tagList.stream().map((t) -> t.getValue()).filter((ts) -> ts != null && ts.length() > 0).collect(Collectors.toList());
        return webTagService.getTagsByValueList(values);
    }

    @Override
    public Paper addBasicInfo(Paper paper, Long createBy) {
        super.beforeAdd(paper, createBy);
        return paperRepository.save(paper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PaperFullData paperFullData, Long updateBy) throws Exception {
        Paper paper = paperFullData.getPaper();
        List<Tag> tagList = paperFullData.getTags();
        Map<Integer, Long> serialProblemIdMap = paperFullData.getSerialProblemIdMap();

        if (paper == null || paper.getId() == null) {
            throw new Exception("Param error : paper should not be null And paperId should not be null");
        }

        /**
         * 修改试卷基本问题
         */
        paper = this.updateBasicInfo(paper, updateBy);

        /**
         * 修改试卷包含的题目
         */
        if (serialProblemIdMap != null && serialProblemIdMap.size() > 0) {
            //先删除已包含的题目
            paperItemRepository.deleteAllByPaperIdEquals(paper.getId());

            //再重新添加包含的题目信息
            List<PaperItem> paperItemList = new ArrayList<>();

            for (Integer serial : serialProblemIdMap.keySet()) {
                Long problemId = serialProblemIdMap.get(serial);
                PaperItem paperItem = new PaperItem();
                paperItem.setPaperId(paper.getId());
                paperItem.setProblemId(problemId);
                paperItem.setSerial(serial);
                paperItemList.add(paperItem);
            }

            webPaperItemService.addAll(paperItemList, updateBy);
        }


        /**
         * 修改试卷标签
         */
        if (tagList != null && tagList.size() > 0) {
            //先删除已关联的标签关系
            paperTagRepository.deleteAllByPaperIdEquals(paper.getId());
            List<Tag> ts = getTagValues(tagList);

            //再添加重新关联的标签信息
            List<PaperTag> paperTagList = new ArrayList<>();

            for (Tag tag : ts) {
                PaperTag paperTag = new PaperTag();
                paperTag.setPaperId(paper.getId());
                paperTag.setTagId(tag.getId());
                paperTagList.add(paperTag);
            }

            webPaperTagService.addAll(paperTagList, updateBy);
        }
    }

    @Override
    public Paper updateBasicInfo(Paper paper, Long updateBy) {
        Paper dbPaper = paperRepository.findById(paper.getId()).get();
        dbPaper.setTitle(paper.getTitle());
        super.beforeAdd(dbPaper, updateBy);
        return paperRepository.save(dbPaper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(List<Long> idList) {
        for (Long id : idList) {
            this.delete(id);
        }
    }

    @Override
    public void delete(Long id) {
        /**
         * 删除试卷基本信息
         */
        this.deleteBasicInfo(id);

        /**
         * 删除试卷标签
         */
        webPaperTagService.deleteByPaperId(id);

        /**
         * 删除试卷包含的题目
         */
        webPaperItemService.deleteByPaperId(id);
    }

    @Override
    public int deleteBasicInfo(Long id) {
        return paperRepository.updateIsDelById(id, Boolean.TRUE);
    }

    @Override
    public Map<String, Object> getAllByTagIdList(List<Long> tagIdList, Integer pageNumber, Integer pageSize, Boolean isDeep) {
        Map<String, Object> resultMap = new HashMap<>();

        //查找含有指定标签的问题id
        Page<Object> page = paperTagRepository.findPaperIdListByTagIdList(
                PageRequest.of(pageNumber, pageSize), tagIdList, Boolean.FALSE, tagIdList.size());

        //将id存储进id列表
        List<Long> paperIdList = new ArrayList<>();
        for (Object id : page.getContent()) {
            paperIdList.add(Long.parseLong(id.toString()));
        }

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        //问题具体信息
        List<PaperFullData> paperFullDataList = paperDataService.getFullDataByIds(paperIdList, isDeep);

        resultMap.put("pagination", pagination);
        resultMap.put("paperFullDataList", paperFullDataList);

        return resultMap;
    }

}

