package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quiz {
    public int id;
    public String quizName;
    public int creatorUserId;
    public boolean isRandom;
    public boolean isImmediate;
    public boolean isPracticeEnable;
    public boolean quizTypeId;
    public String status;
}
