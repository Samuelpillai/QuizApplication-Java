package JunitTesting;

import Factory.StudentFactory;
import Implementations.StudentImpl;
import Interfaces.Student;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Unit tests for the {@code Student} interface and its implementation.
 * These tests verify the creation of student objects, equality and inequality checks,
 * and proper handling of invalid input when creating students.
 *
 * <p>This class ensures that the {@code StudentFactory} and {@code StudentImp} correctly handle student creation,
 * as well as verifying that {@code equals}, {@code notSame}, and invalid data handling work as expected.</p>
 *
 * <p>The test methods use JUnit 5 for running the tests, and assertions are used to verify the expected outcomes.</p>
 *
 * <p>These tests ensure that student objects behave correctly in terms of equality, uniqueness, and validation of input data.</p>
 *
 * @author Your Name
 */
public class StudentTest {

    /**
     * Tests the creation of a {@code Student} object using the {@code StudentFactory}.
     * Verifies that the student's full name and date of birth are set correctly.
     */
    @Test
    public void testStudentCreation(){
        Student student = StudentFactory.createStudent("Jane", "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        assertEquals("Jane Doe", student.getFullNameOfStudent());
        assertEquals(new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime(), student.getDateOfBirth());
    }

    /**
     * Tests that two students created with the same name and date of birth are considered equal.
     * Verifies that the {@code equals} method works as expected for student objects.
     */
    @Test
    public void testStudentEquality(){
        Student studentOne = StudentFactory.createStudent("Jane", "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        Student studentTwo = StudentFactory.createStudent("Jane", "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        assertEquals(studentOne, studentTwo);
    }

    /**
     * Tests that two students created separately, even with the same data, are not the same object.
     * Verifies that {@code notSame} works as expected to check object uniqueness.
     */
    @Test
    public void testStudentInequality(){
        Student studentOne = new StudentImpl("John", "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        Student studentTwo = new StudentImpl("John", "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        assertNotSame(studentOne, studentTwo);
    }

    /**
     * Tests that an exception is thrown when attempting to create a student with invalid data,
     * such as a null first name.
     * Verifies that {@code IllegalArgumentException} is thrown for invalid input.
     */
    @Test
    public void testStudentInvalidDate(){
        assertThrows(IllegalArgumentException.class, () -> {
            StudentFactory.createStudent(null, "Doe", new GregorianCalendar(1992, Calendar.FEBRUARY, 15).getTime());
        });
    }
}
