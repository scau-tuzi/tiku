package io.swagger.service;

import io.swagger.model.ProblemInfo;
import io.swagger.model.QuerryInfo;
import io.swagger.model.QuerryResult;
import io.swagger.utils.ParserErrorException;

import java.util.ArrayList;

/**
 * 外部api的问题服务
 */
public interface ProblemService {
    QuerryResult queryProblem(QuerryInfo querryInfo) throws ParserErrorException;
    ArrayList<Long> addProblemByProblemInfo(ProblemInfo problemInfo) throws Exception;
    void deleteById(String id);
}
