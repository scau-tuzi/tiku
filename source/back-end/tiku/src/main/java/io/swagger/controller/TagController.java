package io.swagger.controller;

import io.swagger.pojo.dao.Tag;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.WebTagServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/test")
@RestController
public class TagController {

    @Autowired
    private WebTagServiceImpl tagService;



    /**
     * 增加标签
     */
    @PostMapping("/add")
    public BasicResponse add(@RequestBody @RequestParam Tag tag){
        BasicResponse basicResponse = new BasicResponse();

        //判断输入的新标签是否有效
        Long createBy = 1L;
        try {
            tagService.add(tag,createBy);
            basicResponse.setData("标签添加成功");
        }catch (Exception e){
            basicResponse.setCode(BasicResponse.ERRORCODE);
            basicResponse.setData("标签添加失败: " + e.getMessage());
        }

        return basicResponse;
    }

    /**
     * 删除一个标签
     */
    @DeleteMapping("/delete")
    public BasicResponse delete(@RequestParam Long id){
        BasicResponse basicResponse = new BasicResponse();

        try {
            tagService.delete(id);
            basicResponse.setData("标签删除成功");
        }catch (Exception e){
            basicResponse.setData("标签删除失败");
            basicResponse.setCode(BasicResponse.ERRORCODE);
        }

        return basicResponse;
    }

    /**
     * 查找所有标签
     */
    @PostMapping("/select")
    public BasicResponse select(Integer pageNumber, Integer pageSize){
        BasicResponse basicResponse = new BasicResponse();
       try {
           basicResponse.setData("查找标签成功"+tagService.select(pageNumber, pageSize));


       }catch (Exception e){
           basicResponse.setCode(BasicResponse.ERRORCODE);
           basicResponse.setData("查找标签失败: " + e.getMessage());
           e.getMessage();
       }
       return basicResponse;
    }

    /**
     * 更改标签值
     */
    @PutMapping("/update")
    public BasicResponse update(@RequestBody Tag tag,@RequestBody Long updateBy){
        BasicResponse basicResponse = new BasicResponse();
       try{

           tagService.update(tag,updateBy);
           basicResponse.setData("更改标签成功");
       }catch (Exception e){
           basicResponse.setCode(BasicResponse.ERRORCODE);
           basicResponse.setData("更改标签失败: " + e.getMessage());

       }

       return basicResponse;
    }


}
