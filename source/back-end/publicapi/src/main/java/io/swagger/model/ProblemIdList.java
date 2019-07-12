package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 待获取的状态查询信息列表，下面三个是and的关系
 */
@ApiModel(description = "待获取的状态查询信息列表，下面三个是and的关系")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
public class ProblemIdList   {
  @JsonProperty("poolId")
  private UUID poolId = null;

  @JsonProperty("token")
  private UUID token = null;

  @JsonProperty("ids")
  @Valid
  private List<String> ids = null;

  @JsonProperty("unionids")
  @Valid
  private List<String> unionids = null;

  @JsonProperty("status")
  @Valid
  private List<String> status = null;

  public ProblemIdList poolId(UUID poolId) {
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

  public ProblemIdList token(UUID token) {
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

  public ProblemIdList ids(List<String> ids) {
    this.ids = ids;
    return this;
  }

  public ProblemIdList addIdsItem(String idsItem) {
    if (this.ids == null) {
      this.ids = new ArrayList<String>();
    }
    this.ids.add(idsItem);
    return this;
  }

  /**
   * 问题id列表
   * @return ids
  **/
  @ApiModelProperty(example = "[\"123456\"]", value = "问题id列表")

  public List<String> getIds() {
    return ids;
  }

  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  public ProblemIdList unionids(List<String> unionids) {
    this.unionids = unionids;
    return this;
  }

  public ProblemIdList addUnionidsItem(String unionidsItem) {
    if (this.unionids == null) {
      this.unionids = new ArrayList<String>();
    }
    this.unionids.add(unionidsItem);
    return this;
  }

  /**
   * 用户id列表
   * @return unionids
  **/
  @ApiModelProperty(example = "[\"xxxxxxx\"]", value = "用户id列表")

  public List<String> getUnionids() {
    return unionids;
  }

  public void setUnionids(List<String> unionids) {
    this.unionids = unionids;
  }

  public ProblemIdList status(List<String> status) {
    this.status = status;
    return this;
  }

  public ProblemIdList addStatusItem(String statusItem) {
    if (this.status == null) {
      this.status = new ArrayList<String>();
    }
    this.status.add(statusItem);
    return this;
  }

  /**
   * 状态列表
   * @return status
  **/
  @ApiModelProperty(example = "[\"错题\"]", value = "状态列表")

  public List<String> getStatus() {
    return status;
  }

  public void setStatus(List<String> status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProblemIdList problemIdList = (ProblemIdList) o;
    return Objects.equals(this.poolId, problemIdList.poolId) &&
        Objects.equals(this.token, problemIdList.token) &&
        Objects.equals(this.ids, problemIdList.ids) &&
        Objects.equals(this.unionids, problemIdList.unionids) &&
        Objects.equals(this.status, problemIdList.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poolId, token, ids, unionids, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemIdList {\n");
    
    sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    ids: ").append(toIndentedString(ids)).append("\n");
    sb.append("    unionids: ").append(toIndentedString(unionids)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
