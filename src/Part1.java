import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
    private static int sum;

    public static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            String previousLine;
            String nextLine;

            //Put each line in arraylist
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            // Loop through lines
            for (int i = 0; i < lines.size(); i++) {
                // Character array for a string represent a line
                char[] charArray = lines.get(i).toCharArray();
                // Iterate char array
                for (int j = 0; j < charArray.length; j++) {
                    // Check if char is special
                    if (!Character.isDigit(charArray[j]) && charArray[j] != '.') {
                        // Check current line
                        checkLine(charArray, j);
                        // If not the first line, check previous line
                        if (i > 0) {
                            previousLine = lines.get(i - 1);
                            checkLine(previousLine.toCharArray(), j);
                        }
                        // If not the last line, check next line
                        if (i < lines.size() - 1) {
                            nextLine = lines.get(i + 1);
                            checkLine(nextLine.toCharArray(), j);
                        }

                    }

                }

            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkLine(char[] charArray, int index) {
        StringBuilder number = new StringBuilder();
        if (Character.isDigit(charArray[index])) {
            int localIndex = index;

            // Go to the right
            while (localIndex < charArray.length && Character.isDigit(charArray[localIndex])) {
                number.append(charArray[localIndex]);
                localIndex++;
            }

            localIndex = index-1;

            // Go to the left
            while (localIndex >= 0 && Character.isDigit(charArray[localIndex])) {
                number.insert(0, charArray[localIndex]);
                localIndex--;
            }

            if (!number.isEmpty()) {
                System.out.println(number);
                sum += Integer.parseInt(number.toString());
                number.setLength(0);
            }
        } else {
            // +1 because we know its not a number
            int localIndex = index+1;

            // Go to the right
            while (localIndex < charArray.length && Character.isDigit(charArray[localIndex])) {
                number.append(charArray[localIndex]);
                localIndex++;
            }
            if (!number.isEmpty()) {
                System.out.println(number);
                sum += Integer.parseInt(number.toString());
                number.setLength(0);
            }
            // -1 because we know its not a number
            localIndex = index-1;

            // Go to the left
            while (localIndex >= 0 && Character.isDigit(charArray[localIndex])) {
                number.insert(0, charArray[localIndex]);
                localIndex--;
            }
            if (!number.isEmpty()) {
                sum += Integer.parseInt(number.toString());
                number.setLength(0);
            }
        }
    }


    public static void main(String[] args) {
        readFile("input.txt");
    }
}