package JunitTesting;

import Factory.QuestionFactory;
import Implementations.QuizImpl;
import Factory.StudentFactory;
import Interfaces.Question;
import Interfaces.QuizGenerator;
import Interfaces.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for verifying the functionality of factory classes {@code StudentFactory},
 * {@code QuestionFactory}, and {@code QuizFactory}.
 *
 * <p>This class contains test methods that validate the creation of {@code Student},
 * {@code Question}, and {@code Quiz} objects using these factories, and ensures that they behave as expected.
 * </p>
 *
 * <p>Various edge cases, such as exceeding the question pool size and incorrect answers,
 * are also tested to ensure robustness.
 * </p>
 *
 * <p>The test methods use JUnit 5 for running the tests, and assertions are used to verify the outcomes.</p>
 *
 * @author Your Name
 */
public class FactoryTest {

    /**
     * Tests the {@code StudentFactory} to ensure that a {@code Student} object is created correctly
     * and that the full name of the student matches the expected result.
     */
    @Test
    public void testStudentFactory(){
        Student student = StudentFactory.createStudent("John", "Smith", new GregorianCalendar(1990, 1, 1).getTime());
        assertNotNull(student);
        assertEquals("John Smith", student.getFullNameOfStudent());
    }

    /**
     * Tests the {@code QuestionFactory} for creating a free-response question.
     * It verifies that the correct answer is recognized and incorrect answers are handled appropriately.
     */
    @Test
    public void testQuestionFactoryFreeResponse(){
        Question question = QuestionFactory.createQuestion("free", "what is the capital of France", List.of("Paris"));
        assertTrue(question.isAnswerCorrectOrNot("Paris"));
        assertFalse(question.isAnswerCorrectOrNot("a,e"));
    }

    /**
     * Tests the {@code QuestionFactory} for creating a multiple-choice question.
     * It verifies that multiple correct answers are recognized, and answers with missing or extra options are handled correctly.
     */
    @Test
    public void testQuestionFactoryMultipleChoice(){
        Question question = QuestionFactory.createQuestion("multiple", "What are vowels?", List.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot("a, e, i"));
        assertFalse(question.isAnswerCorrectOrNot("a, e"));
        assertTrue(question.isAnswerCorrectOrNot(" a, e , i "));
    }

    /**
     * Tests the {@code QuizFactory} for creating a quiz with a given list of questions.
     * It verifies that the quiz is created successfully and that the correct number of questions are included.
     */
    @Test
    public void testQuizFactory(){
        List<Question> questions = Arrays.asList(
                QuestionFactory.createQuestion("free", "What is the capital of France?",List.of("Paris")),
                QuestionFactory.createQuestion("multiple", "Which are vowels?", List.of("a", "e", "i"))
        );

        QuizGenerator quiz = QuizImpl.createQuiz(questions, 2);
        assertNotNull(quiz);
        assertEquals(2, quiz.getQuestions().size());

    }

    /**
     * Tests the {@code QuizFactory} by attempting to create a quiz with more questions than are available in the pool.
     * This test ensures that an {@code IllegalArgumentException} is thrown when the requested number of questions exceeds the pool size.
     */
    @Test
    public void testQuizFactoryExceedingPoolSize(){
        List<Question> questions = new ArrayList<>();
        questions.add(QuestionFactory.createQuestion("free", "What is the capital of France?", List.of("Paris")));
        assertThrows(IllegalArgumentException.class, () ->{
            QuizImpl.createQuiz(questions, 2);
        });
    }
}
