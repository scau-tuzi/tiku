package io.swagger.service;

import cn.czfshine.tiku.pojo.ProblemFullData;
import io.swagger.model.Expression;
import io.swagger.utils.Parser;

import java.util.List;

public class ProblemServiceImpl implements ProblemService {
    @Override
    public List<ProblemFullData> queryProblem(Expression expression) {
        return Parser.getAllProblemsByExpression(expression);
    }
}
