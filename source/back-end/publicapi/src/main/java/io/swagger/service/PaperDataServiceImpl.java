package io.swagger.service;

import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;
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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaperDataServiceImpl implements PaperDataService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PaperItemRepository paperItemRepository;

    @Autowired
    private ProblemDataServiceImpl problemDataServiceImpl;

    /**
     * 获取对应id的试卷信息
     *
     * @param paperIds
     * @param isDeep   是否需要详细的问题信息
     * @return
     */
    @Override
    public List<PaperFullData> getFullDataByIds(List<Long> paperIds, boolean isDeep) {

        if (paperIds.size() <= 0) {
            return null;
        }

        /**
         * 查询试卷基本信息
         */
        Map<Long, Paper> paperIdPaperMap = getPaperIdPaperMap(paperIds);

        /**
         * 查询试卷标签
         */
        Map<Long, List<Tag>> paperIdTagMap = getPaperIdTagMap(paperIds);

        /**
         * 查询试卷题目id(包含序列)
         */
        Map<Long, Map<Integer, Long>> paperIdSerialProblemIdMap = getPaperIdSerialProblemIdMap(paperIds);

        /**
         * 查询试卷题目具体信息
         */
        Map<Long, List<ProblemFullData>> paperIdProblemFullDataMap = null;
        if (isDeep) {
            paperIdProblemFullDataMap = getPaperIdProblemFullDataMap(paperIdSerialProblemIdMap);
        }

        List<PaperFullData> paperFullDataList = new ArrayList<>();

        for (Long paperId : paperIds) {
            PaperFullData paperFullData = new PaperFullData();

            paperFullData.setDeep(isDeep);
            paperFullData.setTags(paperIdTagMap.get(paperId));
            paperFullData.setPaper(paperIdPaperMap.get(paperId));
            paperFullData.setSerialProblemIdMap(paperIdSerialProblemIdMap.get(paperId));

            if (isDeep) {
                paperFullData.setProblems(paperIdProblemFullDataMap.get(paperId));
            }

            paperFullDataList.add(paperFullData);
        }

        return paperFullDataList;
    }


    /**
     * 根据试卷id返回试卷id和试卷的map
     *
     * @param paperIds 一系列试卷id
     * @return 试卷id和试卷的map
     */
    public Map<Long, Paper> getPaperIdPaperMap(List<Long> paperIds) {
        List<Paper> paperList = paperRepository.findAllByIdIn(paperIds);
        Map<Long, Paper> paperIdPaperMap = new HashMap<>();

        for (Paper paper : paperList) {
            paperIdPaperMap.put(paper.getId(), paper);
        }

        return paperIdPaperMap;
    }

    /**
     * 根据试卷id返回试卷id和标签的map
     *
     * @param paperIds 一系列试卷id
     * @return 试卷id和标签的map
     */
    public Map<Long, List<Tag>> getPaperIdTagMap(List<Long> paperIds) {
        //所有试卷标签
        List<PaperTag> paperTagList = paperTagRepository.findAllByPaperIdIn(paperIds);
        //所有标签id(用来查询所有标签)
        List<Long> tagIdList = new ArrayList<>();
        //试卷id和标签id的map
        Map<Long, List<Long>> paperIdTagIdMap = new HashMap<>();
        //试卷id和标签的map
        Map<Long, List<Tag>> paperIdTagMap = new HashMap<>();

        for (PaperTag paperTag : paperTagList) {
            //循环获取所有标签id
            tagIdList.add(paperTag.getTagId());

            //设置试卷id和标签id的map
            if (paperIdTagIdMap.containsKey(paperTag.getPaperId())) {
                List<Long> paperTagIdList = paperIdTagIdMap.get(paperTag.getPaperId());
                paperTagIdList.add(paperTag.getTagId());
            } else {
                //为每一个试卷创建存储标签id的list
                List<Long> paperTagIdList = new ArrayList<>();
                paperTagIdList.add(paperTag.getTagId());
                paperIdTagIdMap.put(paperTag.getPaperId(), paperTagIdList);
            }
        }

        //根据所有标签id查询所有标签
        List<Tag> tagList = tagRepository.findAllByIdIn(tagIdList);
        //设置标签id和标签的map
        Map<Long, Tag> tagIdTagMap = new HashMap<>();
        for (Tag tag : tagList) {
            tagIdTagMap.put(tag.getId(), tag);
        }

        //设置问题id和标签的map
        for (Long paperId : paperIdTagIdMap.keySet()) {
            //存储指定问题的标签
            List<Tag> tagsList = new ArrayList<>();
            //指定问题的标签id
            List<Long> tagsIdList = paperIdTagIdMap.get(paperId);

            //将指定问题的标签id->指定问题的标签
            for (Long tagId : tagsIdList) {
                Tag tag = tagIdTagMap.get(tagId);

                if (tag != null) {
                    tagsList.add(tag);
                }
            }

            //设置问题id和标签的map
            paperIdTagMap.put(paperId, tagsList);
        }

        return paperIdTagMap;
    }


    /**
     * 根据试卷id返回试卷id和问题id(包括序列)的map
     *
     * @param paperIds 一系列试卷id
     * @return 试卷id和问题id(包括序列)的map
     */
    public Map<Long, Map<Integer, Long>> getPaperIdSerialProblemIdMap(List<Long> paperIds) {
        List<PaperItem> paperItemList = paperItemRepository.findAllByPaperIdIn(paperIds);
        Map<Long, Map<Integer, Long>> paperIdSerialProblemIdMap = new HashMap<>();

        for (PaperItem paperItem : paperItemList) {
            if (paperIdSerialProblemIdMap.containsKey(paperItem.getPaperId())) {
                Map<Integer, Long> serialProblemIdMap = paperIdSerialProblemIdMap.get(paperItem.getPaperId());
                serialProblemIdMap.put(paperItem.getSerial(), paperItem.getProblemId());
            } else {
                Map<Integer, Long> serialProblemIdMap = new HashMap<>();
                serialProblemIdMap.put(paperItem.getSerial(), paperItem.getProblemId());
                paperIdSerialProblemIdMap.put(paperItem.getPaperId(), serialProblemIdMap);
            }
        }

        return paperIdSerialProblemIdMap;
    }


    /**
     * 根据试卷id和问题id(包括序列)的map返回试卷id和问题具体信息的map
     *
     * @param paperIdSerialProblemIdMap 试卷id和问题id(包括序列)的map
     * @return 试卷id和问题具体信息的map
     */
    public Map<Long, List<ProblemFullData>> getPaperIdProblemFullDataMap(Map<Long, Map<Integer, Long>> paperIdSerialProblemIdMap) {

        //所有问题的id
        Set<Long> problemIdSet = new HashSet<>();

        //循环获取所有问题的id
        for (Long paperId : paperIdSerialProblemIdMap.keySet()) {
            Map<Integer, Long> serialProblemIdMap = paperIdSerialProblemIdMap.get(paperId);

            for (Integer serial : serialProblemIdMap.keySet()) {
                Long problemId = serialProblemIdMap.get(serial);

                if (!problemIdSet.contains(problemId)) {
                    problemIdSet.add(problemId);
                }
            }
        }

        //所有问题的id
        List<Long> problemIdList = new ArrayList<>(problemIdSet);

        //所有问题的具体信息
        List<ProblemFullData> problemFullDataList = problemDataServiceImpl.getFullDataByIds(problemIdList);
        Map<Long, ProblemFullData> problemIdProblemFullDataMap = new HashMap<>();

        //问题id和问题具体信息的map
        for (ProblemFullData problemFullData : problemFullDataList) {
            problemIdProblemFullDataMap.put(problemFullData.getProblem().getId(), problemFullData);
        }

        Map<Long, List<ProblemFullData>> paperIdProblemFullDataMap = new HashMap<>();

        //设置试卷id和问题具体信息的map
        for (Long paperId : paperIdSerialProblemIdMap.keySet()) {
            Map<Integer, Long> serialProblemIdMap = paperIdSerialProblemIdMap.get(paperId);

            List<ProblemFullData> problemFullDataList1 = new ArrayList<>();

            for (Integer serial : serialProblemIdMap.keySet()) {
                Long problemId = serialProblemIdMap.get(serial);
                problemFullDataList1.add(problemIdProblemFullDataMap.get(problemId));
            }

            paperIdProblemFullDataMap.put(paperId, problemFullDataList1);
        }

        return paperIdProblemFullDataMap;
    }
}
