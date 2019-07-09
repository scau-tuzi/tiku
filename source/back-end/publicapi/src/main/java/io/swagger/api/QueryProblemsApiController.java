package io.swagger.api;

import io.swagger.model.QuerryInfo;
import io.swagger.model.QuerryResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
@Controller
public class QueryProblemsApiController implements QueryProblemsApi {

    private static final Logger log = LoggerFactory.getLogger(QueryProblemsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public QueryProblemsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<QuerryResult> queryProblems(@ApiParam(value = ""  )  @Valid @RequestBody QuerryInfo body) {
        String accept = request.getHeader("Accept");
        QuerryResult querryResult = new QuerryResult();
        querryResult.setStatus(StatusCode.OK);
        ArrayList<HashMap<String,Object>> res=new ArrayList<>();

//        querryResult.setResults(res);
        return new ResponseEntity<QuerryResult>(HttpStatus.NOT_IMPLEMENTED);
    }

}
