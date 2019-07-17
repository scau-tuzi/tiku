package io.swagger.pojo;

import io.swagger.pojo.dao.Answer;
import io.swagger.pojo.dao.Problem;
import io.swagger.pojo.dao.Status;
import io.swagger.pojo.dao.Tag;
import lombok.Data;
import org.hibernate.mapping.Collection;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *一个问题的内部表达形式,聚合一个问题的所有相关数据
 */

@Data
public class ProblemFullData {
    //数据库的problem表
    private Problem problem;
    //数据库的answer表
    private Answer answer;
    // 标签表
    private List<Tag> tags;
    //额外数据 ext_data表，处理成map
    private Map<String,String> extData;
    //状态表
    private Status status;

    public static List<String> FIELDNAME= Arrays.asList(
            "text", // 题目文本
            "answer", //答案文本
            "tags", //标签列表
            "status");//状态
    /**转换成map，主要是用来返回给前台，返回的map只会（也只能）被框架解析成json对象
     * 对象的key约束和意义见开发文档
     * @return
     */
    public HashMap<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        if(problem != null){
            map.put("problemId",problem.getId());
            map.put("text",problem.getProblemText());
        }
        if(answer!=null){
            map.put("answer",answer.getAnswerText());
        }

        List<String> tagstr = new ArrayList<>();
        if(tags !=null){
            tagstr=tags.stream().map(Tag::getValue).collect(Collectors.toList());
        }
        if(tagstr.size()>0){
            map.put("tags",tagstr);
        }
        if(extData!=null){
            extData.forEach(map::put);
        }
        if(status!=null){
            map.put("status",status.getVerifyStatus());
        }
        return map;
    }
}
