package ai.osborn.ld_homework.olympics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OlympicResultPagingRepositoryTest {

    @Autowired
    OlympicResultPagingRepository olympicResultPagingRepository;

    @Test
    public void test_get_the_first_page() {
        var page = olympicResultPagingRepository.findAll(PageRequest.of(0, 8, Sort.by("olympicYear")));
        assertNotNull(page);
        assertEquals(8, page.getContent().size());
    }
}