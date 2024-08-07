package ai.osborn.ld_homework.olympics;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import java.util.Objects;

/**
 * Create a response that Tabulator can understand
 *
 * @param <T>
 */
public class TabulatorPagedModel<T> extends PagedModel<T> {

    /**
     * Creates a new {@link PagedModel} for the given {@link Page}.
     *
     * @param page must not be {@literal null}.
     */
    public TabulatorPagedModel(Page<T> page) {
        super(page);
    }

    public long getTotalPages() {
        return Objects.requireNonNull(getMetadata()).totalPages();
    }
}
