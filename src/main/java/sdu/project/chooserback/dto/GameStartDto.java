package sdu.project.chooserback.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class GameStartDto {

    private String sessionId;

    @NotNull
    private Integer choosenPlayerId;

    private TaskResponseDto task;
}
