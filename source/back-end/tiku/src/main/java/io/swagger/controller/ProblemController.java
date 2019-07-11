package io.swagger.controller;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.ProblemService;
import io.swagger.service.ProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/problem")
@RestController
@Slf4j
public class ProblemController {

    @Autowired
    private ProblemServiceImpl problemServiceImpl;

    // todo 不知道这样写swagger能不能自动生成正确的接口

    /**
     * 分页返回问题的具体信息列表
     * @param pageNumber 当前页
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping("/list")
    public BasicResponse list(Integer pageNumber, Integer pageSize) {

        List<ProblemFullData> allProblemFullData = problemServiceImpl.getAll(pageNumber, pageSize);

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setData(allProblemFullData);

        return basicResponse;
    }

}
