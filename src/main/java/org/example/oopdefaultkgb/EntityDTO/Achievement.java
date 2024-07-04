package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;
@Data
@AllArgsConstructor
public class Achievement {
    public int id;
    public int userId;
    public int achievementId;
    public Date achievedAt;
}
