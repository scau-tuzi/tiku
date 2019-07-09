package io.swagger.utils;

import cn.czfshine.tiku.pojo.PaperFullData;
import cn.czfshine.tiku.pojo.ProblemFullData;
import io.swagger.model.Expression;
import io.swagger.model.ProblemInfo;
import org.springframework.beans.factory.parsing.Problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询表达式解析器
 * @see io.swagger.model.Expression
 */

public class Parser {

   /**根据传进来的查询表达式对象查询所有符合表达式的问题
    * @param expression
    * @return
    */
   public static List<ProblemFullData> getAllProblemsByExpression(Expression expression){
      //todo
      return new ArrayList<>();
   }


   /**根据传进来的查询表达式对象查询所有符合表达式的试卷
    * @param expression
    * @return
    */
   public static List<PaperFullData> getAllPapersByEpression(Expression expression){
      return new ArrayList<>();
   }
}

