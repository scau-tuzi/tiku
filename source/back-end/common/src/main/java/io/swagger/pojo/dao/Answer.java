package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 功能描述：答案实体
 *
 * @作者：黄宽波
 * @时间：2019-07-09
 */
@Entity
@Data
@Table(name = "answer")
@Accessors(chain = true)
public class Answer extends BasicEntity {

    /**
     * 标识
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 答案文本
     */
    @Column(name = "answer_text")
    private String answerText;

    /**
     * 答案图片
     */
    @Column(name = "answer_images")
    private String answerImages;

}
