package ai.osborn.ld_homework.olympics;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TabulatorPagedModelTest {

    @Test
    void test_thatPagedModelWorks() {
        var model = new TabulatorPagedModel<>(new PageImpl<>(asList(1, 2, 3, 4, 5, 6), PageRequest.of(2, 2), 6));
        assertEquals(3, model.getTotalPages());
        ;
    }
}