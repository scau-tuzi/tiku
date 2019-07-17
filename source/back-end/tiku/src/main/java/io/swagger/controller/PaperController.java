package io.swagger.controller;

import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebPaperService;
import io.swagger.service.WebProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/paper")
@RestController
@Slf4j
public class PaperController {

    @Autowired
    private WebPaperService webPaperService;

    /**
     * 分页返回试卷的具体信息列表
     *
     * @param pageNumber 当前页
     * @param pageSize   分页大小
     * @param isDeep     是否需要试卷包含的题目的详细信息
     * @return
     */
    @GetMapping("/list")
    public BasicResponse list(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, Boolean isDeep) {
        BasicResponse basicResponse = new BasicResponse();

        pageNumber = (pageNumber < 0 ? 0 : pageNumber);
        pageSize = (pageSize < 1 || pageSize > 100 ? 100 : pageSize);

        try {
            Map<String, Object> resultMap = webPaperService.getAll(pageNumber, pageSize, isDeep, Boolean.FALSE);
            basicResponse.setData(resultMap);
        } catch (Exception e) {
            basicResponse.setData("Query error!");
            basicResponse.setCode(BasicResponse.ERRORCODE);
        }

        return basicResponse;
    }

    /**
     * 新增试卷
     *
     * @param paperFullData
     * @return
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody PaperFullData paperFullData) {
        BasicResponse basicResponse = new BasicResponse();

        Long createBy = 1L;
        try {
            webPaperService.add(paperFullData, createBy);
            basicResponse.setData("试卷添加成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("试卷添加失败：" + e.getMessage());
        }

        return basicResponse;
    }

    /**
     * 修改试卷
     *
     * @param paperFullData
     * @return
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody PaperFullData paperFullData) {
        BasicResponse basicResponse = new BasicResponse();

        Long updateBy = 1L;
        try {
            webPaperService.update(paperFullData, updateBy);
            basicResponse.setData("试卷修改成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("试卷修改失败：" + e.getMessage());
        }

        return basicResponse;
    }

    /**
     * 删除试卷
     *
     * @param idList
     * @return
     */
    @DeleteMapping("/delete")
    public BasicResponse delete(@RequestBody List<Long> idList) {
        BasicResponse basicResponse = new BasicResponse();

        try {
            webPaperService.deleteAll(idList);
            basicResponse.setData("试卷删除成功");
        } catch (Exception e) {
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("试卷删除失败：" + e.getMessage());
        }

        return basicResponse;
    }
}
