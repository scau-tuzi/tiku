package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 项目
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "project")
public class Project {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 项目名
     */
    @Column(name = "project_name")
    private String projectName;
    /**
     * 项目标识id
     */
    @Column(name = "project_uuid")
    private String projectUuid;
}
