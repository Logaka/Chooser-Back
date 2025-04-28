package sdu.project.chooserback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sdu.project.chooserback.dto.GameOptionDto;
import sdu.project.chooserback.enums.Mode;

public class TaskLevelRequiredValidator implements ConstraintValidator<TaskLevelRequired, GameOptionDto> {

    @Override
    public void initialize(TaskLevelRequired constraintAnnotation) {
    }

    @Override
    public boolean isValid(GameOptionDto gameOptionDto, ConstraintValidatorContext context) {
        if (gameOptionDto == null) {
            return true;
        }
        
        if (Mode.TASK.equals(gameOptionDto.getMode())) {
            return gameOptionDto.getTaskLevel() != null;
        }
        
        return true;
    }
} 