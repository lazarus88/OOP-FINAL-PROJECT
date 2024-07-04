package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;

import java.sql.SQLException;
import java.util.List;

public interface IAchievementRepository {
    List<Achievement> getAchievements(int userId) throws SQLException;
}
