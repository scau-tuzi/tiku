package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户所属项目表
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "user_project")
public class UserProject {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;
}
