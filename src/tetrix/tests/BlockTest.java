package tetrix.tests;

import org.junit.Before;
import org.junit.Test;
import tetrix.model.Block;
import tetrix.model.Coordinate;
import tetrix.model.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by igor on 14.03.18.
 */
public class BlockTest {
    private Block block;
    private Random random;

    @Before
    public void setUp() {
        List<Coordinate> coordinates = new ArrayList<>(2);
        coordinates.add(new Coordinate(Direction.RIGHT, 2));
        coordinates.add(new Coordinate(Direction.UP, 1));
        this.block = new Block(10, 10, coordinates);
        random = new Random();
    }

    @Test
    public void testMovementByOffsets() {
        for (int i = 0; i < 20; ++i) {
            final int dx = random.nextInt(10);
            final int dy = random.nextInt(10);
            final int oldBlockX = block.getX();
            final int oldBlockY = block.getY();
            block.move(dx, dy);
            assertEquals(oldBlockX + dx, block.getX());
            assertEquals(oldBlockY + dy, block.getY());
        }
    }

    @Test
    public void testMovementInTheRightDirection() {
        block.move(Direction.RIGHT);
        assertEquals(11, block.getX());
        block.move(Direction.RIGHT, 2);
        assertEquals(13, block.getX());
    }

    @Test
    public void testMovementInTheLeftDirection() {
        block.move(Direction.LEFT);
        assertEquals(9, block.getX());
        block.move(Direction.LEFT, 3);
        assertEquals(6, block.getX());
    }

    @Test
    public void testMovementInTheUpDirection() {
        block.move(Direction.UP);
        assertEquals(9, block.getY());
        block.move(Direction.UP, 4);
        assertEquals(5, block.getY());
    }

    @Test
    public void testMovementInTheDownDirection() {
        block.move(Direction.DOWN);
        assertEquals(11, block.getY());
        block.move(Direction.DOWN, 3);
        assertEquals(14, block.getY());
    }

    @Test
    public void testClockwiseRotation() {
        block.rotateClockwise();
        /*coordinates.add(new Coordinate(Direction.RIGHT, 2));
        coordinates.add(new Coordinate(Direction.UP, 1));
        this.block = new Block(10, 10, coordinates);
          *
        ***
        * */
        assertTrue(block.getCoordinates().get(0).getDirection().equals(Direction.DOWN));
        assertTrue(block.getCoordinates().get(1).getDirection().equals(Direction.RIGHT));
        assertEquals(11, block.getX());
        assertEquals(12, block.getY());

    }

    @Test
    public void testCounterclockwiseRotation() {
        block.rotateCounterclockwise();
        assertTrue(block.getCoordinates().get(0).getDirection().equals(Direction.UP));
        assertTrue(block.getCoordinates().get(1).getDirection().equals(Direction.LEFT));
        assertEquals(9, block.getX());
        assertEquals(8, block.getY());
    }

    /*@Test
    public void testCopy(){
        final Block clone = block.copy();
        assertNotSame(clone, block);
        assertEquals(clone, block);
    }*/
}
