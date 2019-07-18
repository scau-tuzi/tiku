package io.swagger.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RoleDto {
    private Long id;
    private String roleName;
    private List<Long> permissionList;
}
