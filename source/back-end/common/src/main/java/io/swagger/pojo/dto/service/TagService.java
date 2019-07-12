package io.swagger.pojo.dto.service;

import io.swagger.pojo.dao.*;
import io.swagger.pojo.dao.repos.PaperTagRepository;
import io.swagger.pojo.dao.repos.ProblemTagRepository;
import io.swagger.pojo.dao.repos.TagRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProblemTagRepository problemTagRepository;

    @Autowired
    private PaperTagRepository paperTagRepository;

    /**
     * 增加新标签
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Tag tag) throws Exception {

        //判断输入的新标签是否有效
        if (tag.getId().equals(null)) {
            throw new Exception("新标签没有id值！");
        } else if (tag.getValue().equals(null)) {
            throw new Exception("新标签没有标签值！");
        }

        //判断数据库中是否该标签
        if (!tagRepository.findByTagId(tag.getId()).equals(null)) {
            throw new Exception("该标签已存在！");
        }
        tagRepository.save(tag);
    }

    /**
     * 查看该标签是否已被用
     *
     * @return 0:未被使用
     * 1：已被问题或试卷使用
     */
    public Integer findIfUsed(@RequestBody Tag tag) {
        if (paperTagRepository.findAllByTagIdEquals(tag.getId()).equals(null)
                && problemTagRepository.findAllByTagIdEquals(tag.getId()).equals(null)) {
            return 0;
        } else
            return 1;
    }

    /**
     * 删除标签
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws Exception {

        //判断数据库中是否有该标签
        if (tagRepository.findByTagId(id).equals(null)) {
            throw new Exception("该标签不存在！");
        } else {

            //删除问题和试卷中使用的该标签
            List<PaperTag> paperTagList = paperTagRepository.findAllByTagIdEquals(id);
            List<Long> paperIdList=new ArrayList<>();
            for(PaperTag paperTag:paperTagList){
                paperIdList.add(paperTag.getPaperId());
            }
            paperTagRepository.deleteAllByPaperIdIn(paperIdList);

            List<ProblemTag> problemTagList = problemTagRepository.findAllByTagIdEquals(id);
            List<Long> problemIdList=new ArrayList<>();
            for(ProblemTag problemTag:problemTagList){
                problemIdList.add(problemTag.getProblemId());
            }
            problemTagRepository.deleteAllByProblemIdIn(problemIdList);

            tagRepository.deleteById(id);
        }
    }


    /**
     * 查找出所有标签
     */
    public List<Tag> select(Integer pageNumber, Integer pageSize) throws Exception {
        //判断传入参数是否正确
        if (pageNumber > 0 && pageSize > 0) {
            Page<Tag> page = tagRepository.findAll(PageRequest.of(pageNumber, pageSize));
            return page.getContent();
        } else {
            throw new Exception("页数或页大小参数小于1！");
        }
    }

    /**
     * 更改标签值
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Tag tag) throws Exception {

        //判断传入参数是否正确
        if (tagRepository.findByTagId(tag.getId()).equals(null)) {
            throw new Exception("该标签不存在！");
        } else if (tagRepository.findByTagId(tag.getId()).getValue().equals(tag.getValue())) {
            throw new Exception("新标签值与更改前的标签值相同！");
        } else {
            Tag newTag = tagRepository.findByTagId(tag.getId());
            BeanUtils.copyProperties(tag,newTag);
            tagRepository.save(newTag);

        }
    }
}
