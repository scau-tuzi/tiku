package cn.czfshine.tiku.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 功能描述：试卷项-实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "paper_item")
@IdClass(PaperItemPK.class)
public class PaperItem extends BasicEntity{

    /**
     * 问题id
     */
    @Id
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 试卷id
     */
    @Id
    @Column(name = "paper_id")
    private Long paperId;

    /**
     * 问题序列
     */
    @Column(name = "serial")
    private Integer serial;

}
