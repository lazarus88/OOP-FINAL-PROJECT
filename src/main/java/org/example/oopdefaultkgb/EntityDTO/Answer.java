package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import lombok.Data;


@Data
@AllArgsConstructor
public class Answer {
    public int id;
    public int questionId;
    public String answer;
    public boolean isCorrect;
    public String status;
}
