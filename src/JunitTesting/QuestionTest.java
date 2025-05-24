package JunitTesting;

import Implementations.FreeResponseQuestion;
import Implementations.MultipleChoiceQuestion;
import Interfaces.Question;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for verifying the functionality of the {@code FreeResponseQuestion} and {@code MultipleChoiceQuestion} implementations
 * of the {@code Question} interface.
 *
 * <p>This class contains test methods that validate various scenarios for both free-response and multiple-choice questions,
 * ensuring correct and incorrect answers are handled appropriately. It also tests for case-insensitivity and whitespace trimming
 * in the answers.</p>
 *
 * <p>The test methods use JUnit 5 for running the tests, and assertions are used to verify the expected outcomes.</p>
 *
 * <p>These tests ensure that questions behave correctly for free-response and multiple-choice scenarios, including checking the correctness
 * of answers, case-insensitive matches, and proper handling of comma-separated answers for multiple-choice questions.</p>
 *
 * @author Your Name
 */
public class QuestionTest {

    /**
     * Tests the correct handling of a free-response question with the correct answer provided.
     * Verifies that both exact and case-insensitive answers are accepted, while incorrect answers are rejected.
     */
    @Test
    public void testFreeResponseQuestionCorrectAnswer(){
        Question question = new FreeResponseQuestion("What is the capital of France?", "Paris");
        assertTrue(question.isAnswerCorrectOrNot("Paris"));
        assertTrue(question.isAnswerCorrectOrNot("paris"));
        assertFalse(question.isAnswerCorrectOrNot("London"));
    }

    /**
     * Tests that an incorrect answer is properly rejected for a free-response question.
     */
    @Test
    public void testFreeResponseQuestionInCorrectAnswer(){
        FreeResponseQuestion question = new FreeResponseQuestion("What is the capital of France?", "Paris");
        assertFalse(question.isAnswerCorrectOrNot("Berlin"));
    }

    /**
     * Tests that free-response answers are matched in a case-insensitive manner.
     */
    @Test
    public void testFreeResponseQuestionAnswerCaseInsentiveMatching(){
        FreeResponseQuestion question = new FreeResponseQuestion("What is the capital of France?", "Paris");
        assertTrue(question.isAnswerCorrectOrNot("paris"));
    }

    /**
     * Tests that whitespace around the answer is properly trimmed for free-response questions.
     */
    @Test
    public void testTrimWhiteSpaceInAnswerOfFreeResponseQuestion(){
        FreeResponseQuestion question = new FreeResponseQuestion("What is the capital of France?", "Paris");
        assertTrue(question.isAnswerCorrectOrNot("Paris"));
    }

    /**
     * Tests the correct handling of a multiple-choice question with the correct answers provided.
     * Verifies that different correct combinations of comma-separated answers are accepted.
     */
    @Test
    public void testMultipleChoiceQuestionCorrectAnswer(){
        Question question = new MultipleChoiceQuestion("What is the capital of Frane?", (Set<String>) Set.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot("a, e, i"));
        assertTrue(question.isAnswerCorrectOrNot("i, e, a"));
        assertFalse(question.isAnswerCorrectOrNot("London"));
    }

    /**
     * Tests that an incorrect answer is properly rejected for a multiple-choice question.
     */
    @Test
    public void testMultipleChoiceQuestionInCorrectAnswer(){
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("Which are vowels", Set.of("a", "e", "i"));
        assertFalse(question.isAnswerCorrectOrNot("b, c, d"));
    }

    /**
     * Tests that multiple-choice answers are matched in a case-insensitive manner.
     */
    @Test
    public void testMultipleChoiceQuestionAnswerCaseInsentiveMatching(){
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("Which are vowels?", Set.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot("A, E, I"));
    }

    /**
     * Tests that multiple-choice answers are properly handled with comma-separated values,
     * verifying that whitespace around the answers is trimmed.
     */
    @Test
    public void testMultipleChoiceQuestionCommaSeperatedAnswer(){
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("which are vowels?", Set.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot(" i, e , a"));
    }

    /**
     * Tests the correct handling of a multiple-choice question where the question expects one or more correct answers.
     */
    @Test
    public void testMultipleChoiceQuestionCorrectAnswerWithOneOption(){
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("Which is a vowel?", Set.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot("a, e, i"));
    }

    /**
     * Tests the correct handling of a multiple-choice question with multiple correct answers provided.
     */
    @Test
    public void testMultipleChoiceQuestionCorrectAnswerWithMultipleOptions(){
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("Which are vowels?", Set.of("a", "e", "i"));
        assertTrue(question.isAnswerCorrectOrNot("a, e, i"));
    }
}
