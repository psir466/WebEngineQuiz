package engine.logic;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    private List<Integer> answer = new ArrayList<>();

    public Answer() {
    }

    public Answer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
