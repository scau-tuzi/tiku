package io.swagger.controller;

import io.swagger.InitData;
import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.ProblemService;
import io.swagger.service.ProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param pageNumber 当前页
     * @param pageSize   分页大小
     * @return
     */
    @GetMapping("/list")
    public BasicResponse list(Integer pageNumber, Integer pageSize) {

        List<ProblemFullData> allProblemFullData = problemServiceImpl.getAll(pageNumber, pageSize);

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setData(allProblemFullData);

        return basicResponse;
    }

    @PostMapping("add")
    public BasicResponse add(@RequestBody List<ProblemFullData> problemFullDataList) {

        System.out.println("-----------------------------------------------------");
        System.out.println(problemFullDataList);
        System.out.println("-----------------------------------------------------");

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setData(problemFullDataList);

        return basicResponse;
    }


    @Autowired
    private InitData initData;

    /**
     * 初始化数据
     * @return
     */
    @GetMapping("init")
    public BasicResponse init() {
        initData.before();

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setData("初始化成功");

        return basicResponse;
    }

}
