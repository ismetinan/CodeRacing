package questions;
import java.util.List;



public class AlgorithmTracingQuestion extends Question{
    private String algorithmCode;
    private List<String> options;
    private String correctAnswer;  

    public AlgorithmTracingQuestion(int id, String questionText,String algorithmCode, List<String> options, String correctAnswer) {
        super(id, questionText);
        this.algorithmCode = algorithmCode;
        this.options=options;
        this.correctAnswer=correctAnswer;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAlgorithmCode(){
        return algorithmCode;
    }
    public boolean checkAnswer(String option){
        return correctAnswer.equals(option);
    }
    
    
  
}
