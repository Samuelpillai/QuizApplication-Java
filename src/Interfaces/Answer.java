package Interfaces;

/**
 * Represents an answer provided by a student during a quiz.
 * Stores the text of the answer and indicates whether it is correct or not.
 * <p>
 * This class encapsulates the details of an individual answer, including the answer text and its correctness.
 * </p>
 *
 * @author Your Name
 */
public class Answer {
    private String answerText;
    private boolean isCorrectOrNot;
    /**
     * Constructs an {@code Answer} with the specified answer text and correctness.
     *
     * @param answerText the {@code String} representing the text of the answer.
     * @param isCorrectOrNot a {@code boolean} indicating whether the answer is correct or not.
     */
    public Answer(String answerText, boolean isCorrectOrNot){
        this.answerText = answerText;
        this.isCorrectOrNot = isCorrectOrNot;
    }
    /**
     * Checks if the answer is correct.
     *
     * @return {@code true} if the answer is correct, {@code false} otherwise.
     */
    public boolean isCorrectOrNot(){
        return isCorrectOrNot;
    }
}
