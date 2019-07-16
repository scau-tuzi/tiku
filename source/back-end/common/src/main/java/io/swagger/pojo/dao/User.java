package io.swagger.pojo.dao;

import lombok.Data;

import javax.persistence.*;

/**
 * 功能描述：用户实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Entity
@Data
@Table(name = "user")
public class User extends BasicEntity {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码加盐后的MD5
     */
    @Column(name = "password_salt_md5")
    private String passwordSaltMd5;

    /**
     * 盐值
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 用户头像
     */
    @Column(name = "image")
    private String image;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
}
