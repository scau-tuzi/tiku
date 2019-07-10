package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.model.Expression;
import io.swagger.utils.Parser;
import io.swagger.utils.ParserErrorException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProblemServiceImpl implements ProblemService {
    @Override
    public List<ProblemFullData> queryProblem(Expression expression) throws ParserErrorException {
        return Parser.getAllProblemsByExpression(expression);
    }
}
