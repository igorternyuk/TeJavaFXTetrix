package tetrix.tests;

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
        final Tetramino tetraminoJ = Tetramino.createTetramino(Shape.J, 4, 2);
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
        final Tetramino tetraminoO = Tetramino.createTetramino(Shape.O, 4, 2);
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
        final Tetramino tetraminoI = Tetramino.createTetramino(Shape.I, 4, 2);
        List<Block> blocks = tetraminoI.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(1, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(2, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(3, blocks.get(2).getY());
        assertEquals(4, blocks.get(3).getX());
        assertEquals(4, blocks.get(3).getY());
    }

    @Test
    public void testOfTShapedTetraminoPosition() {
        final Tetramino tetraminoT = Tetramino.createTetramino(Shape.T, 4, 2);
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
        final Tetramino tetraminoS = Tetramino.createTetramino(Shape.S, 4, 2);
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
        final Tetramino tetraminoZ = Tetramino.createTetramino(Shape.Z, 4, 2);
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
        final Tetramino tetraminoL = Tetramino.createTetramino(Shape.L, 4, 2);
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
        final Tetramino tetraminoT = Tetramino.createTetramino(Shape.T, 4, 2);
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
        final Tetramino tetraminoI = Tetramino.createTetramino(Shape.I, 4, 2);
        tetraminoI.move(Direction.DOWN);
        List<Block> blocks = tetraminoI.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(4, blocks.get(2).getY());
        assertEquals(4, blocks.get(3).getX());
        assertEquals(5, blocks.get(3).getY());
    }

    @Test
    public void testOfClockwiseRotation() {
        final Tetramino tetraminoS = Tetramino.createTetramino(Shape.S, 4, 2);
        tetraminoS.rotateClockwise();
        List<Block> blocks = tetraminoS.getBlocks();
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(3, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(3, blocks.get(3).getX());
        assertEquals(1, blocks.get(3).getY());
    }

    @Test
    public void testOfCounterclockwiseRotation() {
        final Tetramino tetraminoS = Tetramino.createTetramino(Shape.S, 4, 2);
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
