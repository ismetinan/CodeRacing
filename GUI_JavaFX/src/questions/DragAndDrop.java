package questions;
import java.util.List;

public class DragAndDrop extends Question {
    private List<String> draggableItems;
    private List<String> droppableTargets;
    private List<String> userAnswer;
    private List<String> correctAnswer;

   
    public DragAndDrop(int id, String questionText, List<String> draggableTargets, List<String> droppableItems, List<String> correctAnswer) {
        super(id, questionText);
        
        this.draggableItems = draggableTargets;
        this.droppableTargets = droppableItems;
        this.correctAnswer = correctAnswer;
        this.userAnswer = null; 
        
    }
    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
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