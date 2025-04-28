package sdu.project.chooserback.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdu.project.chooserback.enums.Level;
import sdu.project.chooserback.enums.Mode;
import sdu.project.chooserback.validation.TaskLevelRequired;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TaskLevelRequired
public class GameOptionDto {
    @NotNull(message = "Mode must be not null.")
    private Mode mode;
    private Level taskLevel;
}
