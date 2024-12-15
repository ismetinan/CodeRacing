package guicoderacers;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctAnswerIndex;
    private int userAnswerIndex = -1; 

  
    public MultipleChoiceQuestion(int id, String questionText, List<String> options, String correctAnswer) {
        super(id, questionText);
        this.options = options;
        this.correctAnswerIndex = options.indexOf(correctAnswer);
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


}