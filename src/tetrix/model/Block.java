package tetrix.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

import static tetrix.TetrixApp.TILE_SIZE;

/**
 * Created by igor on 14.03.18.
 */
public class Block {
    private final List<Coordinate> coordinates;
    private int x, y, rx, ry;

    private Tetramino parent;

    public Block(final int x, final int y, Coordinate... coordinates) {
        this.coordinates = new ArrayList<>(Arrays.asList(coordinates));
        this.x = x;
        this.y = y;
    }

    public Block(Coordinate... coordinates) {
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

    public void setParent(final Tetramino parent) {
        this.parent = parent;
        updatePosition();
    }

    public void rotateClockwise() {
        this.coordinates.forEach(coordinate -> coordinate.setDirection(coordinate.getDirection().nextClockwise()));
        updatePosition();
    }

    public void rotateCounterclockwise() {
        this.coordinates.forEach(coordinate -> coordinate.setDirection(coordinate.getDirection().nextCounterclockwise()));
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

    public void updateRelativeCoordinatesXY() {
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

    public Block copy() {
        return new Block(this.x, this.y, this.coordinates);
    }

    public void draw(GraphicsContext gc) {
        gc.fillRect(this.x * TILE_SIZE, this.y * TILE_SIZE,
                TILE_SIZE, TILE_SIZE);
        if (this.parent != null) {
            final Color color = this.parent.getColor();
            gc.setStroke(color.brighter().brighter());
            gc.strokeLine(this.x * TILE_SIZE + 1, this.y * TILE_SIZE + 1, this.x * TILE_SIZE + TILE_SIZE - 1,
                    this.y * TILE_SIZE + 1);
            gc.strokeLine(this.x * TILE_SIZE + 1, this.y * TILE_SIZE + 1, this.x * TILE_SIZE + 1,
                    this.y * TILE_SIZE + TILE_SIZE - 1);
            gc.setStroke(color.darker().darker());
            gc.strokeLine(this.x * TILE_SIZE + TILE_SIZE - 1, this.y * TILE_SIZE + 1,
                    this.x * TILE_SIZE + TILE_SIZE - 1, this.y * TILE_SIZE + TILE_SIZE - 1);
            gc.strokeLine(this.x * TILE_SIZE + 1, this.y * TILE_SIZE + TILE_SIZE - 1,
                    this.x * TILE_SIZE + TILE_SIZE - 1, this.y * TILE_SIZE + TILE_SIZE - 1);
        }
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
