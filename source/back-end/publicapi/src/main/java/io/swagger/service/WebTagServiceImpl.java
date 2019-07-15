package io.swagger.service;

import io.swagger.model.Pagination;
import io.swagger.pojo.dao.Tag;
import io.swagger.pojo.dao.repos.PaperTagRepository;
import io.swagger.pojo.dao.repos.ProblemTagRepository;
import io.swagger.pojo.dao.repos.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebTagServiceImpl extends BasicService<Tag> implements WebTagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProblemTagRepository problemTagRepository;

    @Autowired
    private PaperTagRepository paperTagRepository;

    @Autowired
    private WebProblemTagServiceImpl webProblemTagServiceImpl;

    @Autowired
    private WebPaperTagServiceImpl webPaperTagServiceImpl;

    /**
     * 增加新标签
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Tag add(Tag tag, Long createBy) throws Exception {

        //判断输入的新标签是否有效
        if (tag.getValue() == null || tag.getValue().equals("")) {
            throw new Exception("新标签没有标签值！");
        }

        //判断输入的新标签是否已存在
        if (tagRepository.findByValueEquals(tag.getValue()).size() > 0) {
            throw new Exception("新标签的标签值已存在！");
        }

        super.beforeAdd(tag, createBy);
        return tagRepository.save(tag);
    }

    /**
     * 查看该标签是否已被用
     *
     * @return 0:未被使用
     * 1：已被问题或试卷使用
     */
    public Integer findIfUsed(@RequestBody Tag tag) {
        if (paperTagRepository.findAllByTagIdEquals(tag.getId()).size()==0
                && problemTagRepository.findAllByTagIdEquals(tag.getId()).size()==0) {
            return 0;
        } else
            return 1;
    }

    /**
     * 删除标签
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws Exception {

        //删除标签本身
        this.deleteBasicInfo(id);

        //删除标签和问题的关联
        webProblemTagServiceImpl.deleteByTagId(id);

        //删除标签和试卷的关联
        webPaperTagServiceImpl.deleteByTagId(id);
    }

    @Override
    public int deleteBasicInfo(Long id) {
        return tagRepository.updateIsDelById(id, Boolean.TRUE);
    }

    /**
     * 查找出所有标签
     */
    @Override
    public Map<String, Object> list(Integer pageNumber, Integer pageSize) {

        Page<Tag> page = tagRepository.findAllByIsDel(PageRequest.of(pageNumber, pageSize), Boolean.FALSE);

        //标签信息
        List<Tag> tagList = new ArrayList<>(page.getContent());

        //分页信息
        Pagination pagination = new Pagination();
        pagination.setPage(BigDecimal.valueOf(page.getNumber()));
        pagination.setSize(BigDecimal.valueOf(page.getSize()));
        pagination.setTotal(BigDecimal.valueOf(page.getTotalPages()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("tagList", tagList);
        resultMap.put("pagination", pagination);

        return resultMap;
    }

    /**
     * 更改标签值
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Tag tag, Long updateBy) throws Exception {

        if (tag.getId() == null) {
            throw new Exception("标签id不能为空");
        }

        //判断传入参数是否正确
        if (tagRepository.findByValueEquals(tag.getValue()).size() > 0) {
            throw new Exception("新标签值与更改前的标签值相同！");
        }

        Tag dbTag = tagRepository.findTagById(tag.getId());
        dbTag.setValue(tag.getValue());
        dbTag.setParentId(tag.getParentId());
        beforeUpdate(dbTag, updateBy);
        tagRepository.save(dbTag);
    }
}
