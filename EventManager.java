import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;

public class EventManager {

    // This is for creating financial events.
    public static Event createEvent(Scanner userInput, History history){

        int trackedEvent = 0;

        trackedEvent = isTracked(userInput);

        // If the event is tracked then determine if it needs to be attached
        // to an existing ID or if a new ID needs to be created.
        EventDetails eventDetails = determineEventDetails(userInput, trackedEvent, history);

        if (eventDetails == null) {
            return null;
        }

        // Take the amount of money exchanged in the event. 
        // If the eventType is expense this number will be made a negative.
        double amount = 0;

        if (eventDetails.eventType == 2) {

            amount = -InputManager.getDouble(userInput, "Amount: ");
        }
        else {

            amount = InputManager.getDouble(userInput, "Amount: ");
        }
        
        // Description of the event for user memory.
        String description = InputManager.getString(userInput, "Description: ");
        description = description.replace(",", ";");

        // Date of the event created.
        LocalDate date = LocalDate.now();

        // Event creation.
        Event newEvent = new Event(date, trackedEvent, eventDetails.eventType, 
            eventDetails.eventName, amount, eventDetails.category, description, 
            eventDetails.eventCode);
        return newEvent;
    }

    // Determine whether this event should be tracked or not.
    public static int isTracked(Scanner userInput) {
        
        int trackedEvent = InputManager.getMenuSelection
        (userInput, "Is this a tracked event?\n1. Yes\n2. No", 2);

        return trackedEvent;
    }

    // Determine the eventType and the eventID
    public static EventDetails determineEventDetails(Scanner userInput, 
        int trackedEvent, History history) 
        {  
            int existingEvent = 0;

            HashMap<Integer, Event> uniqueTrackedEvents = history.uniqueTrackedEvents();
            EventDetails details = new EventDetails();

            if (trackedEvent == 2) {
                details.eventType = InputManager.getMenuSelection
                (userInput, "What kind of event is this?\n1. Income\n2. Expense", 2);

                details.eventCode = 0;
                determineEventType(userInput, details);
            }
            else {

                existingEvent = InputManager.getMenuSelection
                (userInput, "Is this a new or existing tracked event?\n1. New\n2. Existing", 2);

                // If the tracked event exists then create a menu for them to choose their event from.
                // If they choose an existing event then auto fill eventType, eventName and category
                // as they should be consistent across the two events. 
                if (existingEvent == 2) {

                    if  (uniqueTrackedEvents.size() != 0) {

                        String IDSelectionPrompt = "";

                        for (Integer eventID : uniqueTrackedEvents.keySet()){
                            
                            IDSelectionPrompt += eventID + ". " + uniqueTrackedEvents.get(eventID) + "| ID: " + eventID + " |\n";
                        }
                        details.eventCode = InputManager.getMenuSelection(userInput, IDSelectionPrompt , uniqueTrackedEvents.size());
                        
                        Event selectedEvent = uniqueTrackedEvents.get(details.eventCode);
                        details.eventType = selectedEvent.eventType;
                        details.eventName = selectedEvent.eventName;
                        details.category = selectedEvent.category;
                    }
                    else {
                        System.out.println("Sorry you have no tracked items yet.\n");
                        return null;
                    }
                }

                // If the event does not have an existing track on it then
                // create a unique code for the event.
                else {
                    details.eventCode = history.eventIDTracker();

                    // Determine whether this event is income or expense.
                    details.eventType = InputManager.getMenuSelection
                    (userInput, "What kind of event is this?\n1. Income\n2. Expense", 2);

                    determineEventType(userInput, details);
                }
            }

        return details;
    }

    public static EventDetails determineEventType (Scanner userInput, EventDetails details) {
        if (details.eventType == 2) {

            details.eventName = "Expense";
            details.category = determineCategory(userInput,false);
        }
        else {

            details.eventName = "Income";
            details.category = determineCategory(userInput, true);
        }
        return details;
    }

    public static String determineCategory (Scanner userInput, boolean eventType) {

        int categorySelection = 0;
        String category = "";

        // HashMap for expense type events.
        HashMap<Integer, String> expenseCategories = new HashMap<>();
        expenseCategories.put(1, "Food");
        expenseCategories.put(2, "Housing");
        expenseCategories.put(3, "Transportation");
        expenseCategories.put(4, "Entertainment");
        expenseCategories.put(5, "Other");

        // HashMap for income type events.
        HashMap<Integer, String> incomeCategories = new HashMap<>();
        incomeCategories.put(1, "Paycheck");
        incomeCategories.put(2, "Gift");
        incomeCategories.put(3, "Refund");
        incomeCategories.put(4, "Other");

        if (!eventType) {

            categorySelection = InputManager.getMenuSelection
            (userInput, """
            What kind of Expense is this:
            1. Food
            2. Housing
            3. Transportation
            4. Entertainment
            5. Other\n""", 5);

            category = expenseCategories.get(categorySelection);
        }
        else {

            categorySelection = InputManager.getMenuSelection
            (userInput, """
            What kind of Income is this:\n
            1. Paycheck
            2. Gift
            3. Refund
            4. Other\n""", 4);

            category = incomeCategories.get(categorySelection);
        }

        return category;
    }

    // A class to hold my data types I am manipulating so I can carry over
    // return values.
    static class EventDetails {
        int eventCode;
        int eventType;
        String eventName;
        String category;
    }
}
