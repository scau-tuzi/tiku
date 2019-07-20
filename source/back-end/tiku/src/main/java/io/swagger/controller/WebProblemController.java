package io.swagger.controller;

import io.swagger.pojo.ProblemFullData;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/problem")
@RestController
@Slf4j
public class WebProblemController extends WebBasicController {

    @Autowired
    private WebProblemService webProblemService;


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
    public BasicResponse list(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, Integer isCheck) {
        log.info("username:{} userid:{}",getUsername(),getUserId());
        BasicResponse basicResponse = new BasicResponse();

        pageNumber = (pageNumber < 0 ? 0 : pageNumber);
        pageSize = (pageSize < 1 || pageSize > 100 ? 100 : pageSize);
        isCheck = (isCheck == null ? Status.CHECK : isCheck);

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
     * 根据标签进行搜索：分页返回问题的具体信息列表
     *
     * @return
     */
    @PostMapping("/query")
    public BasicResponse query(@RequestBody Map<String, Object> paramMap) {

        BasicResponse basicResponse = new BasicResponse();

        try {
            String type = (String) paramMap.get("type");
            Integer pageNumber = (Integer) paramMap.get("pageNumber");
            Integer pageSize = (Integer) paramMap.get("pageSize");
            Integer isCheck = (Integer) paramMap.get("isCheck");
            List<Long> tagIdList = (List<Long>) paramMap.get("tagIdList");

            pageNumber = (pageNumber < 0 ? 0 : pageNumber);
            pageSize = (pageSize < 1 || pageSize > 100 ? 100 : pageSize);
            isCheck = (isCheck == null ? Status.CHECK : isCheck);

            if (type.equals("tag")) {
                Map<String, Object> resultMap = webProblemService.getAllByTagIdList(tagIdList, pageNumber, pageSize, isCheck);
                basicResponse.setData(resultMap);
            } else {
                basicResponse.setCode(BasicResponse.ERRORCODE);
                basicResponse.setData("error query : type no found !");
            }
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("error query : " + e.getMessage());
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
            webProblemService.add(problemFullData, createBy);
            basicResponse.setData("问题添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题添加失败：" + e.getMessage());
        }

        return basicResponse;
    }


    /**
     * 删除题目 (暂时未处理：题目删除后，更改试卷包含的题目的序列)
     *
     * @param idList 题目id列表
     * @return
     */
    @PostMapping("/delete")
    public BasicResponse delete(@RequestBody List<Long> idList) {
        BasicResponse basicResponse = new BasicResponse();

        try {
            webProblemService.deleteAll(idList);
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
            webProblemService.update(problemFullData, updateBy);
            basicResponse.setData("问题修改成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题修改失败：" + e.getMessage());
        }

        return basicResponse;
    }

    /**
     * 审核题目
     *
     * @param id
     * @return
     */
    @PutMapping("/check")
    public BasicResponse check(@RequestParam Long id) {
        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {
            webProblemService.check(id, Status.CHECK, updateBy);
            basicResponse.setData("问题审核成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("问题审核失败：" + e.getMessage());
        }

        return basicResponse;
    }
}
