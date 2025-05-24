package Factory;

import Implementations.FreeResponseQuestion;
import Implementations.MultipleChoiceQuestion;
import Interfaces.Question;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Factory class for creating instances of {@code Question} objects.
 * This class provides a static method to create different types of questions, such as free-response
 * and multiple-choice questions, based on the specified type.
 * <p>
 * The method allows for flexible creation of question objects by passing in the question type, text,
 * and the correct answers.
 * </p>
 *
 * <p> The factory supports the creation of two question types:
 * <ul>
 *   <li>Free-response questions</li>
 *   <li>Multiple-choice questions</li>
 * </ul>
 * </p>
 *
 * <p> If an unsupported question type is provided, an {@code IllegalArgumentException} is thrown.
 * </p>
 *
 * @author Your Name
 */
public class QuestionFactory {

    /**
     * Creates and returns a new {@code Question} object based on the specified type.
     *
     * @param type the type of question to create, either "free" for free-response or "multiple" for multiple-choice.
     * @param questionText the text of the question.
     * @param correctOptions a {@code List} of correct answer options. For free-response, the first item in the list is the correct answer; for multiple-choice, all items are correct options.
     * @return a {@code Question} object, either a {@code FreeResponseQuestion} or {@code MultipleChoiceQuestion}.
     * @throws IllegalArgumentException if an unsupported question type is provided or if the correct options list is invalid.
     */
    public static Question createQuestion(String type, String questionText, List<String> correctOptions){
        if("free".equalsIgnoreCase(type)){
            // Create a free-response question with the first option as the correct answer
            return new FreeResponseQuestion(questionText, correctOptions.get(0));
        } else if("multiple".equalsIgnoreCase(type)){
            // Create a multiple-choice question with a set of correct options
            return new MultipleChoiceQuestion(questionText, new HashSet<>(correctOptions));
        } else {
            // Throw an exception for unknown question types
            throw new IllegalArgumentException("Unknown question type: "+ type);
        }
    }
}
