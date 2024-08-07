package ai.osborn.ld_homework.olympics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/olympics")
public class OlympicController {
    private static final Logger logger = LoggerFactory.getLogger(OlympicController.class);

    private final OlympicResultRepository olympicResultRepository;
    private final OlympicResultPagingRepository olympicResultPagingRepository;

    public OlympicController(OlympicResultRepository olympicResultRepository,
                             OlympicResultPagingRepository olympicResultPagingRepository) {
        this.olympicResultRepository = olympicResultRepository;
        this.olympicResultPagingRepository = olympicResultPagingRepository;
    }

    @GetMapping("/results")
    public List<OlympicResult> getResults() {
        return olympicResultRepository.findAll();
    }

    @GetMapping("/paged/results")
    public TabulatorPagedModel<OlympicResult> getPagedResults(@RequestParam TabulatorParamsModel params) {
        logger.debug("paging params {}", params);
        var sort = Sort.by(Sort.Direction.ASC, "id");
        if (!CollectionUtils.isEmpty(params.getSort())) {
            var first = params.getSort().iterator().next();
            sort = Sort.by("desc".equals(first.getDir()) ? Sort.Direction.DESC : Sort.Direction.ASC, first.getField());
        }

        return new TabulatorPagedModel<>(olympicResultPagingRepository
                .findAll(PageRequest.of(params.getPage(), params.getSize(), sort)));
    }

}
