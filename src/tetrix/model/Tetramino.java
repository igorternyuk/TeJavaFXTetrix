package tetrix.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by igor on 14.03.18.
 */
public class Tetramino {
    static final int NUMBER_OF_SHAPES = 7;
    private static final int NUMBER_OF_ANGLE_POSITIONS = 4;

    private Shape shape;
    private int x, y;
    private final List<Block> blocks;
    private int angle = 0;

    Tetramino(final Shape shape, final int x, final int y, Block... blocks) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.blocks = new ArrayList<>(Arrays.asList(blocks));
        this.blocks.forEach(block -> block.setParent(this));
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    int getAngle() {
        return this.angle;
    }

    Shape getShape() {
        return this.shape;
    }

    public List<Block> getBlocks() {
        return Collections.unmodifiableList(this.blocks);
    }

    public Color getColor() {
        return this.shape.getColor();
    }

    void move(final int dx, final int dy) {
        this.x += dx;
        this.y += dy;
        this.blocks.forEach(block -> block.move(dx, dy));
    }

    void move(final Direction direction) {
        this.move(direction.getDx(), direction.getDy());
    }

    void rotateClockwise() {
        if (this.shape.equals(Shape.O)) return;
        this.blocks.forEach(Block::rotateClockwise);
        ++this.angle;
        this.angle %= NUMBER_OF_ANGLE_POSITIONS;
    }

    void rotateCounterclockwise() {
        if (this.shape.equals(Shape.O)) return;
        this.blocks.forEach(Block::rotateCounterclockwise);
        --this.angle;
        if (this.angle < 0) this.angle = NUMBER_OF_ANGLE_POSITIONS - 1;
    }

    void adjustPosition() {
        while (this.angle != 0) rotateClockwise();
    }

    public Tetramino copy() {
        final Block[] newBlocks = this.blocks.stream().map(Block::copy).collect(Collectors.toList())
                .toArray(new Block[0]);
        Tetramino clone = new Tetramino(this.shape, this.x, this.y, newBlocks);
        clone.adjustPosition();
        return new Tetramino(this.shape, this.x, this.y, newBlocks);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Tetramino)) return false;
        final Tetramino otherTetramino = (Tetramino) other;
        return Objects.equals(this.shape.getColor(), otherTetramino.getColor())
                && Objects.equals(this.x, otherTetramino.getX())
                && Objects.equals(this.y, otherTetramino.getY())
                && this.blocks.equals(otherTetramino.getBlocks());
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = this.shape.hashCode();
        result = prime * result + getX();
        result = prime * result + getY();
        result = prime * result + this.blocks.hashCode();
        return result;
    }
}
