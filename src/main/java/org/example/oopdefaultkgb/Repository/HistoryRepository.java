package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.HistoryQuiz;
import org.example.oopdefaultkgb.Interface.Repository.IHistoryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository  extends  BaseRepository implements IHistoryRepository {
    public HistoryRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public List<HistoryQuiz> getRecentQuizList(int userId) throws SQLException {
        List<HistoryQuiz> historyQuizList = new ArrayList<>();
        Statement statement =ConnectionString.createStatement();
        String query = String.format("SELECT * FROM UserQuizHistory WHERE UserId =%d ORDER BY TookAt DESC", userId);
        ResultSet res = statement.executeQuery(query);
        while(res.next())
            historyQuizList.add(new HistoryQuiz(
                    res.getInt(1), res.getInt(2),
                    res.getInt(3), res.getObject(4, LocalDateTime.class),
                    res.getInt(5), res.getInt(6),
                    res.getString(7), res.getBoolean(8)));
        return historyQuizList;
    }
}
