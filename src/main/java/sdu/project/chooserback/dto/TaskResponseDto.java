package sdu.project.chooserback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sdu.project.chooserback.enums.Level;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {
    private String text;
    private Level level;
} 