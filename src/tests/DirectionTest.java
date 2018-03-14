package tests;

import org.junit.Before;
import org.junit.Test;
import tetrix.Direction;

import static org.junit.Assert.*;

/**
 * Created by igor on 14.03.18.
 */
public class DirectionTest {
    private Direction dirRight, dirUp, dirLeft, dirDown;

    @Before
    public void setUp() {
        this.dirRight = Direction.RIGHT;
        this.dirUp = Direction.UP;
        this.dirLeft = Direction.LEFT;
        this.dirDown = Direction.DOWN;
    }

    @Test
    public void directionOffsetsTest() {
        assertEquals(1, dirRight.getDx());
        assertEquals(0, dirRight.getDy());
        assertEquals(0, dirUp.getDx());
        assertEquals(-1, dirUp.getDy());
        assertEquals(-1, dirLeft.getDx());
        assertEquals(0, dirLeft.getDy());
        assertEquals(0, dirDown.getDx());
        assertEquals(1, dirDown.getDy());
    }

    @Test
    public void clockwiseRotationTest() {
        assertEquals(dirDown, dirRight.nextClockwise());
        assertEquals(dirRight, dirUp.nextClockwise());
        assertEquals(dirUp, dirLeft.nextClockwise());
        assertEquals(dirLeft, dirDown.nextClockwise());

    }

    @Test
    public void counterclockwiseRotationTest() {
        assertEquals(dirUp, dirRight.nextCounterclockwise());
        assertEquals(dirLeft, dirUp.nextCounterclockwise());
        assertEquals(dirDown, dirLeft.nextCounterclockwise());
        assertEquals(dirRight, dirDown.nextCounterclockwise());
    }
}
