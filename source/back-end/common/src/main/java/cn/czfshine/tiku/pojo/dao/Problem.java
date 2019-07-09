package cn.czfshine.tiku.pojo.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 问题信息的数据存取对象
 * // todo 其他字段-六个通用字段不要每个dao都写吧= =
 */
@Data
@Entity
public class Problem {
    @Id
    private long id;
    @Column
    private String problemText;
    @Column
    private long parentId;
    @Column
    private long answerId;
}
