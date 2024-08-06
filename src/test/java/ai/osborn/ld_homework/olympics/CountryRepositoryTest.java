package ai.osborn.ld_homework.olympics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void testCountryRepository() {
        assertTrue(countryRepository.count() > 0);
    }

    @Test
    public void testCountryRepository_has_usa_by_code() {
        var usa = countryRepository.findByCode("USA");
        assertNotNull(usa);
        assertNotNull(usa.getCode());
        assertNotNull(usa.getCountryName());
    }
}