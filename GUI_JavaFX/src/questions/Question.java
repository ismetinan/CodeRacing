package questions;
public abstract class Question {
    private int id;
    private String questionText;
    

    public Question(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }
    

    
}