import java.util.Scanner;

public class InputManager {

    // I wanted a inRange method like python cause I am lazy so I made one.
    public static boolean inRange(int number, int min, int max) {

        boolean isInRange = true;

        if (number < min || number > max) {
            isInRange = false;
        }

        return isInRange;
    }

    // A reusable menu selector that takes Integer inputs from the user to navigate.
    // This method also validates the input according to the menu options.
    public static int getMenuSelection(Scanner userInput, String prompt,
        int numberOfOptions
    ) {

        int userSelection = 0;

        // My attempt to make a reusable menu selector for integer inputs
        do {
            System.out.print(prompt);
            System.out.print("\nSelection: ");

            try {
                userSelection = Integer.parseInt(userInput.nextLine());
                System.out.print("\n");

                if (!inRange(userSelection, 1, numberOfOptions)) {
                    System.out.println("\nPlease select an available option." );
                }
            }
            catch (NumberFormatException numberException) {
                System.out.println("\nPlease select an option using a number.");
            }
        }
        while (!inRange(userSelection, 1, numberOfOptions));

        return userSelection;
    }

    // Menu to take Double input from the user and validate the input to simplify logic.
    public static double getDouble(Scanner userInput, String prompt) 
        {
            double amount = 0;
            boolean validInput = false;

            // This is to validate doubles entered to ensure they meet the programs needs and simplify what logic I have to do later.
            do {
                System.out.println(prompt);
                System.out.print("\nInput: >> ");

                try {
                    amount = Double.parseDouble(userInput.nextLine());
                    System.out.print("\n");

                    if (amount < 0) {
                        System.out.println("\nPlease enter a positive number.");
                        validInput = false;
                    }
                    else {
                        validInput = true;
                    }
                }
                catch (NumberFormatException numberException) {
                    System.out.println("\nPlease enter a number.");
                }
            }
            while (!validInput);

            return amount;
    }

    // Simple menu for taking String inputs from the user.
    public static String getString(Scanner userInput, String prompt) {
        
        String userOption = "";

        System.out.println(prompt);
        System.out.print("\nInput: >> ");

        userOption = userInput.nextLine();
        System.out.print("\n");

        return userOption;
    }
}
