/**
 * A class to represent an event during a line sweep.
 */
public class Event {

    // Public data members

    public EventType type;
    public Coordinate coord;
    public LineSegment ls1;
    public LineSegment ls2;

    /**
     * Four-parameter constuctor.
     * @param type_ The type of the event.
     * @param coord_ The 2D location of the event.
     * @param ls1_ The first LineSegment associated with the event.
     * @param ls2_ The second LineSegment associated with the event
     *   (currently only acknowledged for intersection events).
     */
    public Event(EventType type_, Coordinate coord_, LineSegment ls1_, LineSegment ls2_) {
        this.type = type_;
        this.coord = coord_;
        this.ls1 = ls1_;
        this.ls2 = ls2_;
    }

    /**
     * Three-parameter constuctor.
     * @param type_ The type of the event.
     * @param coord_ The 2D location of the event.
     * @param ls1_ The LineSegment associated with the event.
     */
    public Event(EventType type_, Coordinate coord_, LineSegment ls_) {
        this.type = type_;
        this.coord = coord_;
        this.ls1 = ls_;
        this.ls2 = null;
    }
    
    public static int convertEventTypeToInteger(Event event) {
    	if (event == null) {
    		throw new IllegalArgumentException("Null event.");
    	}

    	switch (event.type)
    	{
    	case POINT_INTERSECTION:
    		return 0;
    	case START:
    		return 1;
    	case END:
    		return 2;
    	case LINE_INTERSECTION:
    		return 3;
    	default:
    		throw new IllegalArgumentException("Unrecognised EventType: " + event);
    	}
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Event{");
        sb.append(this.type);
        sb.append(",");
        sb.append(this.coord);
        sb.append(",");
        sb.append(ls1);
        sb.append(",");
        sb.append(ls2);
        sb.append("}");
        return sb.toString();
    }
}