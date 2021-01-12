import java.util.Comparator;

/**
 * A Comparator subclass to compare Events, so that
 * ascending order corresponds to descending ordinate
 * value, first y, then x.
 */
public class IntersectionEventComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        final double y1 = o1.coord.y;
        final double y2 = o2.coord.y;

        if (y1 < y2) {
            return -1;
        }

        if (y1 > y2) {
            return 1;
        }

        final double x1 = o1.coord.x;
        final double x2 = o2.coord.x;

        if (x1 < x2) {
            return -1;
        }

        if (x1 > x2) {
            return 1;
        }

        return 0;
    }

}
