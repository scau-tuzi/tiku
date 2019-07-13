package io.swagger.controller;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebProblemService;
import io.swagger.service.WebProblemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * @param isCheck    是否通过审核
     * @return
     */
    @GetMapping("/list")
    public BasicResponse list(@RequestParam Integer pageNumber, Integer pageSize, Integer isCheck) {

        BasicResponse basicResponse = new BasicResponse();

        pageNumber = (pageNumber < 0 ? 0 : pageNumber);
        pageSize = (pageSize < 1 || pageSize > 100 ? 100 : pageSize);
        isCheck = (isCheck == null ? Status.CHECK : isCheck);

        /**
         * 定义一个页对象?
         */

        try {
            Map<String, Object> resultMap = webProblemService.getAll(pageNumber, pageSize, isCheck, Boolean.FALSE);
            basicResponse.setData(resultMap);
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("Query error!");
        }


        return basicResponse;
    }

    /**
     * 新增题目
     *
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


    /**
     * 删除题目
     *
     * @param id 题目id
     * @return
     */
    @DeleteMapping("/delete")
    public BasicResponse delete(@RequestParam Long id) {
        BasicResponse basicResponse = new BasicResponse();

        try {
            webProblemServiceImpl.delete(id);
            basicResponse.setData("问题删除成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题删除失败：" + e.getMessage());
        }

        return basicResponse;
    }

    /**
     * 修改题目
     *
     * @param problemFullData
     * @return
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody ProblemFullData problemFullData) {

        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {
            webProblemServiceImpl.update(problemFullData, updateBy);
            basicResponse.setData("问题修改成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题修改失败：" + e.getMessage());
        }

        return basicResponse;
    }
}
