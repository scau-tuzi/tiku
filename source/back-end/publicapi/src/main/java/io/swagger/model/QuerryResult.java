package io.swagger.model;

import java.util.HashMap;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * 查询结果
 */
@ApiModel(description = "查询结果")
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class QuerryResult   {
  @JsonProperty("status")
  private StatusCode status = null;

  @JsonProperty("results")
  @Valid
  private List<HashMap<String,Object>> results = new ArrayList<HashMap<String,Object>>();

  public QuerryResult status(StatusCode status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
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

  public QuerryResult results(List<HashMap<String,Object>> results) {
    this.results = results;
    return this;
  }

  public QuerryResult addResultsItem(HashMap<String,Object> resultsItem) {
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<HashMap<String,Object>> getResults() {
    return results;
  }

  public void setResults(List<HashMap<String,Object>> results) {
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
    QuerryResult querryResult = (QuerryResult) o;
    return Objects.equals(this.status, querryResult.status) &&
        Objects.equals(this.results, querryResult.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuerryResult {\n");
    
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
