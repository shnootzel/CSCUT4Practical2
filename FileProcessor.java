import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileProcessor {

    public static void main(String[] args) {
        File inputFile = new File("C:\\Users\\joshua\\Downloads\\CSCUT4Practical2-main\\CSCUT4Practical2-main\\input.txt");
        File outputFile = new File("output.txt");

        try {
            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                StringBuilder formattedName = new StringBuilder();
                String formattedDate = "";

                // format name
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];

                    if (i == 0) {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(formattedWord);
                    } else if (i == 1 && word.length() == 2) {
                        formattedName.append(". ").append(word.toUpperCase());
                    } else if (i == words.length - 1) {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(" ").append(formattedWord);
                    } else {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(" ").append(formattedWord);
                    }
                }

                // format date
                int dateIndex = line.lastIndexOf(" ");
                String dateString = line.substring(dateIndex + 1);
                String day = dateString.substring(0, 2);
                String month = dateString.substring(2, 4);
                String year = dateString.substring(4);
                formattedDate = day + "/" + month + "/" + year;

                // write formatted output to file
                writer.println(formattedName.toString() + " " + formattedDate);
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}