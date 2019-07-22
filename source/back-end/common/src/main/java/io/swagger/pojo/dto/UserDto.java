package io.swagger.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String image;

    private List<Long> roleIds;


}
