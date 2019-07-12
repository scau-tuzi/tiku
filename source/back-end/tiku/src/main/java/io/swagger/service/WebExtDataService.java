package io.swagger.service;

import io.swagger.pojo.dao.ExtData;

import java.util.List;

public interface WebExtDataService {

    List<ExtData> addAll(List<ExtData> extDataList, Long createBy);
}
