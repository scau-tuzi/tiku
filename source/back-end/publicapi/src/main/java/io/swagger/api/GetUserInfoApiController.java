package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.UserId;
import io.swagger.model.UserInfo;
import io.swagger.service.UserInfoService;
import io.swagger.service.UserInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Autowired
    private UserInfoService userInfoService;

    public ResponseEntity<UserInfo> getUserInfoPost(@ApiParam(value = "") @Valid @RequestBody UserId body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                UserInfo userInfo = userInfoService.getUserInfo(body);
                return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
            } catch (UserInfoServiceImpl.UserInfoServiceException e) {
                ResponseEntity<UserInfo> objectResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                objectResponseEntity.getHeaders().add("X-tiku-error-message", e.getMessage());
                return objectResponseEntity;
            }
        }
        return new ResponseEntity<UserInfo>(HttpStatus.NOT_IMPLEMENTED);
    }

}
