package io.swagger.service;

import io.swagger.model.Expression;
import io.swagger.pojo.PaperFullData;
import io.swagger.utils.ParserErrorException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CollectionService {
    List<PaperFullData> queryCollection(Expression expressione,boolean isDeep) throws ParserErrorException;
}
