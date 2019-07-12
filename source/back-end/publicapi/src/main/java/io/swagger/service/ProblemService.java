package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.pojo.ProblemFullData;
import io.swagger.utils.ParserErrorException;

import java.util.List;

public interface ProblemService {
    List<ProblemFullData> queryProblem(Expression expressione) throws ParserErrorException;
}
