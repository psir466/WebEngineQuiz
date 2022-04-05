package engine.logic;

import java.util.ArrayList;
import java.util.List;

public class QuizWithoutAnswerDTO {

    private Long id;
    private String title;
    private String text;
    private List<String> options = new ArrayList<>();


    public QuizWithoutAnswerDTO() {
    }

    public QuizWithoutAnswerDTO(Long id, String title, String text, ArrayList<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
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


}
