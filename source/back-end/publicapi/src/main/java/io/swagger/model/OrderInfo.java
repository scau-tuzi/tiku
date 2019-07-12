package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 排序方式，type 有desc 和asce 两种，next是下一级的排序项
 */
@ApiModel(description = "排序方式，type 有desc 和asce 两种，next是下一级的排序项")
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
public class OrderInfo {
    /**
     * Gets or Sets sorttype
     */
    public enum SorttypeEnum {
        DESC("desc"),

        ASCE("asce");

        private String value;

        SorttypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static SorttypeEnum fromValue(String text) {
            for (SorttypeEnum b : SorttypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("sorttype")
    private SorttypeEnum sorttype = null;

    @JsonProperty("field")
    private String field = null;

    @JsonProperty("next")
    private OrderInfo next = null;

    public OrderInfo sorttype(SorttypeEnum sorttype) {
        this.sorttype = sorttype;
        return this;
    }

    /**
     * Get sorttype
     *
     * @return sorttype
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public SorttypeEnum getSorttype() {
        return sorttype;
    }

    public void setSorttype(SorttypeEnum sorttype) {
        this.sorttype = sorttype;
    }

    public OrderInfo field(String field) {
        this.field = field;
        return this;
    }

    /**
     * Get field
     *
     * @return field
     **/
    @ApiModelProperty(example = "create_time", required = true, value = "")
    @NotNull

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OrderInfo next(OrderInfo next) {
        this.next = next;
        return this;
    }

    /**
     * Get next
     *
     * @return next
     **/
    @ApiModelProperty(value = "")

    @Valid
    public OrderInfo getNext() {
        return next;
    }

    public void setNext(OrderInfo next) {
        this.next = next;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(this.sorttype, orderInfo.sorttype) &&
                Objects.equals(this.field, orderInfo.field) &&
                Objects.equals(this.next, orderInfo.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sorttype, field, next);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderInfo {\n");

        sb.append("    sorttype: ").append(toIndentedString(sorttype)).append("\n");
        sb.append("    field: ").append(toIndentedString(field)).append("\n");
        sb.append("    next: ").append(toIndentedString(next)).append("\n");
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
