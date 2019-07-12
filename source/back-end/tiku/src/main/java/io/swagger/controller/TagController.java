package io.swagger.controller;

import io.swagger.pojo.dao.Tag;
import io.swagger.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 增加标签
     */
    @PostMapping("/add")
    public void add(@RequestBody Tag tag){
        try {
            tagService.add(tag);
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * 删除一个标签
     */
    @DeleteMapping("/delete")
    public void delete( Long id){
        try {
            tagService.delete(id);
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * 查找所有标签
     */
    @PostMapping("/select")
    public List<Tag> select(Integer pageNumber, Integer pageSize){
       try {
           return tagService.select(pageNumber, pageSize);
       }catch (Exception e){
           e.getMessage();
       }
       return null;
    }

    /**
     * 更改标签值
     */
    @PutMapping("/update")
    public void update(@RequestBody Tag tag){
       try{
           tagService.update(tag);
       }catch (Exception e){
           e.getMessage();
       }

    }


}
