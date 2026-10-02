import java.util.HashMap;
import java.util.Scanner;

public class HistoryMenu {

    public static void historyMenu(Scanner userInput, History history) {

        int userSelection = 0;
        HashMap<Integer, Event> uniqueTrackedEvents;

        userSelection = InputManager.getMenuSelection(userInput, """
                    What would you like to view?
                    1. Full History
                    2. Tracked Event History
                    3. Back
                    """, 3);

                    switch (userSelection) {
                        case 1 -> {
                            if (history.events.size() != 0) {
                                history.printHistory();
                            }
                            else{
                                System.out.println("Sorry you have no recorded history yet.\n");
                            }
                            return;
                        }
                        case 2 -> {

                            if (history.uniqueTrackedEvents().size() != 0) {
                                uniqueTrackedEvents = history.uniqueTrackedEvents();
                            
                                String IDSelectionPrompt = " ";

                                for (Integer eventID : uniqueTrackedEvents.keySet()){
                                    
                                    IDSelectionPrompt += eventID + ". " + uniqueTrackedEvents.get(eventID) + "| ID: " + eventID + " |\n";
                                }
                                userSelection = InputManager.getMenuSelection(userInput, IDSelectionPrompt , uniqueTrackedEvents.size());

                                history.printHistory(userSelection);
                            }
                            else {
                                System.out.println("Sorry you have no tracked history yet.\n");
                            }
                            return;
                        }
                        case 3 -> {
                            return;
                        }
                    }
    }
}