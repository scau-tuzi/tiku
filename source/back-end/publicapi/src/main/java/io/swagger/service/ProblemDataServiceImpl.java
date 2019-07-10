package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProblemDataServiceImpl implements ProblemDataService {

    private final ProblemRepository problemRepository;

    private final StatusRepository statusRepository;

    private final ExtDataRepository extDataRepository;

    private final ProblemTagRepository problemTagRepository;

    private final TagRepository tagRepository;

    private final AnswerRepository answerRepository;

    public ProblemDataServiceImpl(ProblemRepository problemRepository, StatusRepository statusRepository, ExtDataRepository extDataRepository, ProblemTagRepository problemTagRepository, TagRepository tagRepository, AnswerRepository answerRepository) {
        this.problemRepository = problemRepository;
        this.statusRepository = statusRepository;
        this.extDataRepository = extDataRepository;
        this.problemTagRepository = problemTagRepository;
        this.tagRepository = tagRepository;
        this.answerRepository = answerRepository;
    }


    /**
     * 获取对应id的问题信息(暂时未实现：1.多级问题处理 2.多级标签处理)
     *
     * @param problemIds
     * @return
     */
    @Override
    public List<ProblemFullData> getFullDataByIds(List<Long> problemIds) {

        if (problemIds.size() <= 0) {
            return new ArrayList<>();
        }

        List<ProblemFullData> problemFullDataList = new ArrayList<>();

        // 查询问题基本信息map
        Map<Long, Problem> problemIdProblemMap = getProblemIdProblemMap(problemIds);

        // 查询问题答案map
        Map<Long, Answer> problemIdAnswerMap = getProblemIdAnswerMap(problemIdProblemMap);

        // 查询问题状态map
        Map<Long, Status> problemIdStatusMap = getProblemIdStatusMap(problemIds);

        // 查询问题扩展信息map
        Map<Long, Map<String, String>> problemIdExtDataMap = getProblemIdExtDataMap(problemIds);

        // 查询问题标签map
        Map<Long, List<Tag>> problemIdTagMap = getProblemIdTagMap(problemIds);

        //设置每个问题的所有属性
        for (Long problemId : problemIds) {
            ProblemFullData problemFullData = new ProblemFullData();

            problemFullData.setProblem(problemIdProblemMap.get(problemId));
            problemFullData.setAnswer(problemIdAnswerMap.get(problemId));
            problemFullData.setStatus(problemIdStatusMap.get(problemId));
            problemFullData.setExtData(problemIdExtDataMap.get(problemId));
            problemFullData.setTags(problemIdTagMap.get(problemId));
            problemFullDataList.add(problemFullData);
        }

        return problemFullDataList;
    }

    /**
     * 根据问题id返回问题id和问题的map
     *
     * @param problemIds 一系列问题id
     * @return 问题id和问题的map
     */
    public Map<Long, Problem> getProblemIdProblemMap(List<Long> problemIds) {
        Map<Long, Problem> problemIdProblemMap = new HashMap<>();
        List<Problem> problemList = problemRepository.findAllByIdIn(problemIds);

        for (Problem problem : problemList) {
            problemIdProblemMap.put(problem.getId(), problem);
        }

        return problemIdProblemMap;
    }


    /**
     * 根据问题id返回问题id和状态的map
     *
     * @param problemIds 一系列问题id
     * @return 问题id和状态的map
     */
    public Map<Long, Status> getProblemIdStatusMap(List<Long> problemIds) {

        List<Status> statusList = statusRepository.findAllByProblemIdIn(problemIds);
        Map<Long, Status> problemIdStatusMap = new HashMap<>();

        for (Status status : statusList) {
            problemIdStatusMap.put(status.getProblemId(), status);
        }

        return problemIdStatusMap;
    }

    /**
     * 根据问题id返回问题id和扩展信息的map
     *
     * @param problemIds 一系列问题id
     * @return 问题id和扩展信息的map
     */
    public Map<Long, Map<String, String>> getProblemIdExtDataMap(List<Long> problemIds) {
        List<ExtData> extDataList = extDataRepository.findAllByProblemIdIn(problemIds);
        Map<Long, Map<String, String>> problemIdExtDataMap = new HashMap<>();

        for (ExtData extData : extDataList) {
            if (problemIdExtDataMap.containsKey(extData.getProblemId())) {
                Map<String, String> extDataMap = problemIdExtDataMap.get(extData.getProblemId());
                extDataMap.put(extData.getKey(), extData.getValue());
            } else {
                //为每一个问题创建存储扩展信息的map
                Map<String, String> extDataMap = new HashMap<>();
                extDataMap.put(extData.getKey(), extData.getValue());
                problemIdExtDataMap.put(extData.getProblemId(), extDataMap);
            }
        }

        return problemIdExtDataMap;
    }


    /**
     * 根据问题id返回问题id和标签的map
     *
     * @param problemIds 一系列问题id
     * @return 问题id和标签的map
     */
    public Map<Long, List<Tag>> getProblemIdTagMap(List<Long> problemIds) {
        // 所有问题标签
        List<ProblemTag> problemTagList = problemTagRepository.findAllByProblemIdIn(problemIds);
        // 所有标签id(用来查询所有标签)
        List<Long> tagIdList = new ArrayList<>();
        // 问题和标签id的map
        Map<Long, List<Long>> problemIdTagIdMap = new HashMap<>();
        // 问题和标签的map
        Map<Long, List<Tag>> problemIdTagMap = new HashMap<>();

        for (ProblemTag problemTag : problemTagList) {
            // 获取所有标签id
            tagIdList.add(problemTag.getTagId());

            // 设置问题id和标签id的map
            if (problemIdTagIdMap.containsKey(problemTag.getProblemId())) {
                List<Long> problemTagIdList = problemIdTagIdMap.get(problemTag.getProblemId());
                problemTagIdList.add(problemTag.getProblemId());
            } else {
                // 为每一个问题创建存储标签id的list
                List<Long> problemTagIdList = new ArrayList<>();
                problemTagIdList.add(problemTag.getTagId());
                problemIdTagIdMap.put(problemTag.getProblemId(), problemTagIdList);
            }
        }

        // 根据所有标签id查询所有标签
        List<Tag> tagList = tagRepository.findAllByIdIn(tagIdList);
        // 设置标签id和标签的map
        Map<Long, Tag> IdTagMap = new HashMap<>();
        for (Tag tag : tagList) {
            IdTagMap.put(tag.getId(), tag);
        }

        // 设置问题和标签的map
        for (Long problemId : problemIdTagIdMap.keySet()) {
            // 指定问题的标签id
            List<Long> tagsIdList = problemIdTagIdMap.get(problemId);
            // 存储指定问题的标签
            List<Tag> tagsList = new ArrayList<>();

            //将指定问题的标签id->指定问题的标签
            for (Long tagId : tagsIdList) {
                Tag tag = IdTagMap.get(tagId);

                if (tag != null) {
                    tagsList.add(tag);
                }
            }

            // 设置问题和标签的map
            problemIdTagMap.put(problemId, tagsList);
        }

        return problemIdTagMap;
    }

    /**
     * 根据问题id和问题的map返回问题id和答案的map
     *
     * @param problemIdProblemMap 问题id和问题的map
     * @return 问题id和答案的map
     */
    public Map<Long, Answer> getProblemIdAnswerMap(Map<Long, Problem> problemIdProblemMap) {
        Map<Long, Answer> problemIdAnswerMap = new HashMap<>();
        List<Long> answerIdList = new ArrayList<>();

        //获取问题答案id
        for (Problem problem : problemIdProblemMap.values()) {
            answerIdList.add(problem.getAnswerId());
        }

        List<Answer> answerList = answerRepository.findAllByIdIn(answerIdList);
        Map<Long, Answer> answerIdAnswerMap = new HashMap<>();

        //设置答案id和答案的map
        for (Answer answer : answerList) {
            answerIdAnswerMap.put(answer.getId(), answer);
        }

        //设置问题id和答案的map
        for (Problem problem : problemIdProblemMap.values()) {
            Answer answer = answerIdAnswerMap.get(problem.getAnswerId());

            if (answer != null) {
                problemIdAnswerMap.put(problem.getId(), answer);
            }
        }

        return problemIdAnswerMap;
    }
}
