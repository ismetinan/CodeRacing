package questions;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctAnswerIndex;
    private int userAnswerIndex = -1; 
    private String correctAnswer;

  
    public MultipleChoiceQuestion(int id, String questionText, List<String> options, String correctAnswer) {
        super(id, questionText);
        this.options = options;
        this.correctAnswerIndex = options.indexOf(correctAnswer);
        this.correctAnswer=correctAnswer;
    }

    
    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getUserAnswerIndex() {
        return userAnswerIndex;
    }


    public void setUserAnswerIndex(int userAnswerIndex) {
        this.userAnswerIndex = userAnswerIndex;
    }

   
    public boolean isAnswerCorrect() {
      
        return userAnswerIndex == correctAnswerIndex;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }


}