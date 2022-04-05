package engine.logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "user")
public class User {

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
    private Long id;
    private String userName;
    private String password;
    private boolean isActive;
    private String roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserQuizComplet> lsQuiz = new ArrayList<>();


    public User() {

    }

    public User(String userName, String password, boolean isActive, String roles) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<UserQuizComplet> getLsQuiz() {
        return lsQuiz;
    }

    public void setLsQuiz(List<UserQuizComplet> lsQuiz) {
        this.lsQuiz = lsQuiz;
    }
}