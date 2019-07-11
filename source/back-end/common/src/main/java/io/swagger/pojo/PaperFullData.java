package io.swagger.pojo;

import io.swagger.pojo.dao.Paper;
import io.swagger.pojo.dao.Tag;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 聚合一张试卷的所有信息
 */
@Data
public class PaperFullData {

    private Paper paper;
    private List<Tag> tags;

    // 有时候只需要id就行
    private Map<Integer, Long> serialProblemIdMap;
    private List<ProblemFullData> problems;

    //表示查询的时候是否要获取具体的问题信息
    private boolean deep;


    /**
     * 转换成map，主要是用来返回给前台，返回的map只会（也只能）被框架解析成json对象
     * 对象的key约束和意义见开发文档
     *
     * @return
     */
    public HashMap<String, Object> toMap() {
        return new HashMap<>();
    }
}
