package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Achievement;
import org.example.oopdefaultkgb.EntityDTO.Friend;
import org.example.oopdefaultkgb.EntityDTO.User;
import org.example.oopdefaultkgb.Interface.Repository.IAchievementRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AchievementRepository extends BaseRepository implements IAchievementRepository {
    public AchievementRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    @Override
    public List<Achievement> getAchievements(int userId) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM userAchievement WHERE UserId = %d ORDER BY AchievedAt DESC", userId);
        ResultSet result = statement.executeQuery(query);
        List<Achievement> resultList = new ArrayList<Achievement>();
        while(result.next())
            resultList.add(new Achievement(
                    result.getInt(1),
                    result.getInt(2),
                    result.getInt(3),
                    result.getObject(4, LocalDateTime.class)
            ));
        return resultList;
    }
}
