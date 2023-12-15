/*
 * Written by Nick Lauer
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrizeFileReader {

    // Reads prize information from a file and returns an array of Prize objects
    public static Prize[] readPrizes(String filename) {
        // Initialize an array of Prizes
        Prize[] prizes = new Prize[100];
        int prizeCount = 0;  // Counter to keep track of the number of prizes read

        // Using a try-with-resources block to ensure the BufferedReader is closed
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Splitting the line into parts
                String[] parts = line.split("\t");
                // Check if the line has exactly two parts: name and price
                if (parts.length == 2) {
                    try {
                        // Create a new Prize object and add to the prizes array
                        prizes[prizeCount++] = new Prize(parts[0], Double.parseDouble(parts[1]));  
                    } catch (NumberFormatException e) {
                        // Handle any number exceptions
                    }
                }
            }
        } catch (IOException e) {
            // Print any I/O errors that occur
            e.printStackTrace();
        }

        // Create a new array with the exact size required
        Prize[] exactSizePrizes = new Prize[prizeCount];
        // Copy the prizes from the original array to the exact sized array
        System.arraycopy(prizes, 0, exactSizePrizes, 0, prizeCount);

        return exactSizePrizes;
    }
}