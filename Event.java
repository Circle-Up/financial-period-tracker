import java.time.LocalDate;

public class Event {

    // Event object created.

    LocalDate date;
    int trackedEvent;
    int eventType;
    String eventName;
    double amount;
    String category;
    String description;
    int eventCode;
    

    // Constructor. 
    public Event(LocalDate date, int trackedEvent, int eventType, String eventName,
        double amount, String category, String description, int eventCode
    ) {
       this.date = date;
       this.trackedEvent = trackedEvent;
       this.eventType = eventType;
       this.eventName = eventName;
       this.amount = amount;
       this.category = category;
       this.description = description;
       this.eventCode = eventCode;
    }

    @Override
    public String toString() {
        return category;
    }
}
