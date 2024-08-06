package ai.osborn.ld_homework.olympics;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "olympic_result")
@Data
public class OlympicResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int olympicYear;
    @Column
    private String city;
    @Column
    private String sport;
    @Column
    private String discipline;
    @Column
    private String athlete;
    @Column
    private String country;
    @Column
    private String gender;
    @Column
    private String event;
    @Column
    private String medal;
}
