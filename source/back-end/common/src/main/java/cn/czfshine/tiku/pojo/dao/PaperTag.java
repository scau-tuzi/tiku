package cn.czfshine.tiku.pojo.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;

/**
 * 功能描述：试卷所属标签实体类
 *
 * @作者：黄宽波
 * @时间:2019-07-09
 */
@IdClass(PaperTagPK.class)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "paper_tag")
public class PaperTag extends BasicEntity{

    /**
     * 试卷id
     */
    @Id
    @Column(name = "paper_id")
    private Long paperId;

    /**
     * 标签id
     */
    @Id
    @Column(name = "tag_id")
    private Long tagId;

}
