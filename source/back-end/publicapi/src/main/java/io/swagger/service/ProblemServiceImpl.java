package io.swagger.service;

import io.swagger.model.*;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Answer;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dao.Tag;
import io.swagger.utils.Parser;
import io.swagger.utils.ParserErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    public class ProblemServiceException extends Exception {
        public ProblemServiceException(String message) {
            super(message);
        }
    }

    @Autowired
    private Parser parser;
    @Autowired
    private WebProblemService webProblemService;
    @Autowired
    private ProblemDataService problemDataService;

    @Override
    public QuerryResult queryProblem(QuerryInfo querryInfo) throws ParserErrorException {
        List<Long> longs = parser.getAllProblemsByExpression(querryInfo);

        Boolean random = querryInfo.isRandom();
        if (random != null && random) {
            Collections.shuffle(longs);
        }

        Pagination resPagin = new Pagination();
        @Valid Pagination pagination = querryInfo.getPagination();
        if (pagination != null) {
            int page = pagination.getPage().intValue();
            int size = pagination.getSize().intValue();
            resPagin.setPage(BigDecimal.valueOf(-1));
            resPagin.setSize(BigDecimal.valueOf(size));
            resPagin.setTotal(BigDecimal.valueOf(longs.size()));
            // todo !!!!!!! 分页错误
            // total = 24 page=3 size=7 => 21-23
            //                = 4       error
            //                =4     =6 error
            if (page * size < longs.size()) {
                int to = (page + 1) * size;
                if (to > (longs.size() - 1)) {
                    to = longs.size() - 1;
                }
                longs = longs.subList(page * size, to+1);
                resPagin.setPage(BigDecimal.valueOf(page));
            } else {
                longs = new ArrayList<>();
            }
        }

        List<ProblemFullData> problemFullData = problemDataService.getFullDataByIds(longs);
        ArrayList<HashMap<String, Object>> res = new ArrayList<>();
        // 拼装数据和做下格式转换
        QuerryResult querryResult = new QuerryResult();
        querryResult.setStatus(StatusCode.OK);
        // 拼装返回数据
        for (ProblemFullData e : problemFullData) {
            res.add(e.toMap());
        }
        querryResult.setResults(res);
        querryResult.setPagination(resPagin);
        return querryResult;
    }

    private @NotNull String getRequireStringField(HashMap<String, Object> map, String fieldName) throws ProblemServiceException {
        Object text = map.getOrDefault(fieldName, null);
        if (text == null) {
            throw new ProblemServiceException("域[" + fieldName + "]不能为空");
        }
        if (!(text instanceof String)) {
            throw new ProblemServiceException("域[" + fieldName + "]必须是字符串");
        }
        return (String) text;

    }

    /**
     * 反序列化
     *
     * @param problemInfo
     * @return
     * @throws ProblemServiceException
     * @see io.swagger.pojo.ProblemFullData#toMap()
     */
    public List<ProblemFullData> problemInfoToProblemFullDatas(ProblemInfo problemInfo) throws ProblemServiceException {
        ArrayList<ProblemFullData> problemFullDatas = new ArrayList<>();

        @Valid List<HashMap<String, Object>> problems = problemInfo.getProblems();

        if (problems == null && problems.size() == 0) {
            throw new ProblemServiceException("必须传递题目具体信息");
        }

        for (HashMap<String, Object> p : problems) {
            ProblemFullData problemFullData = new ProblemFullData();
            //基本信息
            @NotNull String text = getRequireStringField(p, "text");
            @NotNull String answerText = getRequireStringField(p, "answer");
            Problem problem = new Problem();
            problem.setProblemText(text);
            problemFullData.setProblem(problem);

            Answer answer = new Answer();
            answer.setAnswerText(answerText);
            problemFullData.setAnswer(answer);

            Object tags = p.getOrDefault("tags", null);
            if (tags != null) {
                if (!(tags instanceof List)) {
                    throw new ProblemServiceException("tags必须为字符串数组");
                }
                List tagList = (List) tags;
                ArrayList<Tag> tagPojos = new ArrayList<>();
                for (Object o : tagList) {
                    if (o instanceof String) {
                        Tag tag = new Tag();
                        tag.setValue((String) o);
                        tagPojos.add(tag);
                    } else {
                        throw new ProblemServiceException("tags数组的元素必须是字符串");
                    }
                }
                problemFullData.setTags(tagPojos);
            }
            HashMap<String, String> extData = new HashMap<>();
            p.forEach((k, v) -> {
                if (!ProblemFullData.FIELDNAME.contains(k)) {
                    extData.put(k, v.toString());
                }
            });
            if (extData.size() > 0) {
                problemFullData.setExtData(extData);
            }
            problemFullDatas.add(problemFullData);
        }

        return problemFullDatas;
    }

    @Override
    public ArrayList<Long> addProblemByProblemInfo(ProblemInfo problemInfo) throws ProblemServiceException, Exception {
        List<ProblemFullData> problemFullData = problemInfoToProblemFullDatas(problemInfo);
        ArrayList<Long> res = new ArrayList<>();
        for (ProblemFullData p : problemFullData) {
            res.add(webProblemService.add(p, 0L));
        }
        return res;
    }

    @Override
    public void deleteById(String id) {
        webProblemService.delete(Long.valueOf(id));
    }
}
