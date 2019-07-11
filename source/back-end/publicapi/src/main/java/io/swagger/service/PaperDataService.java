package io.swagger.service;


import io.swagger.pojo.PaperFullData;

import java.util.List;

public interface PaperDataService {

    List<PaperFullData> getFullDataByIds(List<Long> paperIds, boolean isDeep);
}
