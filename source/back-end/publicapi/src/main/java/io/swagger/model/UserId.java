package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * UserId
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T03:10:29.413Z[GMT]")
public class UserId {
    @JsonProperty("poolId")
    private UUID poolId = null;

    @JsonProperty("token")
    private UUID token = null;

    @JsonProperty("unionid")
    private String unionid = null;

    public UserId poolId(UUID poolId) {
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

    public UserId token(UUID token) {
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

    public UserId unionid(String unionid) {
        this.unionid = unionid;
        return this;
    }

    /**
     * 微信用户唯一id
     *
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


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserId userId = (UserId) o;
        return Objects.equals(this.poolId, userId.poolId) &&
                Objects.equals(this.token, userId.token) &&
                Objects.equals(this.unionid, userId.unionid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poolId, token, unionid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserId {\n");

        sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    unionid: ").append(toIndentedString(unionid)).append("\n");
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
