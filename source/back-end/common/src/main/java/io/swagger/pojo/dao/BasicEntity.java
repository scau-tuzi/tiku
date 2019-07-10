package io.swagger.pojo.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：基础实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Slf4j
@Data
@MappedSuperclass
public abstract class BasicEntity implements Serializable{

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 是否删除
     */
    @Column(name = "is_del")
    private Boolean isDel = Boolean.FALSE;
}
