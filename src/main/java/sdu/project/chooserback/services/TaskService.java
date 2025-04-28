package sdu.project.chooserback.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sdu.project.chooserback.enums.Level;
import sdu.project.chooserback.exceptions.ResourceNotFoundException;
import sdu.project.chooserback.models.Task;
import sdu.project.chooserback.repositories.TaskRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final Random random = new Random(); // Используем один экземпляр Random для лучшей производительности
    
    // Кеш ID заданий по уровням для быстрого доступа
    private final Map<Level, List<Long>> taskIdsCache = new ConcurrentHashMap<>();
    
    // Загружаем кеш при запуске
    @PostConstruct
    public void initCache() {
        for (Level level : Level.values()) {
            refreshCacheForLevel(level);
        }
    }
    
    // Обновление кеша для конкретного уровня
    private void refreshCacheForLevel(Level level) {
        List<Long> ids = taskRepository.findTaskIdsByLevel(level);
        if (!ids.isEmpty()) {
            taskIdsCache.put(level, ids);
        }
    }

    public Task getRandomTask(Level level) {
        // Проверяем кеш
        List<Long> taskIds = taskIdsCache.get(level);
        
        // Если в кеше нет данных, обновляем его
        if (taskIds == null || taskIds.isEmpty()) {
            refreshCacheForLevel(level);
            taskIds = taskIdsCache.get(level);
            
            if (taskIds == null || taskIds.isEmpty()) {
                throw new ResourceNotFoundException("Tasks with level " + level.name() + " do not exist.");
            }
        }
        
        // Выбираем случайный ID из списка
        Long randomTaskId = taskIds.get(random.nextInt(taskIds.size()));
        
        // Получаем задание по ID
        return taskRepository.findById(randomTaskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + randomTaskId + " not found."));
    }
}
