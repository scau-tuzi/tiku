package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 功能描述：问题状态实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "status")
public class Status extends BasicEntity {

    public static Integer UNCHECK = 0;
    public static Integer CHECK = 1;

    /**
     * 问题id
     */
    @Id
    @Column(name = "problem_id")
    private Long problemId;

    /**
     * 审核状态
     */
    @Column(name = "verify_status")
    private Integer verifyStatus = UNCHECK;

}
