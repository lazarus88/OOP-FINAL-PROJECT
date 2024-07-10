package org.example.oopdefaultkgb.Interface.Repository;

import org.example.oopdefaultkgb.EntityDTO.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface IAnnouncementRepository {
    Boolean createAnnouncement(Announcement announcement) throws SQLException;

    List<Announcement> getAllAnnouncement() throws SQLException;
}
