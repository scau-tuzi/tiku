package io.swagger.pojo;

import io.swagger.pojo.dao.Paper;
import io.swagger.pojo.dao.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     *https://www.yuque.com/czfshine/olm1pa/as07ca
     * @return
     */

    public HashMap<String,Object> toMap(){
        HashMap<String, Object> map = new HashMap<>();

        if(paper!=null){
            map.put("paperId",paper.getId());
            map.put("title",paper.getTitle());
        }
        List<String> tagstr = new ArrayList<>();

        if(tags !=null){
            tagstr=tags.stream().map(Tag::getValue).collect(Collectors.toList());
        }
        if(tagstr.size()>=1){
            map.put("tags",tagstr);
        }

        if(serialProblemIdMap!=null){
            map.put("problemIds",serialProblemIdMap);
        }

        if(isDeep()){
            ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
            if(problems!=null){
                problems.stream().forEach((pd)->{
                    hashMaps.add(pd.toMap());
                });
            }
            map.put("problems",hashMaps);
        }

        return map;
    }
}
