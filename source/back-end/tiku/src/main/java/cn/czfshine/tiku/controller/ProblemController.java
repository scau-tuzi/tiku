package cn.czfshine.tiku.controller;

import cn.czfshine.tiku.pojo.dao.Problem;
import cn.czfshine.tiku.pojo.dao.repos.ProblemRepository;
import cn.czfshine.tiku.pojo.dto.BasicResponse;
import cn.czfshine.tiku.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
