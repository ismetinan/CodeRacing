package Questions;

import java.util.List;



public class AlgorithmTracingQuestion extends MultipleChoiceQuestion{
    private String algorithmCode;

    public AlgorithmTracingQuestion(int id, String questionText,String algorithmCode, List<String> options, String correctAnswer) {
        super(id, questionText, options, correctAnswer);
        this.algorithmCode = algorithmCode;
    }

    public String getAlgorithmCode(String algorithmCode){
        return algorithmCode;
    }
    
    
  
}
