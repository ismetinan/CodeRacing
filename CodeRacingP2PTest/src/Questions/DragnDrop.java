package Questions;

import java.util.List;

public class DragnDrop extends Question {
    private List<String> draggableItems;
    private List<String> droppableTargets;
    private List<String> userAnswer;
    private List<String> correctAnswer;

   
    public DragnDrop(int id, String questionText, List<String> draggableItems, List<String> droppableTargets, List<String> correctAnswer) {
        super(id, questionText);
        
        this.draggableItems = draggableItems;
        this.droppableTargets = droppableTargets;
        this.correctAnswer = correctAnswer;
        this.userAnswer = null; 
        
    }

    
    public List<String> getDraggableItems() {
        return draggableItems;
    }

    public List<String> getDroppableTargets() {
        return droppableTargets;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    
    public void setUserAnswer(List<String> userAnswer) {

        this.userAnswer = userAnswer;
    }

    
    public boolean isAnswerCorrect() {
        
        return userAnswer.equals(correctAnswer);
    }


   
   
}