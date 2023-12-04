import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;

public class Part2 {
    private static int sum;

    /**
     * Reads a file and splits it into rows.
     * Iterates through lines of text to find * characters.
     * Takes all the numbers adjacent to a star where amount of numbers >= 2 and multiplies them together.
     * @param filePath File path to test input
     */
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
                    if (!Character.isDigit(charArray[j]) && charArray[j] == '*') {
                        ArrayList<Integer> numbersList = new ArrayList<>();
                        // Check current line
                        checkLine(charArray, j, numbersList);
                        // If not the first line, check previous line
                        if (i > 0) {
                            previousLine = lines.get(i - 1);
                            checkLine(previousLine.toCharArray(), j, numbersList);
                        }
                        // If not the last line, check next line
                        if (i < lines.size() - 1) {
                            nextLine = lines.get(i + 1);
                            checkLine(nextLine.toCharArray(), j, numbersList);
                        }
                        if (numbersList.size() >= 2) {
                            int localSum = 1;
                            for (int nbr : numbersList) {
                                localSum *= nbr;
                            }
                            sum += localSum;
                        }
                    }

                }

            }
            System.out.println("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checking for numbers and adds them to a list, used for current, previous, and next line.
     * @param charArray Line of characters to be checked
     * @param index Starting index for check
     * @param numbersList List that holds all adjacent numbers to a index
     * @return List with all numbers adjacent to a *
     */
    public static ArrayList<Integer> checkLine(char[] charArray, int index, ArrayList<Integer> numbersList) {
        StringBuilder number = new StringBuilder();

        // Check if the character at the given index is a digit
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
                numbersList.add(Integer.parseInt(number.toString()));
                number.setLength(0);
            }
        } else {
            int localIndex = index+1;

            // Go to the right
            while (localIndex < charArray.length && Character.isDigit(charArray[localIndex])) {
                number.append(charArray[localIndex]);
                localIndex++;
            }
            if (!number.isEmpty()) {
                numbersList.add(Integer.parseInt(number.toString()));
                number.setLength(0);
            }
            localIndex = index-1;

            // Go to the left
            while (localIndex >= 0 && Character.isDigit(charArray[localIndex])) {
                number.insert(0, charArray[localIndex]);
                localIndex--;
            }
            if (!number.isEmpty()) {
                numbersList.add(Integer.parseInt(number.toString()));
            }
        }
        return numbersList;
    }


    public static void main(String[] args) {
        readFile("input.txt");
    }
}