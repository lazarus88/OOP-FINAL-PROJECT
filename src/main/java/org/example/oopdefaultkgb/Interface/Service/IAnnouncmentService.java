package org.example.oopdefaultkgb.Interface.Service;

import org.example.oopdefaultkgb.EntityDTO.Announcement;

import java.sql.SQLException;
import java.util.List;

public interface IAnnouncmentService{
    boolean addAnnouncment(int userId, String announcement) throws SQLException;

    List<Announcement> getAllAnnouncement() throws SQLException;
}
