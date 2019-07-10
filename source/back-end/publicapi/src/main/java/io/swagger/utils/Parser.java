package io.swagger.utils;

import cn.czfshine.tiku.pojo.PaperFullData;
import cn.czfshine.tiku.pojo.ProblemFullData;
import cn.czfshine.tiku.pojo.dao.Problem;
import cn.czfshine.tiku.pojo.dao.ProblemTag;
import cn.czfshine.tiku.pojo.dao.Tag;
import cn.czfshine.tiku.pojo.dao.repos.ProblemRepository;
import cn.czfshine.tiku.pojo.dao.repos.ProblemTagRepository;
import cn.czfshine.tiku.pojo.dao.repos.TagRepository;
import io.swagger.model.Expression;
import io.swagger.model.ProblemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * 查询表达式解析器
 * @see io.swagger.model.Expression
 */
@Service
@Slf4j
public class Parser {


   /**根据传进来的查询表达式对象查询所有符合表达式的问题
    * @param expression
    * @return
    */
   public static List<ProblemFullData> getAllProblemsByExpression(Expression expression) throws ParserErrorException {

      @NotNull String op = expression.getOperator();
      @NotNull Object argument1 = expression.getArgument1();
      if(!(argument1 instanceof String) ){
         throw new ParserErrorException("操作【"+expression.getOperator()+"】的第一个参数类型必须是字符串，但是收到的不是");
      }
      switch (op){
         case "==":{

            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof String) ){
               throw new ParserErrorException("操作【==】的第二个参数类型必须是字符串，但是收到的不是");
            }
            List<Long> res = problemEquals((String) argument1, (String) argument2);
            break;
         }
         case "contains":{
            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof List) ){
               throw new ParserErrorException("操作【contains】的第二个参数类型必须是数组，但是收到的不是");
            }
            int size = ((List) argument2).size();
            if(size == 0 ){
               throw new ParserErrorException("操作【contains】的第二个参数数组为空");
            }
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < ((List) argument2).size(); i++) {
               Object item = ((List) argument2).get(i);
               if(!(item instanceof String) ){
                  throw new ParserErrorException("操作【contains】的第二个参数数组元素必须是字符串，但是收到的不是");
               }
               strings.add((String)item);
            }
            break;
         }
         default:
            throw  new ParserErrorException("操作【"+op+"】目前不被支持");
      }
      return new ArrayList<>();
   }



   /**根据传进来的查询表达式对象查询所有符合表达式的试卷
    * @param expression
    * @return
    */
   public static List<PaperFullData> getAllPapersByEpression(Expression expression){
      return new ArrayList<>();
   }


   /**查询数据项为该值的所有问题id
    * 数据项定义在这里https://www.yuque.com/czfshine/olm1pa/as07ca
    * @param fieldName
    * @param value
    * @return
    */
   private static List<Long> problemEquals(String fieldName,String value) throws ParserErrorException {
      ArrayList<Long> res = new ArrayList<>();
      try{

         switch (fieldName){
            case "problemId":{
               //todo 捕获数字解析错误
               Optional<Problem> byId = problemRepository.findById(Long.valueOf(value));
               byId.ifPresent(problem -> res.add(problem.getId()));
               break;
            }
            default:
               throw new ParserErrorException("域["+fieldName+"]不支持[==]操作");
         }
      }catch (Exception e){
         throw new ParserErrorException("未知错误:"+e.getMessage());
      }
      return res;
   }

   /**执行包含操作
    * @param fieldName
    * @param strings
    * @return
    */
   private static List<Long> problemContains(String fieldName,List<String> strings) throws ParserErrorException {
      switch (fieldName){
         case "tags":
         {
            return problemContainsTags(strings);
         }
         default:
            throw new ParserErrorException("域["+fieldName+"]不支持[contains]操作");
      }
   }
   /**找出含有所有标签的所有问题id
    * @param tags
    * @return
    */
   private static List<Long> problemContainsTags(List<String> tags){
      ArrayList<Long> res = new ArrayList<>();

      //每个问题出现对应标签的数量，结果应该是数量和输入的数量一样
      HashMap<Long,Integer> tagCount=new HashMap<>();

      for (String tagname: tags
           ) {
         Tag tagDao = tagRepository.findDistinctByValue(tagname);
         if(tagDao == null){
            // 找不到这个标签
            log.warn("前端请求标签{},但是数据库没有这个标签的记录",tags);
            //所有标签是与的关系，一个没有就没有结果
            return res;
         }

         Long id = tagDao.getId();
         List<ProblemTag> pts = problemTagRepository.findAllByTagIdEquals(id);
         pts.forEach((pt)-> tagCount.merge(pt.getProblemId(),1,(n, o)-> n+o));

      }
      
      int len = tags.size();
      tagCount.forEach((k,v)->{
         if(v==len){
            res.add(k);
         }
      });
      return res;
   }

   @Autowired
   private static ProblemRepository problemRepository;
   @Autowired
   private static ProblemTagRepository problemTagRepository;
   @Autowired
   private static TagRepository tagRepository;
}

