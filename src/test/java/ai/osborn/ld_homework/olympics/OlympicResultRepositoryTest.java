package ai.osborn.ld_homework.olympics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OlympicResultRepositoryTest {

    @Autowired
    OlympicResultRepository olympicResultRepository;

    @Test
    public void testGetByMedal() {
        var results = olympicResultRepository.getAllByMedal("Gold");
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testCountByMedal() {
        assertTrue(olympicResultRepository.countByMedal("Gold") > 0);
    }
}