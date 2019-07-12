package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.pojo.PaperFullData;
import io.swagger.utils.ParserErrorException;

import java.util.List;

public interface CollectionService {
    List<PaperFullData> queryProblem(Expression expressione) throws ParserErrorException;
}
