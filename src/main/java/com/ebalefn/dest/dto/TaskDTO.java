package com.ebalefn.dest.dto;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate deadlineDate;
    private int status; // 0 - created, 1 - in progress, 2 - done, 3 - failed
    private Long assignedUserId; // ID пользователя, которому назначена задача
    private UserDTO assignedUser;
}