package sdu.project.chooserback.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {

    private int playerSize;

    private int chosenPlayer;

    private GameOption gameOption;


}
