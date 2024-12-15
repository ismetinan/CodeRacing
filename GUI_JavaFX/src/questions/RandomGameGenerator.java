package questions;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomGameGenerator {

    public static List<Question> generateRandomQuestions() {
        // Get all questions from QuestionGenerator
        List<Question> allQuestions = QuestionGenerator.generateQuestions();

        // Shuffle the list to randomize the order
        Collections.shuffle(allQuestions);

        // Limit the list to the required number of unique questions
        return allQuestions.stream()
                .distinct() // Ensure uniqueness
                .limit(10) // Limit to the desired number
                .collect(Collectors.toList());
    }

    
}
