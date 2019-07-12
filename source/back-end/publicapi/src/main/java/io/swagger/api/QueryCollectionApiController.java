package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.QuerryInfo;
import io.swagger.model.QuerryResult;
import io.swagger.model.StatusCode;
import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.ProblemFullData;
import io.swagger.service.CollectionService;
import io.swagger.utils.ParserErrorException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@javax.annotation.Generated(value = "io.io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-07T02:38:17.975Z[GMT]")
@Controller
@Slf4j
public class QueryCollectionApiController implements QueryCollectionApi {

    @Autowired
    private CollectionService collectionService;
    private static final Logger log = LoggerFactory.getLogger(QueryCollectionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public QueryCollectionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<QuerryResult> queryCollection(@ApiParam(value = "") @Valid @RequestBody QuerryInfo body) {

        String accept = request.getHeader("Accept");
        ArrayList<HashMap<String, Object>> res = new ArrayList<>();
        // 查询
        List<PaperFullData> paperFullDatas;
        try {
            boolean isDeep =false;
            Boolean deep = body.isDeep();
            if(deep !=null && deep ==true){
                isDeep=true;
            }
            paperFullDatas = collectionService.queryCollection(body.getQuerry(),isDeep);
        } catch (ParserErrorException e) {
            // todo 表达异常
            e.printStackTrace();
            log.error(e.getMessage());
            QuerryResult querryResult = new QuerryResult();
            querryResult.setStatus(StatusCode.ERROR);
            // todo
            HashMap<String, Object> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("message", e.getMessage());
            querryResult.addResultsItem(stringStringHashMap);

            return new ResponseEntity<>(querryResult, HttpStatus.BAD_REQUEST);
        }

        // 拼装数据和做下格式转换
        QuerryResult querryResult = new QuerryResult();
        querryResult.setStatus(StatusCode.OK);

        // 拼装返回数据
        paperFullDatas.stream().forEach((e) -> {
            res.add(e.toMap());
        });
        querryResult.setResults(res);
        return new ResponseEntity<QuerryResult>(querryResult, HttpStatus.OK);
    }

}
