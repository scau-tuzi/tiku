package io.swagger.api;

import io.swagger.model.UserId;
import io.swagger.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T03:10:29.413Z[GMT]")
@Controller
public class GetUserInfoApiController implements GetUserInfoApi {

    private static final Logger log = LoggerFactory.getLogger(GetUserInfoApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetUserInfoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<UserInfo> getUserInfoPost(@ApiParam(value = ""  )  @Valid @RequestBody UserId body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserInfo>(objectMapper.readValue("{\n" +
                        "  \"unionid\" : \"xxxxxxxxxxx\",\n" +
                        "  \"grade\" : \"一年级\",\n" +
                        "  \"poolId\" : \"4468c74d-759e-4d78-8c43-e1c5405f193b\",\n" +
                        "  \"hasTags\" : [ \"语文\", \"数学\" ],\n" +
                        "  \"token\" : \"06e599f3-78db-4c71-b4fa-2b496beab1f6\"\n" +
                        "}", UserInfo.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserInfo>(HttpStatus.NOT_IMPLEMENTED);
    }

}
