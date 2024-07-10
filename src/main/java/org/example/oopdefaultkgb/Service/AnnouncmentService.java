package org.example.oopdefaultkgb.Service;

import org.example.oopdefaultkgb.EntityDTO.Announcement;
import org.example.oopdefaultkgb.Interface.Repository.IAnnouncementRepository;
import org.example.oopdefaultkgb.Interface.Service.IAnnouncmentService;
import org.example.oopdefaultkgb.Repository.AnnouncementRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AnnouncmentService implements IAnnouncmentService {
    private IAnnouncementRepository announcementRepository;
    public AnnouncmentService  () throws SQLException, ClassNotFoundException {
        announcementRepository = new AnnouncementRepository();
    }
    @Override
    public boolean addAnnouncment(int userId, String announcement) throws SQLException {
        Announcement announcementModel = new Announcement(announcement, userId, LocalDateTime.now());
        return announcementRepository.createAnnouncement(announcementModel);
    }
    @Override
    public List<Announcement> getAllAnnouncement() throws SQLException {
        return announcementRepository.getAllAnnouncement();
    }
}
