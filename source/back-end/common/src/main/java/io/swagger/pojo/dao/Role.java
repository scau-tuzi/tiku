package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 功能描述：角色实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Entity
@Table(name = "role")
@Data
@Accessors(chain = true)
public class Role extends BasicEntity{

    /**
     * 角色id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "rolename")
    private String roleName;

}
