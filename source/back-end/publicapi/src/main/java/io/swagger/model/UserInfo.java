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
 * UserInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T03:10:29.413Z[GMT]")
public class UserInfo   {
  @JsonProperty("unionid")
  private String unionid = null;

  @JsonProperty("grade")
  private String grade = null;

  @JsonProperty("poolId")
  private UUID poolId = null;

  @JsonProperty("token")
  private UUID token = null;

  @JsonProperty("hasTags")
  @Valid
  private List<String> hasTags = null;

  public UserInfo unionid(String unionid) {
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

  public UserInfo grade(String grade) {
    this.grade = grade;
    return this;
  }

  /**
   * Get grade
   * @return grade
  **/
  @ApiModelProperty(example = "一年级", required = true, value = "")
  @NotNull

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public UserInfo poolId(UUID poolId) {
    this.poolId = poolId;
    return this;
  }

  /**
   * 题库对应的id，从管理平台获取
   * @return poolId
  **/
  @ApiModelProperty(example = "4468c74d-759e-4d78-8c43-e1c5405f193b", value = "题库对应的id，从管理平台获取")

  @Valid
  public UUID getPoolId() {
    return poolId;
  }

  public void setPoolId(UUID poolId) {
    this.poolId = poolId;
  }

  public UserInfo token(UUID token) {
    this.token = token;
    return this;
  }

  /**
   * 验证用户权限的OAuth令牌，从管理平台申请，暂时无用
   * @return token
  **/
  @ApiModelProperty(example = "06e599f3-78db-4c71-b4fa-2b496beab1f6", value = "验证用户权限的OAuth令牌，从管理平台申请，暂时无用")

  @Valid
  public UUID getToken() {
    return token;
  }

  public void setToken(UUID token) {
    this.token = token;
  }

  public UserInfo hasTags(List<String> hasTags) {
    this.hasTags = hasTags;
    return this;
  }

  public UserInfo addHasTagsItem(String hasTagsItem) {
    if (this.hasTags == null) {
      this.hasTags = new ArrayList<String>();
    }
    this.hasTags.add(hasTagsItem);
    return this;
  }

  /**
   * Get hasTags
   * @return hasTags
  **/
  @ApiModelProperty(example = "[\"语文\",\"数学\"]", value = "")

  public List<String> getHasTags() {
    return hasTags;
  }

  public void setHasTags(List<String> hasTags) {
    this.hasTags = hasTags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfo userInfo = (UserInfo) o;
    return Objects.equals(this.unionid, userInfo.unionid) &&
        Objects.equals(this.grade, userInfo.grade) &&
        Objects.equals(this.poolId, userInfo.poolId) &&
        Objects.equals(this.token, userInfo.token) &&
        Objects.equals(this.hasTags, userInfo.hasTags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unionid, grade, poolId, token, hasTags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfo {\n");
    
    sb.append("    unionid: ").append(toIndentedString(unionid)).append("\n");
    sb.append("    grade: ").append(toIndentedString(grade)).append("\n");
    sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    hasTags: ").append(toIndentedString(hasTags)).append("\n");
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
