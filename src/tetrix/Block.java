package tetrix;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static tetrix.TetrixApp.TILE_SIZE;

/**
 * Created by igor on 14.03.18.
 */
public class Block {
    private static final int COORDINATES_MAX_NUMBER = 2;
    private final List<Coordinate> coordinates = new ArrayList<>(COORDINATES_MAX_NUMBER);
    private int x, y;
    private Tetramino parent;

    public Block(final int x, final int y, final List<Coordinate> coordinates) {
        this.coordinates.addAll(coordinates);
        this.x = x;
        this.y = y;
    }

    public Block(final Tetramino parent, final List<Coordinate> coordinates) {
        this.coordinates.addAll(coordinates);
        this.setParent(parent);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<Coordinate> getCoordinates() {
        return Collections.unmodifiableList(this.coordinates);
    }

    public Tetramino getParent() {
        return this.parent;
    }

    public void setParent(final Tetramino parent) {
        this.parent = parent;
        this.x = parent.getX();
        this.y = parent.getY();
        for (final Coordinate coordinate : this.coordinates) {
            this.x += coordinate.getOffset() * coordinate.getDirection().getDx();
            this.y += coordinate.getOffset() * coordinate.getDirection().getDy();
        }
    }

    public void rotateClockwise() {
        for (final Coordinate coordinate : this.coordinates) {
            coordinate.setDirection(coordinate.getDirection().nextClockwise());
            this.x += coordinate.getOffset() * coordinate.getDirection().getDx();
            this.y += coordinate.getOffset() * coordinate.getDirection().getDy();
        }
    }

    public void rotateCounterclockwise() {
        for (final Coordinate coordinate : this.coordinates) {
            coordinate.setDirection(coordinate.getDirection().nextCounterclockwise());
            this.x += coordinate.getOffset() * coordinate.getDirection().getDx();
            this.y += coordinate.getOffset() * coordinate.getDirection().getDy();
        }
    }

    public void move(final int dx, final int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void move(final Direction direction) {
        this.x += direction.getDx();
        this.y += direction.getDy();
    }

    public void move(final Direction direction, final int offset) {
        this.x += direction.getDx() * offset;
        this.y += direction.getDy() * offset;
    }

    public Block copy() {
        return new Block(this.parent, this.coordinates);
    }

    public void draw(GraphicsContext gc) {
        gc.fillRect(this.x * TILE_SIZE, this.y * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Block)) return false;
        final Block otherBlock = (Block) other;
        return this.coordinates.equals(otherBlock.getCoordinates())
                && Objects.equals(this.x, otherBlock.getX())
                && Objects.equals(this.y, otherBlock.getY())
                && Objects.equals(this.parent, otherBlock.getParent());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = x;
        result = prime * result + y;
        result = prime * result + parent.hashCode();
        return result;
    }
}
