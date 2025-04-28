package sdu.project.chooserback.models;

import jakarta.persistence.*;
import lombok.Data;
import sdu.project.chooserback.enums.Level;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;
    private String task;

}
