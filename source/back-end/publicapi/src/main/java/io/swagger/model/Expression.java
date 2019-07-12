package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 要查询执行的操作，类型是一个 操作表达式对象 ，每个 操作表达式 对象包括 operator(操作名),argument1/2 参数。同时参数也可以是另外的一个表达式对象。样例的查询等价于 ((grade &#x3D;&#x3D; \&quot;一年级\&quot;) and (sroce &lt;&#x3D; 10))| 具体信息在：https://www.yuque.com/czfshine/olm1pa/mxeyum
 */
@ApiModel(description = "要查询执行的操作，类型是一个 操作表达式对象 ，每个 操作表达式 对象包括 operator(操作名),argument1/2 参数。同时参数也可以是另外的一个表达式对象。样例的查询等价于 ((grade == \"一年级\") and (sroce <= 10))| 具体信息在：https://www.yuque.com/czfshine/olm1pa/mxeyum ")
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class Expression {
    @JsonProperty("operator")
    private String operator = null;

    @JsonProperty("argument1")
    private Object argument1 = null;

    @JsonProperty("argument2")
    private Object argument2 = null;

    public Expression operator(String operator) {
        this.operator = operator;
        return this;
    }

    /**
     * Get operator
     *
     * @return operator
     **/
    @ApiModelProperty(example = "and", required = true, value = "")
    @NotNull

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression argument1(Object argument1) {
        this.argument1 = argument1;
        return this;
    }

    /**
     * Get argument1
     *
     * @return argument1
     **/
    @ApiModelProperty(example = "{\"operator\":\"==\",\"argument1\":\"grade\",\"argument2\":\"一年级\"}", required = true, value = "")
    @NotNull

    public Object getArgument1() {
        return argument1;
    }

    public void setArgument1(Object argument1) {
        this.argument1 = argument1;
    }

    public Expression argument2(Object argument2) {
        this.argument2 = argument2;
        return this;
    }

    /**
     * Get argument2
     *
     * @return argument2
     **/
    @ApiModelProperty(example = "{\"operator\":\"==\",\"argument1\":\"subject\",\"argument2\":\"小学数学\"}", required = true, value = "")
    @NotNull

    public Object getArgument2() {
        return argument2;
    }

    public void setArgument2(Object argument2) {
        this.argument2 = argument2;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Expression expression = (Expression) o;
        return Objects.equals(this.operator, expression.operator) &&
                Objects.equals(this.argument1, expression.argument1) &&
                Objects.equals(this.argument2, expression.argument2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, argument1, argument2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Expression {\n");

        sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
        sb.append("    argument1: ").append(toIndentedString(argument1)).append("\n");
        sb.append("    argument2: ").append(toIndentedString(argument2)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
