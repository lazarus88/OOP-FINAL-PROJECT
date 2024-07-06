package org.example.oopdefaultkgb.EntityDTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    public int id;
    public String fullName;
    public String userName;
    public String hashPassword;
    public String Status;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public long updateAdminId;
    public String Role;
}
