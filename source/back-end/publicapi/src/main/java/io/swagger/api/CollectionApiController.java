package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.CollectionIdResult;
import io.swagger.model.CollectionInfo;
import io.swagger.model.StatusCode;
import io.swagger.service.CollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-12T09:08:16.977Z[GMT]")
@Controller
public class CollectionApiController implements CollectionApi {

    private static final Logger log = LoggerFactory.getLogger(CollectionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CollectionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private CollectionService collectionService;

    public ResponseEntity<CollectionIdResult> collectionPost(@ApiParam(value = "") @Valid @RequestBody CollectionInfo body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            CollectionIdResult collectionIdResult = new CollectionIdResult();

            try {
                Long aLong = collectionService.addPaperByCollectionInfo(body);
                collectionIdResult.setStatus(StatusCode.OK);
                collectionIdResult.setResults(aLong.toString());
                return new ResponseEntity<CollectionIdResult>(collectionIdResult, HttpStatus.OK);
            } catch (Exception e) {
                collectionIdResult.setStatus(StatusCode.ERROR);
                collectionIdResult.setResults(e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<>(collectionIdResult, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CollectionIdResult>(HttpStatus.NOT_IMPLEMENTED);
    }

}
