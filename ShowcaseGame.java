/*
 * Written by Nick Lauer
 */
import java.util.*;

public class ShowcaseGame {
    private Prize[] prizes;          				// Array holding available prizes
    private Prize[] showcase;                   	// Array holding randomly selected prizes for the current game round
    private double showcaseTotal;              		// The total price of the current showcase
    private static final int NUM_PRIZES_IN_SHOWCASE = 5; // Number of prizes in each showcase

    public ShowcaseGame(Prize[] prizes) {
        this.prizes = prizes;
        selectRandomPrizes();
    }

    // Selects a random set of prizes for the showcase and calculates their total price
    private void selectRandomPrizes() {
        Random random = new Random();
        showcase = new Prize[NUM_PRIZES_IN_SHOWCASE];
        showcaseTotal = 0;

        // Make sure there are enough prizes to select a showcase
        if (prizes.length < NUM_PRIZES_IN_SHOWCASE) {
            throw new IllegalArgumentException("Not enough prizes to select a showcase");
        }

        // Set to keep track of which prizes have already been selected
        Set<Integer> selectedIndices = new HashSet<>();
        for (int i = 0; i < NUM_PRIZES_IN_SHOWCASE; i++) {
            int randomIndex;
            // Prize is not selected multiple times
            do {
                randomIndex = random.nextInt(prizes.length);
            } while (selectedIndices.contains(randomIndex));

            selectedIndices.add(randomIndex);
            showcase[i] = prizes[randomIndex];
            showcaseTotal += prizes[randomIndex].getPrice();
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {  // Main game loop
            
            // Display the prizes in the current showcase
            System.out.println("Your prizes are:");
            for (Prize prize : showcase) {
                System.out.println(prize.getName());
            }
            
            System.out.println("You must guess the total cost of the prizes without going over and within $1,300 of its actual price");
            
            while (true) {  // Loop to get the user's guess
                System.out.println("Enter your guess:");
                try {
                    double userGuess = scanner.nextDouble();

                    // Check if the user's guess is correct
                    if (userGuess <= showcaseTotal && userGuess >= showcaseTotal - 1300) {
                        System.out.println("You win!!! \nActual total was: " + showcaseTotal);
                    } else {
                        System.out.println("Your guess was incorrect. You lose \nActual total was: " + showcaseTotal);
                    }
                    break;  // Exit the loop after a valid guess
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); 
                }
            }
            
            // Ask if the user wants to play another round
            System.out.println("Do you want to play again? Enter 'yes' to play again.");
            scanner.nextLine(); 
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;  // Exit the game loop if the user doesn't want to play again
            }
            
            // Select a new random set of prizes for the next round
            selectRandomPrizes();
        	}
    }
}