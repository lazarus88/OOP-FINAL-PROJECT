package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Announcement {
    public int id;
    public String announcement;
    public int adminId;
    public LocalDateTime CreatedAt;
    public  Announcement(String announcement, int adminId, LocalDateTime CreatedAt) {
        this.announcement = announcement;
        this.adminId = adminId;
        this.CreatedAt = CreatedAt;
    }
}

