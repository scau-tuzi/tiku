package io.swagger.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Accessors(chain = true)
public class PermissionDto {
    private Long id;
    private String name;
    private String url;
    private String method;
    private List<PermissionDto> childPermissions;
    private Long parentPermission;
}
