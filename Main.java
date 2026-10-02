import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner for taking user inputs to navigate the menu's.
        Scanner userInput = new Scanner(System.in);
        int userSelection = 0;

        // Creating History object.
        History history = new History();

        // Check if file for memory exists.
        history.fileCheck();

        System.out.println("Welcome to your financial tracker!");

        do {
            // Calling InputManager class to handle menu navigation.
            userSelection = InputManager.getMenuSelection
            (userInput, """
                What would you like to do?
                \n1. Create Financial Event\n2. View History\n3. Exit
                    """, 3);

            // Create new Event and store it in the History array.
            switch (userSelection) {
                case 1 -> {
                    Event newEvent = EventManager.createEvent(userInput, history);
                    if (newEvent != null) {
                        history.addEvent(newEvent);
                    }
                }
                case 2 -> {
                    HistoryMenu.historyMenu(userInput, history);
                }
                case 3 -> {
                    System.out.println("Thank you!\n");
                    System.exit(0);
                }
            }
        }
        while ( userSelection != 3);

        userInput.close();
    }
}