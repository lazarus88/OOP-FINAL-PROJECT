package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class Mail {
    public int id;
    public int SenderUserId;
    public int ReceiverUserId;
    public String message;
    public int MailTypeId;
    public LocalDateTime CreatedAt;
    public String Status;
}