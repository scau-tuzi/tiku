package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 功能描述：问题实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "problem")
public class Problem extends BasicEntity {

    /**
     * 问题id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 问题主体文本
     */
    @Column(name = "problem_text")
    private String problemText;

    /**
     * 父问题
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 对应答案id
     */
    @Column(name = "answer_id")
    private Long answerId;
}
