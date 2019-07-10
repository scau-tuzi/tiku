package io.swagger.pojo.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 功能描述：试卷项-复合主键
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
public class PaperItemPK implements Serializable{

    /**
     * 问题id
     */
    private Long problemId;

    /**
     * 试卷id
     */
    private Long paperId;

}
