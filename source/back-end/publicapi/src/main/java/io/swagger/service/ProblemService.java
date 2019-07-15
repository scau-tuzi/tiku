package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.model.ProblemInfo;
import io.swagger.model.QuerryInfo;
import io.swagger.pojo.ProblemFullData;
import io.swagger.utils.ParserErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * 外部api的问题服务
 */
public interface ProblemService {
    List<ProblemFullData> queryProblem(QuerryInfo querryInfo) throws ParserErrorException;
    ArrayList<Long> addProblemByProblemInfo(ProblemInfo problemInfo) throws Exception;
    void deleteById(String id);
}
