package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 项目授权id表
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "project_token")
public class ProjectToken {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 项目授权码
     */
    @Column(name = "token")
    private String token;
    /**
     * 失效时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 失效状态，1为失效
     */
    @Column(name = "status")
    private Boolean status;
}
