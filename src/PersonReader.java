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
public class PersonReader {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.printf("\n");
                System.out.printf("%-12s  %-12s %-12s %-12s %-12s\n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                System.out.printf("===========================================================\n");

                while (reader.ready())
                {
                    rec = reader.readLine();

                    String[] values = rec.split(",");

                    String ID = values[0];
                    String firstName = values[1];
                    String lastName = values[2];
                    String title = values[3];
                    String yearOfBirth = values[4];

                    System.out.printf("%-12s %-12s %-12s %-12s %-12s\n", ID, firstName, lastName, title, yearOfBirth);

                }

                reader.close();

            }

            else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
