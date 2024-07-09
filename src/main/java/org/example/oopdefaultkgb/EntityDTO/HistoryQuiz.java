package org.example.oopdefaultkgb.EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistoryQuiz {
    public int id;
    public int userId;
    public int quizId;
    public LocalDateTime tookAt;
    public int duration;
    public int Score;
    public String status;
    public Boolean isPractice;
}
