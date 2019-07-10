package cn.czfshine.tiku.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 问题描述：问题-标签实体(多对多)
 *
 * @作者：黄宽波
 * @时间：2019-07-09
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "problem_tag")
@IdClass(ProblemTagPK.class)
public class ProblemTag extends BasicEntity{

    /**
     * 问题id
     */
    @Id
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 标签id
     */
    @Id
    @Column(name = "tag_id")
    private Long tagId;

}
















