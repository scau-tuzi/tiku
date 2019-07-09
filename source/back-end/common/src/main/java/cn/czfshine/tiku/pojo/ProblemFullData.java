package cn.czfshine.tiku.pojo;

import cn.czfshine.tiku.pojo.dao.Answer;
import cn.czfshine.tiku.pojo.dao.Problem;
import cn.czfshine.tiku.pojo.dao.Status;
import cn.czfshine.tiku.pojo.dao.Tag;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

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
    private HashMap<String,String> extData;
    //状态表
    private Status status;
    /**转换成map，主要是用来返回给前台，返回的map只会（也只能）被框架解析成json对象
     * 对象的key约束和意义见开发文档
     * @return
     */
    public HashMap<String,Object> toMap(){
        return new HashMap<>();
    }
}
