package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * CollectionInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
public class CollectionInfo {
    @JsonProperty("poolId")
    private UUID poolId = null;

    @JsonProperty("token")
    private UUID token = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("problemIds")
    @Valid
    private List<String> problemIds = new ArrayList<String>();

    @JsonProperty("tags")
    @Valid
    private List<String> tags = null;

    public CollectionInfo poolId(UUID poolId) {
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

    public CollectionInfo token(UUID token) {
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

    public CollectionInfo title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     *
     * @return title
     **/
    @ApiModelProperty(example = "试卷标题", required = true, value = "")
    @NotNull

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CollectionInfo problemIds(List<String> problemIds) {
        this.problemIds = problemIds;
        return this;
    }

    public CollectionInfo addProblemIdsItem(String problemIdsItem) {
        this.problemIds.add(problemIdsItem);
        return this;
    }

    /**
     * Get problemIds
     *
     * @return problemIds
     **/
    @ApiModelProperty(example = "[\"123465\",\"654321\"]", required = true, value = "")
    @NotNull

    public List<String> getProblemIds() {
        return problemIds;
    }

    public void setProblemIds(List<String> problemIds) {
        this.problemIds = problemIds;
    }

    public CollectionInfo tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public CollectionInfo addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<String>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * Get tags
     *
     * @return tags
     **/
    @ApiModelProperty(example = "[\"一年级\",\"语文\"]", value = "")

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionInfo collectionInfo = (CollectionInfo) o;
        return Objects.equals(this.poolId, collectionInfo.poolId) &&
                Objects.equals(this.token, collectionInfo.token) &&
                Objects.equals(this.title, collectionInfo.title) &&
                Objects.equals(this.problemIds, collectionInfo.problemIds) &&
                Objects.equals(this.tags, collectionInfo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poolId, token, title, problemIds, tags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionInfo {\n");

        sb.append("    poolId: ").append(toIndentedString(poolId)).append("\n");
        sb.append("    token: ").append(toIndentedString(token)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    problemIds: ").append(toIndentedString(problemIds)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
