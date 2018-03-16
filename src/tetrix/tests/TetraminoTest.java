package tetrix.tests;

import org.junit.Before;
import org.junit.Test;
import tetrix.model.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Created by igor on 15.03.18.
 */
public class TetraminoTest {

    /*@Test
    public void testOfLShapedTetraminoPosition() {
        final Tetramino tetraminoL = Model.createTetramino(Shape.L, 4, 2);
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
        final Tetramino tetraminoJ = Model.createTetramino(Shape.J, 4, 2);
        tetraminoJ.adjustPosition();
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
        final Tetramino tetraminoO = Model.createTetramino(Shape.O, 4, 2);
        tetraminoO.adjustPosition();
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
        final Tetramino tetraminoI = Model.createTetramino(Shape.I, 4, 2);
        tetraminoI.adjustPosition();
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
        final Tetramino tetraminoT = Model.createTetramino(Shape.T, 4, 2);
        tetraminoT.adjustPosition();
        List<Block> blocks = tetraminoT.getBlocks();
        assertEquals(4, blocks.size());
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(4, blocks.get(2).getX());
        assertEquals(1, blocks.get(2).getY());
        assertEquals(3, blocks.get(3).getX());
        assertEquals(2, blocks.get(3).getY());
    }

    @Test
    public void testOfSShapedTetraminoPosition() {
        final Tetramino tetraminoS = Model.createTetramino(Shape.S, 4, 2);
        tetraminoS.adjustPosition();
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
        final Tetramino tetraminoZ = Model.createTetramino(Shape.Z, 4, 2);
        tetraminoZ.adjustPosition();
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
        final Tetramino tetraminoL = Model.createTetramino(Shape.L, 4, 2);
        tetraminoL.adjustPosition();
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
        final Tetramino tetraminoT = Model.createTetramino(Shape.T, 4, 2);
        tetraminoT.adjustPosition();
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
        final Tetramino tetraminoI = Model.createTetramino(Shape.I, 4, 2);
        tetraminoI.adjustPosition();
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

   @Test
    public void testOfClockwiseRotation() {
        final Tetramino T = Model.createTetramino(Shape.T, 5, 2);
        T.adjustPosition();
        assertEquals(0, T.getAngle());
        T.rotateClockwise();
        List<Block> blocks = T.getBlocks();
        assertEquals(5, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(5, blocks.get(1).getX());
        assertEquals(3, blocks.get(1).getY());
        assertEquals(5, blocks.get(2).getX());
        assertEquals(1, blocks.get(2).getY());
        assertEquals(4, blocks.get(3).getX());
        assertEquals(2, blocks.get(3).getY());
    }

    @Test
    public void testOfCounterclockwiseRotation() {
        final Tetramino S = Model.createTetramino(Shape.T, 5, 2);
        S.adjustPosition();
        S.rotateCounterclockwise();
        List<Block> blocks = S.getBlocks();
        assertEquals(4, blocks.get(0).getX());
        assertEquals(2, blocks.get(0).getY());
        assertEquals(4, blocks.get(1).getX());
        assertEquals(1, blocks.get(1).getY());
        assertEquals(5, blocks.get(2).getX());
        assertEquals(2, blocks.get(2).getY());
        assertEquals(5, blocks.get(3).getX());
        assertEquals(3, blocks.get(3).getY());
    }*/

}
