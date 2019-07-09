package io.swagger.utils;

import cn.czfshine.tiku.pojo.PaperFullData;
import cn.czfshine.tiku.pojo.ProblemFullData;
import cn.czfshine.tiku.pojo.dao.Problem;
import cn.czfshine.tiku.pojo.dao.repos.ProblemRepository;
import io.swagger.model.Expression;
import io.swagger.model.ProblemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 查询表达式解析器
 * @see io.swagger.model.Expression
 */
@Service
public class Parser {


   /**根据传进来的查询表达式对象查询所有符合表达式的问题
    * @param expression
    * @return
    */
   public static List<ProblemFullData> getAllProblemsByExpression(Expression expression) throws ParserErrorException {

      @NotNull String op = expression.getOperator();
      switch (op){
         case "==":{
            @NotNull Object argument1 = expression.getArgument1();
            if(!(argument1 instanceof String) ){
               throw new ParserErrorException("操作【==】的第一个参数类型必须是字符串，但是收到的不是");
            }
            @NotNull Object argument2 = expression.getArgument2();
            if(!(argument2 instanceof String) ){
               throw new ParserErrorException("操作【==】的第二个参数类型必须是字符串，但是收到的不是");
            }
            problemEquals((String)argument1,(String)argument2);
         }
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

   /**找出含有所有标签的所有问题id
    * @param tags
    * @return
    */
   private static List<Long> problemContainsTags(List<String> tags){
      ArrayList<Long> res = new ArrayList<>();
      for (String tagname: tags
           ) {

      }
      return res;
   }

   @Autowired
   private static ProblemRepository problemRepository;
}

