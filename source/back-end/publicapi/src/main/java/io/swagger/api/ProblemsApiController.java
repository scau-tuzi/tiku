package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.ProblemInfo;
import io.swagger.pojo.dto.BasicResponse;
import io.swagger.service.ProblemService;
import io.swagger.service.ProblemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-15T03:10:29.413Z[GMT]")
@Controller
public class ProblemsApiController implements ProblemsApi {

    private static final Logger log = LoggerFactory.getLogger(ProblemsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProblemsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private ProblemService problemService;

    // TODO 返回值
    public ResponseEntity<BasicResponse> addProblems(@ApiParam(value = "") @Valid @RequestBody ProblemInfo body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            BasicResponse basicResponse = new BasicResponse();
            try {
                ArrayList<Long> longs = problemService.addProblemByProblemInfo(body);
                basicResponse.setData(longs);
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.NOT_IMPLEMENTED);
            } catch (ProblemServiceImpl.ProblemServiceException e) {
                basicResponse.setCode("error");
                basicResponse.setData(e.getMessage());
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                e.printStackTrace();
                basicResponse.setCode("error");
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<BasicResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<BasicResponse> problemsDelete(@ApiParam(value = "题库id", required = true) @RequestHeader(value = "X-tiku-poolId", required = true) UUID xTikuPoolId, @ApiParam(value = "授权码", required = true) @RequestHeader(value = "X-tiku-token", required = true) UUID xTikuToken, @NotNull @ApiParam(value = "待删除的问题", required = true) @Valid @RequestParam(value = "problemId", required = true) String problemId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                problemService.deleteById(problemId);
                return new ResponseEntity<BasicResponse>(new BasicResponse(), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                BasicResponse basicResponse = new BasicResponse();
                basicResponse.setCode("error");
                basicResponse.setData(e.getMessage());
                return new ResponseEntity<BasicResponse>(basicResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<BasicResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
