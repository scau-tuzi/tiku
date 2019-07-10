package cn.czfshine.tiku.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class Status extends BasicEntity{

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
    private Integer verifyStatus;


}
