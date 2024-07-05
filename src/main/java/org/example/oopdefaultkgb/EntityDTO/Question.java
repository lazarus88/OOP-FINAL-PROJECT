package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import java.sql.Date;
import lombok.Data;
@Data
@AllArgsConstructor
public class Question {
    public int id;
    public int quizId;
    public String question;
    public int QuestionTypeId;
    public String status;
}

