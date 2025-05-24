package JunitTesting;

import Interfaces.StudentStatistics;
import Model.StudentStatisticsImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Unit tests for the {@code StudentStatistics} implementation, specifically testing
 * the functionality of tracking quiz scores, calculating average scores, and generating a final verdict.
 *
 * <p>This class contains test methods to ensure that the {@code StudentStatisticsImp} class correctly
 * tracks quiz scores, handles invalid scores, and computes the final verdict based on student performance.</p>
 *
 * <p>It includes tests for edge cases such as invalid score inputs and ensures that the average quiz scores
 * and last quiz scores are correctly calculated.</p>
 *
 * <p>The test methods use JUnit 5 for running the tests, and assertions are used to verify the expected outcomes.</p>
 *
 * <p>These tests ensure that the student statistics functionality works correctly for tracking and evaluating quiz performance.</p>
 *
 * @author Your Name
 */
public class StudentStatisticsTest {

    /**
     * Tests that quiz scores are correctly tracked and stored within the student statistics object.
     * It verifies that the correct number of scores is recorded and that the individual scores match the expected values.
     */
    @Test
    public void testQuizScoreTraking(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.7);
        studentStatistics.addQuizScore(0.3);

        List<Double> scores = studentStatistics.getQuizScores();
        assertEquals(2, scores.size());
        assertEquals(0.7, scores.get(0), 0.0001);
        assertEquals(0.3, scores.get(1), 0.0001);
    }

    /**
     * Tests that the final verdict is generated correctly based on the student's quiz performance.
     * If any quiz score is 0.5 or higher, the verdict should be "PASS". Otherwise, it should reflect the student's performance.
     */
    @Test
    public void testFinalVerdict(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.6);
        studentStatistics.addQuizScore(0.3);
        String verdict = studentStatistics.getFinalVerdict();
        assertEquals("PASS", verdict);

        studentStatistics.addQuizScore(0.4);
        assertEquals("PASS", studentStatistics.getFinalVerdict());
    }

    /**
     * Tests that adding a single quiz score correctly tracks the score and increases the number of tracked quiz scores by one.
     */
    @Test
    public void testAddQuizScore(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.7);
        assertEquals(1, studentStatistics.getQuizScores().size());
    }

    /**
     * Tests that multiple quiz scores can be tracked correctly and verifies that the number of stored scores is accurate.
     */
    @Test
    public void testTrackMultipleQuizScores(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.7);
        studentStatistics.addQuizScore(0.3);
        assertEquals(2, studentStatistics.getQuizScores().size());
    }

    /**
     * Tests that the average quiz score is correctly calculated based on the student's recorded scores.
     * It verifies that the calculated average matches the expected result.
     */
    @Test
    public void testGetQuizAverageScore(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.7);
        studentStatistics.addQuizScore(0.3);
        assertEquals(0.5, studentStatistics.getQuizAverageScore(), 0.01);
    }

    /**
     * Tests that the last quiz score is correctly retrieved from the student statistics object.
     * Verifies that the last recorded score matches the expected value.
     */
    @Test
    public void testGetLastQuizScore(){
        StudentStatistics studentStatistics= new StudentStatisticsImpl();
        studentStatistics.addQuizScore(0.7);
        assertEquals(0.7, studentStatistics.getLastQuizScore(), 0.001);
    }

    /**
     * Tests that an {@code IllegalArgumentException} is thrown when an invalid quiz score (outside the 0.0 to 1.0 range) is added.
     */
    @Test
    public void testInvalidScoreException(){
        StudentStatistics studentStatistics = new StudentStatisticsImpl();
        assertThrows(IllegalArgumentException.class, () -> studentStatistics.addQuizScore(1.5));
    }
}
