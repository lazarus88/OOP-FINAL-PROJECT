package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@AllArgsConstructor
public class Quiz {
    public int id;
    public String quizName;
    public int creatorUserId;
    public boolean isRandom;
    public boolean IsOneVsMultiple;
    public boolean isImmediate;
    public boolean isPracticeEnable;
    public int quizTypeId;
    public String status;
    public int takenCount;
    public LocalDateTime CreatedAt;
}
