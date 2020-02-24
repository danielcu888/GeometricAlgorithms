enum EventType {
	START,
	END,
	INTERSECTION,
	UNKNOWN
}

public class Event {
	public EventType type = EventType.UNKNOWN;
	public LineSegment ls1 = null;
	public LineSegment ls2 = null;

	public Event(EventType type_, LineSegment ls1_, LineSegment ls2_) {
		this.type = type_;
		this.ls1 = ls1_;
		this.ls2 = ls2_;
	}

	public Event(EventType type_, LineSegment ls1_) {
		this.type = type_;
		this.ls1 = ls1_;
		this.ls2 = null;
	}
}
