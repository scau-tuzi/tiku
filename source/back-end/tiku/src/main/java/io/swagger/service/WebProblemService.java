package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;

import java.util.List;

public interface WebProblemService {

    List<ProblemFullData> getAll(Integer pageNumber, Integer pageSize);
}
