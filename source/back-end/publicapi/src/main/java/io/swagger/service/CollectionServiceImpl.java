package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.ProblemFullData;
import io.swagger.utils.Parser;
import io.swagger.utils.ParserErrorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private Parser parser;
    @Override
    public List<PaperFullData> queryProblem(Expression expression) throws ParserErrorException {
        return parser.getAllPapersByExpression(expression);
    }

}
