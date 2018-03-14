package tetrix.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by igor on 14.03.18.
 */
public class Tetramino {
    private static final Map<Shape, Tetramino> PROTOTYPES = createPrototypes();
    private Shape shape;
    private int x, y;
    private List<Block> blocks;

    public Tetramino(final Shape shape, final int x, final int y, Block... blocks) {
        this.x = x;
        this.y = y;
        this.blocks = new ArrayList<>(Arrays.asList(blocks));
        this.blocks.forEach(block -> block.setParent(this));
    }

    private static Map<Shape, Tetramino> createPrototypes() {
        Map<Shape, Tetramino> prototypes = new HashMap<>();
        //L-shape
        Tetramino tetraminoL = new Tetramino(Shape.L, 0, 0,
                new Block(new Coordinate(Direction.UP, 1)),
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.RIGHT, 1))
        );
        //J-shape
        Tetramino tetraminoJ = new Tetramino(Shape.J, 0, 0,
                new Block(new Coordinate(Direction.UP, 1)),
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.LEFT, 1))
        );
        //O-shape
        //I-shape
        //T-shape
        //S-shape
        //Z-shape
        return prototypes;
    }

    public static Tetramino createTetramino(final Shape shape, final int x, final int y) {
        return null;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Shape getShape() {
        return this.shape;
    }

    public Color getColor() {
        return this.shape.getColor();
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

    public void detach(final int x, final int y) {
        this.blocks.removeIf(piece -> piece.getX() == x && piece.getY() == y);
    }

    public Tetramino copy() {
        final Block[] newBlocks = this.blocks.stream().map(Block::copy).collect(Collectors.toList())
                .toArray(new Block[0]);
        return new Tetramino(this.shape, this.x, this.y, newBlocks);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(this.shape.getColor());
        this.blocks.forEach(block -> block.draw(gc));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Tetramino)) return false;
        final Tetramino otherTetramino = (Tetramino) other;
        return Objects.equals(this.shape.getColor(), otherTetramino.getColor())
                && Objects.equals(this.x, otherTetramino.getX())
                && Objects.equals(this.y, otherTetramino.getY())
                && blocks.equals(otherTetramino.blocks);
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
