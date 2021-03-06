package io.swagger.pojo.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 问题描述：问题-标签实体(多对多)-复合主键类
 *
 * @作者：黄宽波
 * @时间：2019-07-09
 */
@Data
public class ProblemTagPK implements Serializable {

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 标签id
     */
    private Long tagId;

}
















