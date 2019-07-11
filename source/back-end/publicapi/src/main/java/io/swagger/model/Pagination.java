package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 分页器
 */
@ApiModel(description = "分页器")
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class Pagination {
    @JsonProperty("page")
    private BigDecimal page = null;

    @JsonProperty("size")
    private BigDecimal size = null;

    @JsonProperty("total")
    private BigDecimal total = null;

    public Pagination page(BigDecimal page) {
        this.page = page;
        return this;
    }

    /**
     * Get page
     *
     * @return page
     **/
    @ApiModelProperty(example = "1", required = true, value = "")
    @NotNull

    @Valid
    public BigDecimal getPage() {
        return page;
    }

    public void setPage(BigDecimal page) {
        this.page = page;
    }

    public Pagination size(BigDecimal size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     *
     * @return size
     **/
    @ApiModelProperty(example = "10", required = true, value = "")
    @NotNull

    @Valid
    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public Pagination total(BigDecimal total) {
        this.total = total;
        return this;
    }

    /**
     * 后端返回还有多少数据的
     *
     * @return total
     **/
    @ApiModelProperty(value = "后端返回还有多少数据的")

    @Valid
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pagination pagination = (Pagination) o;
        return Objects.equals(this.page, pagination.page) &&
                Objects.equals(this.size, pagination.size) &&
                Objects.equals(this.total, pagination.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pagination {\n");

        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
