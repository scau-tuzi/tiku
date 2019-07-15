package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 状态信息
 */
@ApiModel(description = "状态信息")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
public class StatusInfo   {
  @JsonProperty("poolId")
  private UUID poolId = null;

  @JsonProperty("token")
  private UUID token = null;

  @JsonProperty("problemId")
  private String problemId = null;

  @JsonProperty("unionid")
  private String unionid = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("date")
  private Long date = null;

  public StatusInfo poolId(UUID poolId) {
    this.poolId = poolId;
    return this;
  }

  /**
   * 题库对应的id，从管理平台获取
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

  public StatusInfo token(UUID token) {
    this.token = token;
    return this;
  }

  /**
   * 验证用户权限的OAuth令牌，从管理平台申请，暂时无用
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

  public StatusInfo problemId(String problemId) {
    this.problemId = problemId;
    return this;
  }

  /**
   * 题目id
   * @return problemId
  **/
  @ApiModelProperty(example = "123456", required = true, value = "题目id")
  @NotNull

  public String getProblemId() {
    return problemId;
  }

  public void setProblemId(String problemId) {
    this.problemId = problemId;
  }

  public StatusInfo unionid(String unionid) {
    this.unionid = unionid;
    return this;
  }

  /**
   * 微信用户唯一id
   * @return unionid
  **/
  @ApiModelProperty(example = "xxxxxxxxxxx", required = true, value = "微信用户唯一id")
  @NotNull

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }

  public StatusInfo status(String status) {
    this.status = status;
    return this;
  }

  /**
   * 用户对应的问题状态信息，具体格式看上传者的定义
   * @return status
  **/
  @ApiModelProperty(example = "错题+已解决", required = true, value = "用户对应的问题状态信息，具体格式看上传者的定义")
  @NotNull

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public StatusInfo date(Long date) {
    this.date = date;
    return this;
  }

  /**
   * 状态时间
   * @return date
  **/
  @ApiModelProperty(example = "2019-08-18", value = "状态时间")

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusInfo statusInfo = (StatusInfo) o;
    return Objects.equals(this.poolId, statusInfo.poolId) &&
        Objects.equals(this.token, statusInfo.token) &&
        Objects.equals(this.problemId, statusInfo.problemId) &&
        Objects.equals(this.unionid, statusInfo.unionid) &&
        Objects.equals(this.status, statusInfo.status) &&
        Objects.equals(this.date, statusInfo.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poolId, token, problemId, unionid, status, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StatusInfo {\n");
    
    sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    problemId: ").append(toIndentedString(problemId)).append("\n");
    sb.append("    unionid: ").append(toIndentedString(unionid)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
