package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Mail {
    public int id;
    public int SenderUserId;
    public int ReceiverUserId;
    public String message;
    public int MailTypeId;
    public Date CreatedAt;
    public String Status;
}