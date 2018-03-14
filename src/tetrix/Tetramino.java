package tetrix;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Created by igor on 14.03.18.
 */
public class Tetramino {
    private final Color color;
    private int x, y;
    private List<Block> blocks;

    public Tetramino(final Color color, final int x, final int y, Block... blocks) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.blocks = new ArrayList<>(Arrays.asList(blocks));
        this.blocks.forEach(block -> block.setParent(this));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }

    public void move(final int dx, final int dy) {
        this.x += dx;
        this.y += dy;
        this.blocks.forEach(block -> block.move(dx, dy));
    }

    public void move(final Direction direction) {
        this.move(direction.getDx(), direction.getDy());
    }

    public void rotateClockwise() {
        System.out.println("Rotating clockwise");
        this.blocks.forEach(Block::rotateClockwise);
    }

    public void rotateCounterclockwise() {
        System.out.println("Rotating counterclockwise");
        this.blocks.forEach(Block::rotateCounterclockwise);
    }

    public Tetramino copy() {
        final Block[] newBlocks = this.blocks.stream().map(Block::copy).collect(Collectors.toList())
                .toArray(new Block[0]);
        return new Tetramino(this.color, this.x, this.y, newBlocks);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.blocks.forEach(block -> block.draw(gc));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Tetramino)) return false;
        final Tetramino otherTetramino = (Tetramino) other;
        return Objects.equals(this.color, otherTetramino.getColor())
                && Objects.equals(this.x, otherTetramino.getX())
                && Objects.equals(this.y, otherTetramino.getY())
                && blocks.equals(otherTetramino.blocks);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = color.hashCode();
        result = prime * result + getX();
        result = prime * result + getY();
        result = prime * result + blocks.hashCode();
        return result;
    }
}
