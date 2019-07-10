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
import io.swagger.service.ProblemDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

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
      List<Long> longs = executeExpression(expression);
      return problemDataService.getFullDataByIds(longs);
   }



   /**根据传进来的查询表达式对象查询所有符合表达式的试卷
    * @param expression
    * @return
    */
   public static List<PaperFullData> getAllPapersByExpression(Expression expression){
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

   /**执行表达式，返回符合结果的问题id
    * @param expression
    * @return
    * @throws ParserErrorException
    */
   private static List<Long> executeExpression(Expression expression) throws ParserErrorException {
      @NotNull String op = expression.getOperator();


      switch (op){
         case "==":{
            @NotNull Object argument1 = expression.getArgument1();
            if(!(argument1 instanceof String) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第一个参数类型必须是字符串，但是收到的不是");
            }
            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof String) ){
               throw new ParserErrorException("操作【==】的第二个参数类型必须是字符串，但是收到的不是");
            }
            List<Long> res = problemEquals((String) argument1, (String) argument2);
            return res;
         }
         case "contains":{
            @NotNull Object argument1 = expression.getArgument1();
            if(!(argument1 instanceof String) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第一个参数类型必须是字符串，但是收到的不是");
            }
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
            List<Long> longs = problemContains((String) argument1, strings);
            return longs;
         }
         case "and":{
            @NotNull Object argument1 = expression.getArgument1();
            if(!(argument1 instanceof LinkedHashMap) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第一个参数类型必须是json对象，但是收到的不是");
            }
            Expression expression1 = mapToExpression((LinkedHashMap) argument1);

            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof LinkedHashMap) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第二个参数类型必须是json对象，但是收到的不是");
            }
            Expression expression2 = mapToExpression((LinkedHashMap) argument2);
            return andOperator(expression1,expression2);
         }
         case "or":{
            @NotNull Object argument1 = expression.getArgument1();
            if(!(argument1 instanceof LinkedHashMap) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第一个参数类型必须是json对象，但是收到的不是");
            }
            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof LinkedHashMap) ){
               throw new ParserErrorException("操作【"+expression.getOperator()+"】的第二个参数类型必须是json对象，但是收到的不是");
            }
            Expression expression1 = mapToExpression((LinkedHashMap) argument1);
            Expression expression2 = mapToExpression((LinkedHashMap) argument2);
            return orOperator(expression1,expression2);
         }
         default:
            throw  new ParserErrorException("操作【"+op+"】目前不被支持");
      }

   }


   private static Expression mapToExpression(LinkedHashMap json) throws ParserErrorException {
      Expression expression = new Expression();
      Object operator;
      Object argument1;
      Object argument2;

      try{

         operator = json.getOrDefault("operator",null);

         if(operator==null){
            throw  new ParserErrorException("表示式的json对象必须有operator域");
         }

         if(!(operator instanceof String )){
            throw  new ParserErrorException("表示式的json对象的operator域必须是字符串");
         }

         argument1 = json.getOrDefault("argument1", null);

         if(argument1==null){
            throw  new ParserErrorException("表示式的json对象必须有argument1域");
         }
         argument2 = json.getOrDefault("argument2", null);

         if(argument2==null){
            throw  new ParserErrorException("表示式的json对象必须有argument2域");
         }

      }catch (Exception e){
         throw  new ParserErrorException("解析表达式对象失败");
      }
      expression.setOperator((String) operator);
      expression.setArgument1(argument1);
      expression.setArgument2(argument2);
      return expression;
   }

   private static List<Long> orOperator(Expression left,Expression right) throws ParserErrorException {
      // todo 短路
      List<Long> l = executeExpression(left);
      List<Long> r = executeExpression(right);
      HashSet<Long> ls = new HashSet<>(l);
      ls.addAll(r);
      return new ArrayList<>(ls);
   }
   private static List<Long> andOperator(Expression left,Expression right) throws ParserErrorException {
      // todo 短路
      List<Long> l = executeExpression(left);
      List<Long> r = executeExpression(right);
      HashSet<Long> ls = new HashSet<>(l);
      return r.stream().filter(ls::contains).collect(Collectors.toList());
   }
   @Autowired
   private static ProblemRepository problemRepository;
   @Autowired
   private static ProblemTagRepository problemTagRepository;
   @Autowired
   private static TagRepository tagRepository;

   @Autowired
   private static ProblemDataService problemDataService;
}

