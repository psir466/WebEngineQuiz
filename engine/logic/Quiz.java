package engine.logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

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
    private String title;
    private String text;

    @ElementCollection // 1
    @CollectionTable(name = "option", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "options") // 3
    private List<String> options = new ArrayList<>();

    @ElementCollection // 1
    @CollectionTable(name = "answer", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "answers") // 3
    private List<Integer> answer = new ArrayList<>();


 /*  1) The basic @ElementCollection annotation (where you can define the known fetch and targetClass preferences)
     2) The @CollectionTable annotation is very useful when it comes to giving a name to the table that'll be generated, as well as definitions like joinColumns, foreignKey's, indexes, uniqueConstraints, etc.
     3) @Column is important to define the name of the column that'll store the varchar value of the list.
   */

    private String username;

    public Quiz() {
    }

    public Quiz(Long id, String title, String text, ArrayList<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer, String username) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
