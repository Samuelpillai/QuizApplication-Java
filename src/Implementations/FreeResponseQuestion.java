package Implementations;

import Interfaces.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a free-response question, where the answer is a single correct response
 * or a set of correct answers. Implements the {@code Question} interface, providing
 * functionality to retrieve the question text, check the correctness of a free-response
 * answer, and retrieve the correct answer.
 * <p>
 * The class ensures that the correct answer is not null or empty. Answers are case-insensitive
 * and trimmed for comparison.
 * </p>
 *
 * <p> This class is suitable for use in quizzes or assessments where a free-text answer is required.
 * </p>
 *
 * @author Your Name
 */
public class FreeResponseQuestion implements Question {

    private final String[] correctAnswer;
    private final String questionText;

    /**
     * Constructs a {@code FreeResponseQuestion} with the specified question text and correct answer.
     *
     * @param questionText the text of the question, must not be null or empty.
     * @param correctAnswer the correct answer for the question, must not be null or empty.
     * @throws IllegalArgumentException if the correct answer is null or empty.
     */
    public FreeResponseQuestion(String questionText, String correctAnswer) {
        this.questionText = questionText;
        if (correctAnswer == null || correctAnswer.trim().isEmpty()){
            throw new IllegalArgumentException("Correct answer cannot be null or empty");
        }
        this.correctAnswer = new String[]{correctAnswer};
    }

    /**
     * Retrieves the text of the question.
     *
     * @return the question text as a {@code String}.
     */
    @Override
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Checks if the given answer matches the correct answer for this free-response question.
     * <p>
     * The method compares the given answer with the correct answer(s), ignoring case and leading/trailing spaces.
     * For multiple correct answers (if separated by commas), it checks whether the sets of answers match.
     * </p>
     *
     * @param answer the answer provided by the user, may be a single answer or comma-separated values.
     * @return {@code true} if the given answer matches the correct answer(s), {@code false} otherwise.
     */
    @Override
    public boolean isAnswerCorrectOrNot(String answer) {
        if (answer == null){
            return false;
        }

        Set<String> answerSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (String answerElement : correctAnswer){
            answerSet.add(answerElement.trim().toLowerCase());
        }

        if(answer.contains(",")){
            String[] splitAnswers = answer.split(",");
            Set<String> inputAnswerSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

            for (String ans: splitAnswers){
                inputAnswerSet.add(ans.trim().toLowerCase());
            }
            return answerSet.equals(inputAnswerSet);
        }
        return Arrays.stream(correctAnswer).anyMatch(correctAns -> correctAns.trim().equalsIgnoreCase(answer.trim()))
                || answerSet.contains(answer.trim().toLowerCase());
    }

    /**
     * Retrieves the correct answer(s) for the question.
     *
     * @return a {@code List} of {@code String} representing the correct answer(s).
     */
    @Override
    public List<String> getCorrectAnswers() {
        return List.of(correctAnswer);
    }

    /**
     * Returns a string representation of the free-response question.
     *
     * @return a {@code String} describing the free-response question.
     */
    @Override
    public String toString() {
        return "FreeResponseQuestion: " + questionText;
    }
}
