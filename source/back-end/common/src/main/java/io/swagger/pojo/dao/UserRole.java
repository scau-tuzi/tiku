package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户角色表
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "user_role")
public class UserRole {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
}
