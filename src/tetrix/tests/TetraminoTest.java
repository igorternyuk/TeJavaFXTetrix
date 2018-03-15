package tetrix.tests;

import org.junit.Before;
import org.junit.Test;
import tetrix.model.Block;
import tetrix.model.Direction;
import tetrix.model.Shape;
import tetrix.model.Tetramino;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by igor on 15.03.18.
 */
public class TetraminoTest {
    private Tetramino tetraminoL, tetraminoJ, tetraminoO, tetraminoT, tetraminoI, tetraminoS, tetraminoZ;

    @Before
    public void setUp() {
        tetraminoL = Tetramino.createTetramino(Shape.L, 4, 2);
        tetraminoJ = Tetramino.createTetramino(Shape.J, 4, 2);
        tetraminoO = Tetramino.createTetramino(Shape.O, 4, 2);
        tetraminoI = Tetramino.createTetramino(Shape.I, 4, 2);
        tetraminoT = Tetramino.createTetramino(Shape.T, 4, 2);
        tetraminoS = Tetramino.createTetramino(Shape.S, 4, 2);
        tetraminoZ = Tetramino.createTetramino(Shape.Z, 4, 2);
    }

    @Test
    public void testOfLShapedTetraminoPosition() {
        final Tetramino tetraminoL = Tetramino.createTetramino(Shape.L, 4, 2);
        List<Block> blocks = tetraminoL.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(1, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfJShapedTetraminoPosition() {
        List<Block> blocks = tetraminoJ.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(1, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(3, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfOShapedTetraminoPosition() {
        List<Block> blocks = tetraminoO.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(5, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfIShapedTetraminoPosition() {
        List<Block> blocks = tetraminoI.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(3, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(5, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(6, blocks.get(3).getX());
        assertEquals(2, blocks.get(3).getY());
    }

    @Test
    public void testOfTShapedTetraminoPosition() {
        List<Block> blocks = tetraminoT.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(5, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(3, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(4, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfSShapedTetraminoPosition() {
        List<Block> blocks = tetraminoS.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(5, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(3, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfZShapedTetraminoPosition() {
        List<Block> blocks = tetraminoZ.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(3, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfLeftMove() {
        tetraminoL.move(Direction.LEFT);
        List<Block> blocks = tetraminoL.getBlocks();
        assertEquals(3, blocks.get(0).getX());
        assertEquals(1, blocks.get(0).getY());
        assertEquals(3, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(3, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(4, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfRightMove() {
        tetraminoT.move(Direction.RIGHT);
        List<Block> blocks = tetraminoT.getBlocks();
        assertEquals(5, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(6, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    @Test
    public void testOfDownMove() {
        tetraminoI.move(Direction.DOWN);
        List<Block> blocks = tetraminoI.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(3, blocks.get(0).getX());
        assertEquals(3, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(5, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(6, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

    /*@Test
    public void testOfClockwiseRotation() {
        final Tetramino tetrSS = Tetramino.createTetramino(Shape.S, 4, 2);
        tetrSS.rotateClockwise();
        List<Block> blocks = tetrSS.getBlocks();
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(3, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(3, blocks.get(3).getX());
        assertEquals(1, blocks.get(3).getY());
    }*/

    @Test
    public void testOfCounterclockwiseRotation() {
        tetraminoS.rotateCounterclockwise();
        List<Block> blocks = tetraminoS.getBlocks();
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(1, blocks.get(1).getY());
        assertEquals(5, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }

}
