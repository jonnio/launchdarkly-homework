package ai.osborn.ld_homework.olympics;

import lombok.Data;

import java.util.Collection;

@Data
public class TabulatorParamsModel {
    private int page;
    private int size;
    private Collection<TabulatorSortModel> sort;
}
