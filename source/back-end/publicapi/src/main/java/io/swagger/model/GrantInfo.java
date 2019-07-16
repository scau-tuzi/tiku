package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * GrantInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
public class GrantInfo {
    @JsonProperty("appToken")
    private UUID appToken = null;

    @JsonProperty("userToken")
    private UUID userToken = null;

    @JsonProperty("grantRole")
    private String grantRole = null;

    @JsonProperty("expiresIn")
    private String expiresIn = null;

    public GrantInfo appToken(UUID appToken) {
        this.appToken = appToken;
        return this;
    }

    /**
     * 应用授权id，从管理平台获取
     *
     * @return appToken
     **/
    @ApiModelProperty(required = true, value = "应用授权id，从管理平台获取")
    @NotNull

    @Valid
    public UUID getAppToken() {
        return appToken;
    }

    public void setAppToken(UUID appToken) {
        this.appToken = appToken;
    }

    public GrantInfo userToken(UUID userToken) {
        this.userToken = userToken;
        return this;
    }

    /**
     * 待授权的用户授权码，可本地自行生成，或者不传，服务器生成
     *
     * @return userToken
     **/
    @ApiModelProperty(value = "待授权的用户授权码，可本地自行生成，或者不传，服务器生成")

    @Valid
    public UUID getUserToken() {
        return userToken;
    }

    public void setUserToken(UUID userToken) {
        this.userToken = userToken;
    }

    public GrantInfo grantRole(String grantRole) {
        this.grantRole = grantRole;
        return this;
    }

    /**
     * 权限角色
     *
     * @return grantRole
     **/
    @ApiModelProperty(required = true, value = "权限角色")
    @NotNull

    public String getGrantRole() {
        return grantRole;
    }

    public void setGrantRole(String grantRole) {
        this.grantRole = grantRole;
    }

    public GrantInfo expiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    /**
     * 时效，默认300s
     *
     * @return expiresIn
     **/
    @ApiModelProperty(example = "300", value = "时效，默认300s")

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GrantInfo grantInfo = (GrantInfo) o;
        return Objects.equals(this.appToken, grantInfo.appToken) &&
                Objects.equals(this.userToken, grantInfo.userToken) &&
                Objects.equals(this.grantRole, grantInfo.grantRole) &&
                Objects.equals(this.expiresIn, grantInfo.expiresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appToken, userToken, grantRole, expiresIn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GrantInfo {\n");

        sb.append("    appToken: ").append(toIndentedString(appToken)).append("\n");
        sb.append("    userToken: ").append(toIndentedString(userToken)).append("\n");
        sb.append("    grantRole: ").append(toIndentedString(grantRole)).append("\n");
        sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
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
