import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.System.out;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class History {

    // File pathing
    File historyFile = new File("history.csv");

    // Array to hold and eventually store Event objects.
    ArrayList<Event> events = new ArrayList<>();

    // File creation/validation.
    // Full disclosure, I had AI help me write fileCheck(), saveEventToFile()
    // and loadHistoryFile(), I did not know the syntax or the requisite
    // libraries I would need to make it work although the design of 
    // the functions is my own as is most all of the code.
    public void fileCheck() {
        try {
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }
            loadHistoryFile();
        }
        catch (IOException error) {
            out.println("Sorry you have encountered a problem with your history.\n");
            return;
        }
    }

    // File writing/saving to file.
    public void saveEventToFile(Event event) {

        String saveData = (event.date + "," + event.trackedEvent + "," + event.eventType + 
        "," + event.eventName + "," + String.format("%.2f", event.amount) + "," + event.category + "," +
        event.description + "," + event.eventCode + "\n");

        try {
            FileWriter writer = new FileWriter(historyFile, true);
            writer.write(saveData);
            writer.close();
        }
        catch (IOException error) {
            out.println("Sorry you have encountered a problem with your history.\n");
            return;
        }
    }

    // Load memory from local history.csv file
    public void loadHistoryFile() {
        
        try {
            Scanner fileReader = new Scanner(historyFile);

            if (fileReader.hasNextLine()) {
                do {
                String fileLine = fileReader.nextLine();
                String[] fileData = fileLine.split(",");

                Event event = new Event(
                LocalDate.parse(fileData[0]),
                Integer.parseInt(fileData[1]), 
                Integer.parseInt(fileData[2]), 
                fileData[3],
                Double.parseDouble(fileData[4]), 
                fileData[5], 
                fileData[6],
                Integer.parseInt(fileData[7]));

                events.add(event);
                }
                while (fileReader.hasNextLine());
            }
            fileReader.close();
        }
        catch (FileNotFoundException error) {
            out.print("Sorry there seems to be a problem with your file.");
        }
    }

    // Method to add new events to the events array.
    public void addEvent(Event newEvent) {

        events.add(newEvent);
        saveEventToFile(newEvent);
    }

    // Print out stored Events
    public void printHistory() {

        for (Event event : events) {
            out.println("Date created: " + event.date);
            out.println("Event Type: " + event.eventName);
            out.printf("Amount: $%.2f%n", event.amount);
            out.println("Category: " + event.category);
            out.println("Description: " + event.description);
            out.println( " ");
        }
    }

    // Print out specific sub sections of history according to ID of event.
    public void printHistory(int eventID) {

        for (Event event : events) {
            if (event.eventCode == eventID) {
                out.println("Date created: " + event.date);
                out.println("Event Type: " + event.eventName);
                out.printf("Amount: $%.2f%n", event.amount);
                out.println("Category: " + event.category);
                out.println("Description: " + event.description);
                out.println( " ");
            }
        }
        financialMath(createFinancialPeriod(eventPeriodTracker(eventID)));
    }

    // Create accessible HashSet of Category and eventCode.
    public HashMap<Integer, Event> uniqueTrackedEvents() {

        HashMap<Integer, Event> eventID = new HashMap<>();

        // Enhanced loop to retrieve each object inside of the ArrayList events
        // and temporarily retrieve them using an Event type variable called event
        // so I can intereact with each object invdividually.
        for (Event event : events) {
            if (event.eventCode != 0) {
                eventID.put(event.eventCode, event);
            }
        }
        return eventID;
    }

    // Tracking event ID's so I can assign a unique ID to each tracked event.
    public int eventIDTracker() {
        HashMap<Integer, Event> eventID = uniqueTrackedEvents();
        int nextEventID = 1;

        for (Integer eventCode : eventID.keySet()){
            if (nextEventID <= eventCode) {
                nextEventID = eventCode + 1;
            }
        }

        return nextEventID;
    }

    // Create and return an array of periods taken out of the events array.
    public ArrayList<Integer> eventPeriodTracker(int eventID) {
        ArrayList<Integer> trackedIndexes = new ArrayList<>();

        for (int index = 0; index < events.size(); index++) {
            
            if (events.get(index).eventCode == eventID) {
                trackedIndexes.add(index);
            }
        }

        return trackedIndexes;
    }

    // Receive an array of integers and seperate it into periods of two items (start and end).
    public HashMap<Integer, ArrayList<Integer>> createFinancialPeriod(ArrayList<Integer> trackedIndexes) {

        int index = 0;
        int positionStart = 0;
        int positionEnd = 0;
        HashMap<Integer, ArrayList<Integer>> periods = new HashMap<>();
        
        // Ensure that there is enough data to work with.
        if (trackedIndexes.size() > 1) {

            // Create boundaries with periods as arrays with two items inside
            // And then storing those array's into periods map.
            for (index = 0; index <= trackedIndexes.size() - 2; index++) {
                positionStart = index;
                positionEnd = index + 1;
                ArrayList<Integer> period = new ArrayList<>();

                period.add(trackedIndexes.get(positionStart));
                period.add(trackedIndexes.get(positionEnd));
                periods.put(index, period);
            }
        }

        return periods;
    }

    // Math necessary to create summaries over given periods of selected events.
    public void financialMath(HashMap<Integer, ArrayList<Integer>> financialPeriods) {

        int periodCounter = 1;

        // Split the given Map of Lists into periods of two items.
        for (Integer period : financialPeriods.keySet()) {

            ArrayList<Integer> singlePeriod = financialPeriods.get(period);
            ArrayList<Double> summary = new ArrayList<>();

            int start = singlePeriod.get(0);
            int end = singlePeriod.get(1);
            LocalDate dateStart = (events.get(start).date);
            LocalDate dateEnd = (events.get(end).date);;
            Double summaryTotal = 0.00;
            Double incomeTotal = 0.00;
            Double expensesTotal = 0.00;


            // Populate summary with all amount items from events.
            for (int index = start; index < end; index ++) {

                summary.add(events.get(index).amount);

                if (events.get(index).amount > 0) {
                    incomeTotal = (incomeTotal + events.get(index).amount);
                }
                else {
                    expensesTotal = (expensesTotal + events.get(index).amount);
                }
            }
            // Figure out the total amount of change within the financial period.
            for (int i = 0; i < summary.size(); i++){

                summaryTotal = summaryTotal + summary.get(i);
            }

            out.println("Financial Period: " + periodCounter);
            out.println(dateStart + " - " + dateEnd + "\n");
            out.printf("\nTotal Income: $%.2f%n", incomeTotal);
            out.printf("\nTotal Expenses: $%.2f%n", expensesTotal);
            out.printf("\nYour net change: $%.2f%n", summaryTotal);

            periodCounter += 1;

        }
    }
}