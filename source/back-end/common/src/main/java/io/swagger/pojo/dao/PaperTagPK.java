package io.swagger.pojo.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 功能描述：试卷所属标签-复合主键类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@Data
public class PaperTagPK implements Serializable {
    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 标签id
     */
    private Long tagId;
}
