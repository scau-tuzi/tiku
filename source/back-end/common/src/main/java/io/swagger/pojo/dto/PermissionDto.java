package io.swagger.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PermissionDto {
    private Long id;
    private String name;
    private List<PermissionDto> childPermissions;
}
