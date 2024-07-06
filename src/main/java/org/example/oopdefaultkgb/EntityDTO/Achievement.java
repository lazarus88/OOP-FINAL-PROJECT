package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Achievement {
    public int id;
    public int userId;
    public int achievementId;
    public LocalDateTime achievedAt;
}
