package engine.logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserQuizComplet {

    // Permet d'avoir des séquences d'incréments unique par Table
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    ///////////////////////////////
    private Long idusQuiz;

    private Long id;

    @Column(name="datetime")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public UserQuizComplet() {
    }

    public Long getIdusQuiz() {
        return idusQuiz;
    }

    public void setIdusQuiz(Long idusQuiz) {
        this.idusQuiz = idusQuiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
