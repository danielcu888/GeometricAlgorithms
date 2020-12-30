import java.util.Comparator;

public class IntersectionEventComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        final double d1 = o1.coord.y;
        final double d2 = o2.coord.y;

        if (d1 < d2) {
            return -1;
        }

        if (d1 > d2) {
            return 1;
        }

        return 0;
    }

}
