/**
 * A class wrapping an int that can be compared.
 */
public class ComparableInteger implements Comparable<ComparableInteger> {

    /**
     * The wrapped int.
     */
    public int i;

    /**
     * The comparison predicate.
     * @param o The object to be compared.
     * @retval -1 If this.i < o.i.
     * @retval 0 If this.i == o.i.
     * @retval +1 If this.i > o.i.
     */
    @Override
    public int compareTo(ComparableInteger o) {
        if (this.i < o.i) {
            return -1;
        } else if (this.i > o.i) {
            return +1;
        } else {
            return 0;
        }
    }

    /**
     * One parameter constructor.
     * @param i_ The int to be wrapped by this object.
     */
    public ComparableInteger(Integer i_) {
        this.i = i_;
    }
}