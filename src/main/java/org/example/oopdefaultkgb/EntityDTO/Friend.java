package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;


@Data
@AllArgsConstructor
public class Friend {
    public int id;
    public int senderUserId;
    public int receiverUserId;
    public LocalDateTime invitedAt;
    public String status;
}
