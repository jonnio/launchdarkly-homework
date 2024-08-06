package ai.osborn.ld_homework.olympics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResultRepositoryTest {

    @Autowired
    ResultRepository resultRepository;

    @Test
    public void testGetByMedal() {
        var results = resultRepository.getAllByMedal("Gold");
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    public void testCountByMedal() {
        assertTrue(resultRepository.countByMedal("Gold") > 0);
    }
}