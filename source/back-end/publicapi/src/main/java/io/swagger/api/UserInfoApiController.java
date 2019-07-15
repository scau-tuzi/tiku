package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.UserInfo;
import io.swagger.pojo.dto.BasicResponse;
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
import java.util.HashMap;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T03:10:29.413Z[GMT]")
@Controller
public class UserInfoApiController implements UserInfoApi {

    private static final Logger log = LoggerFactory.getLogger(UserInfoApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserInfoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private UserInfoService userInfoService;

    public ResponseEntity<BasicResponse> userInfoPost(@ApiParam(value = "") @Valid @RequestBody UserInfo body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                userInfoService.addUserInfo(body);

                return new ResponseEntity<BasicResponse>(new BasicResponse(), HttpStatus.OK);
            } catch (UserInfoServiceImpl.UserInfoServiceException e) {
                BasicResponse basicResponse = new BasicResponse();
                basicResponse.setCode("error");
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("message", e.getMessage());
                basicResponse.setData(stringObjectHashMap);
                e.printStackTrace();
                return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<BasicResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
