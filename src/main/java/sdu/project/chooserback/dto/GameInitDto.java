package sdu.project.chooserback.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import sdu.project.chooserback.models.GameOption;

import java.util.List;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class GameInitDto {

    @NotNull(message = "playerIds must be not null.")
    @NotEmpty(message = "playerIds must not be empty.")
    private List<Integer> playerIds;

    @NotNull(message = "gameOption must be not null.")
    @Valid
    private GameOptionDto gameOption;
}
