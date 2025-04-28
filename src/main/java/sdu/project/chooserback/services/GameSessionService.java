package sdu.project.chooserback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sdu.project.chooserback.dto.GameInitDto;
import sdu.project.chooserback.dto.GameStartDto;
import sdu.project.chooserback.dto.TaskResponseDto;
import sdu.project.chooserback.enums.Level;
import sdu.project.chooserback.enums.Mode;
import sdu.project.chooserback.models.GameOption;
import sdu.project.chooserback.models.GameSession;
import sdu.project.chooserback.models.Task;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class GameSessionService {

    private Map<String, GameSession> sessions = new ConcurrentHashMap<>();
    private final TaskService taskService;

    public GameStartDto startGame(GameInitDto dto) {
        GameStartDto gameStartDto = new GameStartDto();
        List<Integer> playerIds = dto.getPlayerIds();
        
        Integer choosenPlayerId = chooseRandomPlayerId(playerIds);
        gameStartDto.setChoosenPlayerId(choosenPlayerId);
        
        Mode mode = dto.getGameOption().getMode();
        
        if (Mode.TASK.equals(mode)) {
            String sessionId = UUID.randomUUID().toString();
            gameStartDto.setSessionId(sessionId);
            
            Level taskLevel = dto.getGameOption().getTaskLevel();
            Task task = taskService.getRandomTask(taskLevel);
            
            TaskResponseDto taskResponse = new TaskResponseDto();
            taskResponse.setText(task.getTask());
            taskResponse.setLevel(task.getLevel());
            gameStartDto.setTask(taskResponse);
            
            GameOption gameOption = new GameOption(mode, taskLevel);
            GameSession gameSession = new GameSession(playerIds.size(), choosenPlayerId, gameOption);
            sessions.put(sessionId, gameSession);
        } else {
            gameStartDto.setSessionId(null);
            gameStartDto.setTask(null);
        }
        
        return gameStartDto;
    }
    
    public Integer chooseRandomPlayerId(List<Integer> playerIds) {
        if (playerIds == null || playerIds.isEmpty()) {
            throw new IllegalArgumentException("Player IDs list cannot be empty");
        }
        
        int randomIndex = ThreadLocalRandom.current().nextInt(playerIds.size());
        return playerIds.get(randomIndex);
    }
}
