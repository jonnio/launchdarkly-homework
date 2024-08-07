package ai.osborn.ld_homework.olympics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OlympicResultPagingRepository extends PagingAndSortingRepository<OlympicResult, Integer> {

}
