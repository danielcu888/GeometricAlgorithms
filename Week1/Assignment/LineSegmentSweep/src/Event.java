
public class Event {
	public EventType type;
	public Coordinate coord;
	public LineSegment ls1;
	public LineSegment ls2;

	public Event(EventType type_, Coordinate coord_, LineSegment ls1_, LineSegment ls2_) {
		this.type = type_;
		this.coord = coord_;
		this.ls1 = ls1_;
		this.ls2 = ls2_;
	}

	public Event(EventType type_, Coordinate coord_, LineSegment ls_) {
		this.type = type_;
		this.coord = coord_;
		this.ls1 = ls_;
		this.ls2 = null;
	}
}