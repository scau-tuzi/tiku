package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ProblemIdList;
import io.swagger.model.StatusCode;
import io.swagger.model.StatusInfo;
import io.swagger.model.StatusInfoList;
import io.swagger.pojo.dao.UserProblemStatus;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.ProblemStatusService;
import io.swagger.service.ProblemStatusServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
@Controller
public class ProblemStatusApiController implements ProblemStatusApi {

    private static final Logger log = LoggerFactory.getLogger(ProblemStatusApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProblemStatusApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<StatusInfoList> getProblemStatus(@ApiParam(value = "") @Valid @RequestBody ProblemIdList body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {

                List<UserProblemStatus> problemStatus = problemStatusService.getProblemStatus(body);

                List<StatusInfo> collect = problemStatus.stream().map((u) -> {
                    StatusInfo statusInfo = new StatusInfo();
                    Date date = u.getDate();
                    if (date == null) {
                        log.warn("查询结果{}的date为null", u);
                        date = new Date();
                    }
                    statusInfo.setDate(date.getTime());
                    statusInfo.setProblemId(String.valueOf(u.getProblemId()));
                    statusInfo.setStatus(u.getStatus());
                    statusInfo.setUnionid(u.getUserUuid());
                    return statusInfo;
                }).collect(Collectors.toList());
                StatusInfoList statusInfoList = new StatusInfoList();
                statusInfoList.setResults(collect);
                statusInfoList.setStatus(StatusCode.OK);

                return new ResponseEntity<StatusInfoList>(statusInfoList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                StatusInfoList statusInfoList = new StatusInfoList();
                statusInfoList.setStatus(StatusCode.ERROR);
                StatusInfo statusInfo = new StatusInfo();
                statusInfo.setStatus(e.getMessage());
                statusInfoList.addResultsItem(statusInfo);
                return new ResponseEntity<StatusInfoList>(statusInfoList, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<StatusInfoList>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Autowired
    private ProblemStatusService problemStatusService;

    //put
    public ResponseEntity<BasicResponse> problemStatus(@ApiParam(value = "") @Valid @RequestBody StatusInfo body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            BasicResponse basicResponse = new BasicResponse();

            try {

                problemStatusService.updateProblemStatus(body);
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.OK);
            } catch (ProblemStatusServiceImpl.ProblemStatusArgumentException e) {
                basicResponse.setCode("error");
                basicResponse.setData(e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<BasicResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
