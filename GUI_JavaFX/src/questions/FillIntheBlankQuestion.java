package questions;
import java.util.List;

public class FillIntheBlankQuestion extends MultipleChoiceQuestion {
   
    public FillIntheBlankQuestion(int id, String questionText, List<String> options, String correctAnswer){
        super(id, questionText, options, correctAnswer);

    }
    public boolean isAnswerCorrect(String userAnswer) {
        // Compare user input to the correct answer (case-insensitive)
        return userAnswer.equalsIgnoreCase(getOptions().get(getCorrectAnswerIndex()));
    }
}

  

    

