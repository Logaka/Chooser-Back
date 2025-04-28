package sdu.project.chooserback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdu.project.chooserback.enums.Level;
import sdu.project.chooserback.enums.Mode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameOption {
    private Mode mode;
    private Level level;
}
