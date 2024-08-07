package ai.osborn.ld_homework.olympics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OlympicResultRepository extends JpaRepository<OlympicResult, Integer> {
    List<OlympicResult> getAllByMedal(String medal);
    int countByMedal(String medal);
}
