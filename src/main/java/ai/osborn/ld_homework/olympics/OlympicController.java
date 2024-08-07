package ai.osborn.ld_homework.olympics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/olympics")
public class OlympicController {

    private OlympicResultRepository olympicResultRepository;

    public OlympicController(OlympicResultRepository olympicResultRepository) {
        this.olympicResultRepository = olympicResultRepository;
    }

    @GetMapping("/results")
    public List<OlympicResult> getResults() {
        return olympicResultRepository.findAll();
    }
}
