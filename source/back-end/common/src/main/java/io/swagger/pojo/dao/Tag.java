package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 问题描述：标签实体
 *
 * @作者：黄宽波
 * @时间：2019-07-09
 */
@Accessors(chain = true)
@Data
@Table(name = "tag")
@Entity
public class Tag extends BasicEntity{

    /**
     * 标签id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 标签名称
     */
    @Column(name = "value")
    private String value;

    /**
     * 父标签
     */
    @Column(name = "parent_id")
    private Long parentId;

    public Tag(Long id, String value, Long parentId) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
    }

    public Tag() {
    }
}

























