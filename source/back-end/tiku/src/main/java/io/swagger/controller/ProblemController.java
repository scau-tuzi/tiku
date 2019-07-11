package io.swagger.controller;

import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/problem")
@RestController
@Slf4j
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    // todo 不知道这样写swagger能不能自动生成正确的接口
    @GetMapping("/list")
    public BasicResponse test(){

        List<Problem> all = problemService.getAll();

        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setData(all);

        return basicResponse;
    }

}
