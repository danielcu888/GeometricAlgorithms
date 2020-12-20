import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CoordinateTest {

	@Test
	void testCompare1() {
		final Coordinate c1 = new Coordinate(0.0, 1.0);
		final Coordinate c2 = new Coordinate(0.0, 2.0);
		assertEquals(-1, c1.compareTo(c2));
	}

	@Test
	void testCompare2() {
		final Coordinate c1 = new Coordinate(0.0, 1.0);
		final Coordinate c2 = new Coordinate(0.0, 0.0);
		assertEquals(1, c1.compareTo(c2));
	}

	@Test
	void testCompare3() {
		final Coordinate c1 = new Coordinate(0.0, 0.0);
		final Coordinate c2 = new Coordinate(0.0, 0.0);
		assertEquals(0, c1.compareTo(c2));
	}

	@Test
	void testCompare4() {
		final Coordinate c1 = new Coordinate(1.0, 0.0);
		final Coordinate c2 = new Coordinate(2.0, 0.0);
		assertEquals(-1, c1.compareTo(c2));
	}

	@Test
	void testCompare5() {
		final Coordinate c1 = new Coordinate(1.0, 0.0);
		final Coordinate c2 = new Coordinate(0.0, 0.0);
		assertEquals(1, c1.compareTo(c2));
	}

	@Test
	void testCompare6() {
		final Coordinate c1 = new Coordinate(0.0, 0.0);
		final Coordinate c2 = new Coordinate(0.0, 0.0);
		assertEquals(0, c1.compareTo(c2));
	}
}
