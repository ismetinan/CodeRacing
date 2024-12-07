package Questions;

import java.util.List;

public class TrueFalseQuestion extends MultipleChoiceQuestion {
    public TrueFalseQuestion(int id, String questionText,List<String> options, String correctAnswer) {
        super(id, questionText, List.of("True", "False"), correctAnswer);
    }

    @Override
    public boolean isAnswerCorrect() {
        return super.isAnswerCorrect();
    }

    

  
}