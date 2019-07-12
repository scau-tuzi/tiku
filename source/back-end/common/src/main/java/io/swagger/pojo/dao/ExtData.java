package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 功能描述：问题扩展信息实体
 *
 * @作者：黄宽波
 * @时间：2019-07-09
 */
@Entity
@Data
@Table(name = "ext_data")
@Accessors(chain = true)
public class ExtData extends BasicEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 所属问题
     */
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 数据项的key
     */
    @Column(name = "keyname")
    private String keyname;

    /**
     * 数据项的值
     */
    @Column(name = "value")
    private String value;

}
