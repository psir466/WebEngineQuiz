package engine.logic;

import java.time.LocalDateTime;

public class UserQuizCompletDTO {

    private Long id;

    private LocalDateTime completedAt;

    public UserQuizCompletDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
