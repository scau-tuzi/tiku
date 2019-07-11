package io.swagger.service;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;

import java.util.List;

public interface ProblemService {

    List<ProblemFullData> getAll(Integer pageNumber, Integer pageSize);
}
