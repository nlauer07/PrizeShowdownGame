/*
 * Written by Nick Lauer
 */
public class Main {

    // The main method to initiate the Showcase Game
    public static void main(String[] args) {
        // Name of the file containing prize information
        String filename = "prizes.txt"; 
        
        // Read prizes from the specified file
        Prize[] prizes = PrizeFileReader.readPrizes(filename);

        // Initialize the ShowcaseGame with the loaded prizes
        ShowcaseGame game = new ShowcaseGame(prizes);

        // Start the game
        game.playGame();
    }
}