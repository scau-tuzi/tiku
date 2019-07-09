package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QuerryInfo
 */
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class QuerryInfo   {
  @JsonProperty("poolId")
  private UUID poolId = null;

  @JsonProperty("token")
  private UUID token = null;

  @JsonProperty("querry")
  private Expression querry = null;

  @JsonProperty("random")
  private Boolean random = null;

  @JsonProperty("deep")
  private Boolean deep = null;

  @JsonProperty("ordering")
  private OrderInfo ordering = null;

  @JsonProperty("pagination")
  private Pagination pagination = null;

  public QuerryInfo poolId(UUID poolId) {
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

  public QuerryInfo token(UUID token) {
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

  public QuerryInfo querry(Expression querry) {
    this.querry = querry;
    return this;
  }

  /**
   * Get querry
   * @return querry
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Expression getQuerry() {
    return querry;
  }

  public void setQuerry(Expression querry) {
    this.querry = querry;
  }

  public QuerryInfo random(Boolean random) {
    this.random = random;
    return this;
  }

  /**
   * 是否打乱顺序输出
   * @return random
  **/
  @ApiModelProperty(example = "false", value = "是否打乱顺序输出")

  public Boolean isRandom() {
    return random;
  }

  public void setRandom(Boolean random) {
    this.random = random;
  }

  public QuerryInfo deep(Boolean deep) {
    this.deep = deep;
    return this;
  }

  /**
   * 是否递归获取所有信息 为false的话，result就只是一个id列表数组 为true的话，result是具体的信息对象，在可能有大量结果的时候慎用
   * @return deep
  **/
  @ApiModelProperty(example = "false", value = "是否递归获取所有信息 为false的话，result就只是一个id列表数组 为true的话，result是具体的信息对象，在可能有大量结果的时候慎用")

  public Boolean isDeep() {
    return deep;
  }

  public void setDeep(Boolean deep) {
    this.deep = deep;
  }

  public QuerryInfo ordering(OrderInfo ordering) {
    this.ordering = ordering;
    return this;
  }

  /**
   * Get ordering
   * @return ordering
  **/
  @ApiModelProperty(value = "")

  @Valid
  public OrderInfo getOrdering() {
    return ordering;
  }

  public void setOrdering(OrderInfo ordering) {
    this.ordering = ordering;
  }

  public QuerryInfo pagination(Pagination pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
  **/
  @ApiModelProperty(value = "")

  @Valid
  public Pagination getPagination() {
    return pagination;
  }

  public void setPagination(Pagination pagination) {
    this.pagination = pagination;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuerryInfo querryInfo = (QuerryInfo) o;
    return Objects.equals(this.poolId, querryInfo.poolId) &&
        Objects.equals(this.token, querryInfo.token) &&
        Objects.equals(this.querry, querryInfo.querry) &&
        Objects.equals(this.random, querryInfo.random) &&
        Objects.equals(this.deep, querryInfo.deep) &&
        Objects.equals(this.ordering, querryInfo.ordering) &&
        Objects.equals(this.pagination, querryInfo.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poolId, token, querry, random, deep, ordering, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuerryInfo {\n");
    
    sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    querry: ").append(toIndentedString(querry)).append("\n");
    sb.append("    random: ").append(toIndentedString(random)).append("\n");
    sb.append("    deep: ").append(toIndentedString(deep)).append("\n");
    sb.append("    ordering: ").append(toIndentedString(ordering)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
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
