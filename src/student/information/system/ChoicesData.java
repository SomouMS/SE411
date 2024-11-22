package student.information.system;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Class for storing and writing student choices to a file.
 *
 * @author Pawan Saxena
 */
public class ChoicesData {

    // Constants
    private static final String FILE_NAME = "choices.txt";

    // Class-level Writers
    private static Writer choicesWriter;

    // Instance variables
    private String cChoice1;
    private String cChoice2;
    private String cChoice3;
    private String cChoice4;
    private String cChoice5;
    private String bChoice1;
    private String bChoice2;
    private String bChoice3;
    private String bChoice4;
    private String bChoice5;

    /**
     * Constructor to initialize student choices and store them.
     *
     * @param student   The student's name or identifier.
     * @param cChoice1  Course choice 1.
     * @param cChoice2  Course choice 2.
     * @param cChoice3  Course choice 3.
     * @param cChoice4  Course choice 4.
     * @param cChoice5  Course choice 5.
     * @param bChoice1  Branch choice 1.
     * @param bChoice2  Branch choice 2.
     * @param bChoice3  Branch choice 3.
     * @param bChoice4  Branch choice 4.
     * @param bChoice5  Branch choice 5.
     * @throws IOException if there's an issue writing to the file.
     */
    public ChoicesData(
            String student,
            String cChoice1, String cChoice2, String cChoice3, String cChoice4, String cChoice5,
            String bChoice1, String bChoice2, String bChoice3, String bChoice4, String bChoice5
    ) throws IOException {

        this.cChoice1 = cChoice1;
        this.cChoice2 = cChoice2;
        this.cChoice3 = cChoice3;
        this.cChoice4 = cChoice4;
        this.cChoice5 = cChoice5;
        this.bChoice1 = bChoice1;
        this.bChoice2 = bChoice2;
        this.bChoice3 = bChoice3;
        this.bChoice4 = bChoice4;
        this.bChoice5 = bChoice5;

        // Store the data
        store(student);
    }

    /**
     * Writes the student's choices to the file.
     *
     * @param student The student's name or identifier.
     * @throws IOException if there's an issue writing to the file.
     */
    private void store(String student) throws IOException {
        // Using try-with-resources to ensure proper closure of Writer
        try (FileWriter choicesWriter = new FileWriter(FILE_NAME, true)) {
            // Write a new line for separation
            choicesWriter.write(System.lineSeparator());

            // Format the data as a single line
            String data = String.format(
                    "%s Choice-1: %s Choice-2: %s Choice-3: %s Choice-4: %s Choice-5: %s " +
                            "Branch-Choice-1: %s Branch-Choice-2: %s Branch-Choice-3: %s Branch-Choice-4: %s Branch-Choice-5: %s",
                    student, cChoice1, cChoice2, cChoice3, cChoice4, cChoice5, bChoice1, bChoice2, bChoice3, bChoice4, bChoice5
            );

            // Write the data to the file
            choicesWriter.write(data);
            choicesWriter.write(System.lineSeparator());
        } catch (IOException e) {
            throw new IOException("Error writing choices to file: " + e.getMessage(), e);
        }
    }
}
