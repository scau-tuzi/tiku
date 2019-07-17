package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 题库
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "pool")
public class Pool {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 题库名
     */
    @Column(name = "pool_name")
    private String poolName;
    /**
     * 题库标识
     */
    @Column(name = "pool_uuid")
    private String poolUuid;
}
