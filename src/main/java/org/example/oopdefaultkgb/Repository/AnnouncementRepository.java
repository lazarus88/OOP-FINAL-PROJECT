package org.example.oopdefaultkgb.Repository;

import org.example.oopdefaultkgb.EntityDTO.Announcement;
import org.example.oopdefaultkgb.Interface.Repository.IAnnouncementRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementRepository extends  BaseRepository implements IAnnouncementRepository {
    public AnnouncementRepository() throws SQLException, ClassNotFoundException {
        super();
    }
    @Override
    public Boolean createAnnouncement(Announcement announcement) throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("insert into Answer(Announcement,AdminId,CreatedAt) values('%s',%d,'%s') ",announcement.announcement ,announcement.adminId, announcement.getCreatedAt());
        return statement.execute(query);
    }
    @Override
    public List<Announcement> getAllAnnouncement() throws SQLException {
        Statement statement = ConnectionString.createStatement();
        String query = String.format("SELECT * FROM Announcement ORDER BY CreatedAt DESC");
        ResultSet result = statement.executeQuery(query);
        List<Announcement> resultList = new ArrayList<>();
        while(result.next())
            resultList.add(new Announcement(
                    result.getString(2),
                    result.getInt(3),
                    result.getObject(4, LocalDateTime.class)
            ));
        return resultList;
    }
}
