package org.example.oopdefaultkgb.EntityDTO;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class User {
    public int id;
    public String fullName;
    public String userName;
    public String hashPassword;
    public Date createdAt;
    public Date updatedAt;
    public long updateAdminId;
    public String Role;
}
