package Questions;

import java.util.List;

public class CodeDebuggingQuestion extends Question {
    private List<String> options;
    private String codeSnippet;  
    private String correctFix;  

  
    public CodeDebuggingQuestion(int id, String questionText,List<String> options, String codeSnippet, String correctFix) {
        super(id, questionText); 
        this.options = options;
        this.codeSnippet = codeSnippet;
        this.correctFix = correctFix;
    }

   
    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public String getCorrectFix() {
        return correctFix;
    }

    public void setCorrectFix(String correctFix) {
        this.correctFix = correctFix;
    }

    
   

   
    public boolean checkAnswer(String userFix) {
        return userFix.equals(correctFix);
    }
}