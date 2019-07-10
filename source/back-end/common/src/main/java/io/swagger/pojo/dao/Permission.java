package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 功能描述：权限实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "permission")
public class Permission extends BasicEntity {

    /**
     * 权限id
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 请求接口
     */
    @Column(name = "url")
    private String url;

    /**
     * 请求方法
     */
    @Column(name = "method")
    private String method;

    /**
     * 父权限
     */
    @Column(name = "parent_permission")
    private Long parentPermission;
}
