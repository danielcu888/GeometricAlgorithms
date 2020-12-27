import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComparableIntegerTest {

    @Test
    void compareTest1() {
        final ComparableInteger ci1 = new ComparableInteger(1);
        final ComparableInteger ci2 = new ComparableInteger(4);

        assertEquals(-1, ci1.compareTo(ci2));
    }

    @Test
    void compareTest2() {
        final ComparableInteger ci1 = new ComparableInteger(1);
        final ComparableInteger ci2 = new ComparableInteger(4);

        assertEquals(1, ci2.compareTo(ci1));
    }

    @Test
    void compareTest3() {
        final ComparableInteger ci1 = new ComparableInteger(1);
        final ComparableInteger ci2 = new ComparableInteger(1);

        assertEquals(0, ci1.compareTo(ci2));
        assertEquals(0, ci2.compareTo(ci1));
    }

    @Test
    void valueTest() {
        final ComparableInteger ci = new ComparableInteger(1);
        assertEquals(1, ci.i);
    }

}
