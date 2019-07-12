package io.swagger.controller;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebProblemService;
import io.swagger.service.WebProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/problem")
@RestController
@Slf4j
public class ProblemController {

    @Autowired
    private WebProblemService webProblemService;

    @Autowired
    private WebProblemServiceImpl webProblemServiceImpl;

    // todo 不知道这样写swagger能不能自动生成正确的接口

    /**
     * 分页返回问题的具体信息列表
     *
     * @param pageNumber 当前页
     * @param pageSize   分页大小
     * @return
     */
    @GetMapping("/list")
    public BasicResponse list(@RequestParam Integer pageNumber, Integer pageSize) {

        BasicResponse basicResponse = new BasicResponse();

        if (pageNumber < 0 || pageSize < 1) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("Param error! pageNumber should >= 0 And pageSize should >= 1");
        } else {
            try {
                List<ProblemFullData> allProblemFullData = webProblemService.getAll(pageNumber, pageSize);
                basicResponse.setData(allProblemFullData);
            } catch (Exception e) {
                basicResponse.setCode(BasicResponse.ERRORCODE);
                basicResponse.setData("Query error!");
            }
        }

        return basicResponse;
    }

    /**
     * 新增题目
     * @param problemFullData
     * @return
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody ProblemFullData problemFullData) {

        BasicResponse basicResponse = new BasicResponse();

        Long createBy = 1L;
        try {
            webProblemServiceImpl.add(problemFullData, createBy);
            basicResponse.setData("问题添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题添加失败：" + e.getMessage());
        }

        return basicResponse;
    }

}
