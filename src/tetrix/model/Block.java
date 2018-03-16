package tetrix.model;

import java.util.*;

/**
 * Created by igor on 14.03.18.
 */
public class Block {
    private final List<Coordinate> coordinates;
    private int cx, cy, x, y, rx, ry;

    private Tetramino parent;

    public Block(final int x, final int y, Coordinate... coordinates) {
        this.coordinates = new ArrayList<>(Arrays.asList(coordinates));
        this.x = x;
        this.y = y;
    }

    Block(Coordinate... coordinates) {
        this.coordinates = new ArrayList<>(Arrays.asList(coordinates));
        this.x = 0;
        this.y = 0;
    }

    public Block(final int x, final int y, final List<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
        this.x = x;
        this.y = y;
    }

    public Block(final List<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
        this.x = 0;
        this.y = 0;
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

    void setParent(final Tetramino parent) {
        this.parent = parent;
        updatePosition();
    }

    private void updatePosition() {
        if (this.parent != null) {
            this.x = this.parent.getX();
            this.y = this.parent.getY();
        }
        updateRelativeCoordinatesXY();
        this.x += rx;
        this.y += ry;
    }

    private void updateRelativeCoordinatesXY() {
        this.rx = 0;
        this.ry = 0;
        for (final Coordinate coordinate : this.coordinates) {
            this.rx += coordinate.getOffset() * coordinate.getDirection().getDx();
            this.ry += coordinate.getOffset() * coordinate.getDirection().getDy();
        }
    }

    public int getRelativeX() {
        return this.rx;
    }

    public int getRelativeY() {
        return this.ry;
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

    public void rotateClockwise() {
        this.coordinates.forEach(coordinate -> coordinate.setDirection(coordinate.getDirection().nextClockwise()));
        updatePosition();
    }

    public void rotateCounterclockwise() {
        this.coordinates.forEach(coordinate -> coordinate.setDirection(coordinate.getDirection().nextCounterclockwise()));
        updatePosition();
    }

    Block copy() {
        return new Block(this.x, this.y, this.coordinates);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Block)) return false;
        final Block otherBlock = (Block) other;
        return coordinates.equals(otherBlock.getCoordinates())
                && Objects.equals(this.x, otherBlock.getX())
                && Objects.equals(this.y, otherBlock.getY())
                && Objects.equals(this.rx, otherBlock.getRelativeX())
                && Objects.equals(this.ry, otherBlock.getRelativeY());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = x;
        result = prime * result + y;
        result = prime * result + rx;
        result = prime * result + ry;
//        result = prime * result + parent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("rx = %d ry = %d", rx, ry);
    }
}
