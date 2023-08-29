import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class PersonGenerator {

    static ArrayList<String> list = new ArrayList<>();
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        boolean done = false;

        do {

            String ID = SafeInput.getRegExString(console, "Enter the ID of the person", "^[0-9]{6}$");
            String firstName = SafeInput.getNonZeroLenString(console, "Enter the first name of the person");
            String lastName = SafeInput.getNonZeroLenString(console, "Enter the last name of the person");
            String title = SafeInput.getNonZeroLenString(console, "Enter the title of the person");
            int yearOfBirth = SafeInput.getRangedInt(console, "Enter the year of birth of the person ", 0, 2023);

            String record = String.join(", ", ID, firstName, lastName, title, Integer.toString(yearOfBirth));

            list.add(record);

            done = !(SafeInput.getYNConfirm(console,"Do you want to enter another person?"));

            if (done) {
                String fileName = SafeInput.getNonZeroLenString(console, "Enter a name for a file that saves your list");

                File workingDirectory = new File(System.getProperty("user.dir"));

                Path file;

                file = Paths.get(workingDirectory.getPath()).resolve(fileName);

                try {
                    File userFile = new File((file.getFileName().toString()));
                    if (userFile.createNewFile()) {
                        try {

                            Files.newBufferedWriter(file, TRUNCATE_EXISTING);
                            Files.newInputStream(file, TRUNCATE_EXISTING);

                            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                            for (String line: list)
                            {
                                writer.write(line, 0, line.length());

                                writer.newLine();
                            }
                            writer.close();

                            System.out.println("\nThe list has been saved to the file " + file.getFileName() + "!");
                        }

                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    }

            }

        } while (!done);

    }
}