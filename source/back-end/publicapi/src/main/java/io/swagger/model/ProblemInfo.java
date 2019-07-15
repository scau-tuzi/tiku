package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * ProblemInfo
 */
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class ProblemInfo {
    @JsonProperty("poolId")
    private UUID poolId = null;

    @JsonProperty("token")
    private UUID token = null;

    @JsonProperty("problems")
    @Valid
    private List<HashMap<String, Object>> problems = null;

    public ProblemInfo poolId(UUID poolId) {
        this.poolId = poolId;
        return this;
    }

    /**
     * 题库对应的id，从管理平台获取
     *
     * @return poolId
     **/
    @ApiModelProperty(example = "4468c74d-759e-4d78-8c43-e1c5405f193b", required = true, value = "题库对应的id，从管理平台获取")
    @NotNull

    @Valid
    public UUID getPoolId() {
        return poolId;
    }

    public void setPoolId(UUID poolId) {
        this.poolId = poolId;
    }

    public ProblemInfo token(UUID token) {
        this.token = token;
        return this;
    }

    /**
     * 验证用户权限的OAuth令牌，从管理平台申请，暂时无用
     *
     * @return token
     **/
    @ApiModelProperty(example = "06e599f3-78db-4c71-b4fa-2b496beab1f6", required = true, value = "验证用户权限的OAuth令牌，从管理平台申请，暂时无用")
    @NotNull

    @Valid
    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public ProblemInfo problems(List<HashMap<String, Object>> problems) {
        this.problems = problems;
        return this;
    }

    public ProblemInfo addProblemsItem(HashMap<String, Object> problemsItem) {
        if (this.problems == null) {
            this.problems = new ArrayList<HashMap<String, Object>>();
        }
        this.problems.add(problemsItem);
        return this;
    }

    /**
     * Get problems
     *
     * @return problems
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<HashMap<String, Object>> getProblems() {
        return problems;
    }

    public void setProblems(List<HashMap<String, Object>> problems) {
        this.problems = problems;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProblemInfo problemInfo = (ProblemInfo) o;
        return Objects.equals(this.poolId, problemInfo.poolId) &&
                Objects.equals(this.token, problemInfo.token) &&
                Objects.equals(this.problems, problemInfo.problems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poolId, token, problems);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProblemInfo {\n");

        sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    problems: ").append(toIndentedString(problems)).append("\n");
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
