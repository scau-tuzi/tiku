package io.swagger.service;

import io.swagger.model.CollectionInfo;
import io.swagger.model.Expression;
import io.swagger.pojo.PaperFullData;
import io.swagger.pojo.dao.Paper;
import io.swagger.utils.Parser;
import io.swagger.utils.ParserErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private Parser parser;
    @Autowired
    private WebPaperService webPaperService;
    @Autowired
    private WebTagService webTagService;
    @Override
    public List<PaperFullData> queryCollection(Expression expression,boolean isDeep) throws ParserErrorException {
         return parser.getAllPapersByExpression(expression,isDeep);

    }

    @Override
    public Long addPaperByCollectionInfo(CollectionInfo collectionInfo) throws Exception {

        PaperFullData paperFullData = new PaperFullData();

        Paper paper = new Paper();
        paper.setTitle(collectionInfo.getTitle());
        paperFullData.setPaper(paper);

        HashMap<Integer, Long> serialProblemIdMap = new HashMap<>();
        @NotNull List<String> problemIds = collectionInfo.getProblemIds();
        for (int i = 0; i < problemIds.size(); i++) {
            serialProblemIdMap.put(i,Long.valueOf(problemIds.get(i)));
        }
        paperFullData.setSerialProblemIdMap(serialProblemIdMap);
        paperFullData.setTags(webTagService.getTagsByValueList(collectionInfo.getTags()));
        return webPaperService.add(paperFullData,0L);
    }
}
