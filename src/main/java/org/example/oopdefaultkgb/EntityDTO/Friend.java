package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import lombok.Data;


@Data
@AllArgsConstructor
public class Friend {
    public int id;
    public int senderUserId;
    public int receiverUserId;
    public Date invitedAt;
    public String status;
}
