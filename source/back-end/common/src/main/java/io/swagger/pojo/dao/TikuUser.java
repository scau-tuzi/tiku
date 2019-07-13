package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "tiku_user")
public class TikuUser extends BasicEntity {

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
     * 用户唯一id
     */
    @Column(name="user_uuid")
    private String userUuid;

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
