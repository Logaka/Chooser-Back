package sdu.project.chooserback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sdu.project.chooserback.enums.Level;
import sdu.project.chooserback.models.Task;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {

    // Более эффективный способ - сначала получить все ID заданий нужного уровня
    @Query("SELECT t.id FROM Task t WHERE t.level = :level")
    List<Long> findTaskIdsByLevel(@Param("level") Level level);
    
    // Получить задание по ID
    Optional<Task> findById(Long id);
}
