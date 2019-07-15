package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 查询结果
 */
@ApiModel(description = "查询结果")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
public class StatusInfoList {
    @JsonProperty("status")
    private StatusCode status = null;

    @JsonProperty("results")
    @Valid
    private List<StatusInfo> results = new ArrayList<StatusInfo>();

    public StatusInfoList status(StatusCode status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid
    public StatusCode getStatus() {
        return status;
    }

    public void setStatus(StatusCode status) {
        this.status = status;
    }

    public StatusInfoList results(List<StatusInfo> results) {
        this.results = results;
        return this;
    }

    public StatusInfoList addResultsItem(StatusInfo resultsItem) {
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public List<StatusInfo> getResults() {
        return results;
    }

    public void setResults(List<StatusInfo> results) {
        this.results = results;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusInfoList statusInfoList = (StatusInfoList) o;
        return Objects.equals(this.status, statusInfoList.status) &&
                Objects.equals(this.results, statusInfoList.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StatusInfoList {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
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
