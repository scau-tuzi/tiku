package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.pojo.PaperFullData;
import io.swagger.utils.Parser;
import io.swagger.utils.ParserErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private Parser parser;

    @Override
    public List<PaperFullData> queryCollection(Expression expression,boolean isDeep) throws ParserErrorException {
         return parser.getAllPapersByExpression(expression,isDeep);

    }
}
