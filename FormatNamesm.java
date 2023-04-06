import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FormatNamesm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        String inputFileName = scanner.nextLine();
        System.out.print("Enter output file name: ");
        String outputFileName = scanner.nextLine();

        try {
            Scanner inputFileScanner = new Scanner(new File(inputFileName));
            PrintWriter outputFileWriter = new PrintWriter(outputFileName);

            while (inputFileScanner.hasNextLine()) {
                String line = inputFileScanner.nextLine();
                String[] words = line.split(" ");
                StringBuilder formattedName = new StringBuilder();
                String formattedDate = "";

                // Format name
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];

                    if (i == 0) {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(formattedWord);
                    } else if (i == 1 && word.length() == 2) {
                        formattedName.append(" ").append(word.substring(0, 1).toUpperCase()).append(". ");
                        formattedName.append(word.substring(1).toUpperCase());
                    } else if (i == words.length - 1) {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(" ").append(formattedWord.replaceAll("[0-9]", ""));
                        if (words.length > 2 && words[1].length() == 1) {
                            formattedName.insert(formattedName.indexOf(" ") + 1, ".");
                        }
                    } else {
                        String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                        formattedName.append(" ").append(formattedWord.replaceAll("[0-9]", ""));
                    }
                }

                // Format date
                int dateIndex = line.lastIndexOf(" ");
                String dateString = line.substring(dateIndex + 1);
                String day = dateString.substring(0, 2);
                String month = dateString.substring(2, 4);
                String year = dateString.substring(4);
                formattedDate = day + "/" + month + "/" + year;

                // Write formatted output to file
                String output = String.format("%-25s %-10s", formattedName.toString(), formattedDate);
                outputFileWriter.println(output);
            }

            System.out.println("The input file has been formatted successfully!");

            inputFileScanner.close();
            outputFileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: file not found!");
            e.printStackTrace();
        }
    }
}