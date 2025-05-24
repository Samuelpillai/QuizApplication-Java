package JunitTesting;

import Factory.StudentFactory;
import Interfaces.Question;
import Interfaces.QuizGenerator;
import Interfaces.Student;
import Model.QuizSystem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@code QuizSystem} class, testing the core functionality of generating quizzes,
 * taking quizzes, handling attempts, and generating revision quizzes based on student performance.
 *
 * <p>This class includes various test scenarios to verify that the quiz system correctly handles quiz generation,
 * tracks quiz attempts, restricts students to two attempts, and generates revision quizzes based on incorrect answers.</p>
 *
 * <p>The test methods use JUnit 5 for running the tests, and assertions are used to verify the expected outcomes.</p>
 *
 * <p>It tests the behavior of the system when generating quizzes with different conditions,
 * managing student quiz attempts, and allowing or restricting further attempts based on performance.</p>
 *
 * @author Your Name
 */
public class QuizSystemTest {

    private QuizSystem quizSystem = new QuizSystem();
    private Student student = StudentFactory.createStudent("John","Doe",
            new GregorianCalendar(1995,1,1).getTime());

    /**
     * Tests the quiz generation functionality to ensure that a quiz with the correct number of questions is generated.
     */
    @Test
    public void testGenerateRegularQuiz(){
        QuizGenerator quiz = quizSystem.generateQuiz(5);
        assertNotNull(quiz);
        assertEquals(5, quiz.getQuestions().size());
    }

    /**
     * Tests that a student is allowed a maximum of two quiz attempts, and an exception is thrown if a third attempt is made.
     */
    @Test
    public void testMaxQuizAttempts(){
        QuizGenerator quiz = quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, quiz, Arrays.asList("Paris", "Berlin", "Tokyo", "New Delhi", "Canberra"));
        quizSystem.takeQuiz(student, quiz, Arrays.asList("Paris", "Berlin", "Tokyo", "New Delhi", "Canberra"));
        assertThrows(IllegalArgumentException.class, () ->{
            quizSystem.takeQuiz(student, quiz, Arrays.asList("Paris", "Berlin", "Tokyo", "New Delhi", "Canberra"));
        });
    }

    /**
     * Tests the scoring of the first regular quiz attempt, ensuring the score is valid and between 0 and 1.0.
     */
    @Test
    public void testFirstRegularQuiz(){
        QuizGenerator quiz = quizSystem.generateQuiz(5);
        List<String> answers = Arrays.asList("Paris", "Berlin", "Tokyo", "New Delhi", "Canberra");
        double score = quizSystem.takeQuiz(student, quiz, answers);
        assertTrue(score >= 0 && score <= 1.0);
    }

    /**
     * Tests that a second quiz attempt is allowed if the first attempt fails.
     */
    @Test
    public void testSecondRegularQuizAllowedAfterFirstFail(){
        QuizGenerator firstQuiz = quizSystem.generateQuiz(5);
        List<String> firstAttemptAnswers = Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong");
        double firstScore = quizSystem.takeQuiz(student, firstQuiz, firstAttemptAnswers);
        System.out.println("First Second: "+firstScore);
        assertTrue(firstScore < 0.5);

        QuizGenerator secondQuiz = quizSystem.generateQuiz(5);
        List<String> secondAttemptAnswers = Arrays.asList("Paris", "Berlin", "Tokyo", "New Delhi", "Canberra");
        double secondScore = quizSystem.takeQuiz(student, secondQuiz, secondAttemptAnswers);

        System.out.println("Second Score: "+ secondScore);
        System.out.println("Number of attempts after second quiz: "+quizSystem.getQuizAttempts(student));
        assertTrue(secondScore >= 0 && secondScore <= 1.0);
    }

    /**
     * Tests that no further quiz attempts are allowed after two failed regular quizzes.
     */
    @Test
    public void testNoMoreAttemptsAfterTwoRegularQuizzes(){
        QuizGenerator firstQuiz = quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, firstQuiz, Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong"));

        QuizGenerator secondQuiz = quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, secondQuiz, Arrays.asList("Paris", "Berlin", "tokyo", "New Delhi", "Canberra"));

        assertThrows(IllegalArgumentException.class, () -> {
            QuizGenerator thirdQuiz = quizSystem.generateQuiz(5);
            quizSystem.takeQuiz(student, thirdQuiz, Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong"));
        });
    }

    /**
     * Tests that a revision quiz is generated after two failed quiz attempts.
     * The revision quiz should be based on the student's incorrect answers.
     */
    @Test
    public void testRevisionQuizAfterSecondFail(){
        QuizGenerator firstQuiz = quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, firstQuiz, Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong"));

        QuizGenerator secondQuiz = quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, secondQuiz, Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong"));

        QuizGenerator revisionQuiz = quizSystem.generateRevisionQuiz(student);

        assertNotNull(revisionQuiz);
        assertTrue(revisionQuiz.getQuestions().size() > 0);
    }

    /**
     * Tests that the revision quiz is generated based on the student's incorrect answers in previous quizzes.
     */
    @Test
    public void testRevisionQuizBasedOnFirstIncorrectQuestions(){
        QuizGenerator quiz =quizSystem.generateQuiz(5);
        quizSystem.takeQuiz(student, quiz, Arrays.asList("wrong", "wrong", "wrong", "wrong", "wrong"));
        QuizGenerator revisionQuiz = quizSystem.generateRevisionQuiz(student);
        assertNotNull(revisionQuiz);
    }
}
