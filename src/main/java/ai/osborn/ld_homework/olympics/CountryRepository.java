package ai.osborn.ld_homework.olympics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

    Country findByCode(String code);
}
