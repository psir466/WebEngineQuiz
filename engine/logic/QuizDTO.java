package engine.logic;

import java.util.ArrayList;
import java.util.List;

public class QuizDTO {

    private String title;
    private String text;
    private List<String> options = new ArrayList<>();


    public QuizDTO() {
    }

    public QuizDTO(String title, String text, ArrayList<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
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
