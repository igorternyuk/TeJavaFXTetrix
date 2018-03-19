package tetrix.model;

import sun.security.provider.SHA;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by igor on 14.03.18.
 */
public class Model {
    public static final int FIELD_WIDTH = 10;
    public static final int FIELD_HEIGHT = 20;
    private static final int TETRAMINO_START_POSITION_X = FIELD_WIDTH / 2;
    private static final int TETRAMINO_START_POSITION_Y = 0;
    private static final int NEXT_LEVEL_SCORE = 500;
    private static final Map<Shape, Tetramino> PROTOTYPES = createPrototypes();
    private static final int[] SCORE = {0, 300, 500, 700, 1500};
    private final boolean[][] field = new boolean[FIELD_HEIGHT + 1][FIELD_WIDTH];
    private final Random random = new Random();
    private Tetramino activeTetramino;
    private Shape nextShape;
    private int score, numberOfRemovedLines, level = 1;
    private boolean isGameOver = false;

    public Model() {
        prepareNewGame();
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
        prototypes.put(Shape.L, tetraminoL);

        //J-shape
        Tetramino tetraminoJ = new Tetramino(Shape.J, 0, 0,
                new Block(new Coordinate(Direction.UP, 1)),
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.LEFT, 1))
        );
        prototypes.put(Shape.J, tetraminoJ);

        //O-shape
        Tetramino tetraminoO = new Tetramino(Shape.O, 0, 0,
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.RIGHT, 1)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.RIGHT, 1))
        );
        prototypes.put(Shape.O, tetraminoO);

        //I-shape
        Tetramino tetraminoI = new Tetramino(Shape.I, 0, 0,
                new Block(new Coordinate(Direction.LEFT, 1)),
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.RIGHT, 1)),
                new Block(new Coordinate(Direction.RIGHT, 2))
        );
        prototypes.put(Shape.I, tetraminoI);

        //T-shape
        Tetramino tetraminoT = new Tetramino(Shape.T, 0, 0,
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.RIGHT, 1)),
                new Block(new Coordinate(Direction.LEFT, 1)),
                new Block(new Coordinate(Direction.DOWN, 1))
        );
        prototypes.put(Shape.T, tetraminoT);

        //S-shape
        Tetramino tetraminoS = new Tetramino(Shape.S, 0, 0,
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.RIGHT, 1)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.LEFT, 1))
        );
        prototypes.put(Shape.S, tetraminoS);

        //Z-shape
        Tetramino tetraminoZ = new Tetramino(Shape.Z, 0, 0,
                new Block(new Coordinate(Direction.UP, 0)),
                new Block(new Coordinate(Direction.LEFT, 1)),
                new Block(new Coordinate(Direction.DOWN, 1)),
                new Block(new Coordinate(Direction.DOWN, 1), new Coordinate(Direction.RIGHT, 1))
        );
        prototypes.put(Shape.Z, tetraminoZ);
        return prototypes;
    }

    private static Tetramino createTetramino(final Shape shape, final int x, final int y) {
        if (PROTOTYPES.get(shape) != null) {
            final Tetramino newTetramino = PROTOTYPES.get(shape).copy();
            newTetramino.move(x, y);
            return newTetramino;
        }
        return PROTOTYPES.get(Shape.L).copy();
    }

    public static Tetramino getPrototype(final Shape shape) {
        if (PROTOTYPES.get(shape) != null) {
            Tetramino tetramino = PROTOTYPES.get(shape).copy();
            tetramino.adjustPosition();
            return tetramino;
        } else {
            throw new IllegalArgumentException("Invalid shape");
        }
    }

    private boolean isValidCoordinates(final int x, final int y) {
        return x >= 0 && x < FIELD_WIDTH && y >= 0 && y < FIELD_HEIGHT + 1;
    }

    public boolean getTileAt(final int x, final int y) {
        return isValidCoordinates(x, y) && this.field[y][x];
    }

    public Tetramino getActiveTetramino() {
        return this.activeTetramino;
    }

    public int getScore() {
        return this.score;
    }

    public int getNumberOfRemovedLines() {
        return this.numberOfRemovedLines;
    }

    public int getLevel() {
        return this.level;
    }

    public Shape getNextShape() {
        return this.nextShape;
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void prepareNewGame() {
        this.score = 0;
        this.numberOfRemovedLines = 0;
        this.level = 1;
        clearField();
        this.activeTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, TETRAMINO_START_POSITION_Y);
        int randomIndex = random.nextInt(Shape.values().length);
        while (randomIndex == this.activeTetramino.getShape().ordinal()) {
            randomIndex = random.nextInt(Shape.values().length);
        }
        this.nextShape = Shape.values()[randomIndex];
        this.isGameOver = false;
    }

    private void clearField() {
        for (int y = 0; y < FIELD_HEIGHT; ++y) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                this.field[y][x] = false;
            }
        }
        for (int x = 0; x < FIELD_WIDTH; ++x) {
            this.field[FIELD_HEIGHT][x] = true;
        }
    }

    private int removeFilledLines() {
        int removeLinesCounter = 0;
        int top = 0;
        outer:
        for (int line = 0; line < FIELD_HEIGHT; ++line) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                if (!this.field[line][x]) continue outer;
            }
            ++removeLinesCounter;
            for (int y = line; y > top; --y) {
                this.field[y] = Arrays.copyOf(this.field[y - 1], this.field[y - 1].length);
            }
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                this.field[top][x] = false;
            }
            ++top;
        }
        return removeLinesCounter;
    }

    private void awardPlayer(final int numberOfRemovedLines) {
        this.score += SCORE[numberOfRemovedLines];
    }

    private void switchLevel() {
        //System.out.println("Trying to switch level = " + this.score);
        if (this.score > this.level * NEXT_LEVEL_SCORE) {
            //System.out.println("Next level score = " + this.score);
            ++this.level;
        }
    }

    private Tetramino createRandomTetramino(final int x, final int y) {
        return createTetramino(Shape.values()[random.nextInt(Tetramino.NUMBER_OF_SHAPES)], x, y);
    }

    private boolean isBlockOutOfFieldBounds(final Block block) {
        return block.getX() < 0 || block.getX() > FIELD_WIDTH - 1 || block.getY() > FIELD_HEIGHT - 1;
    }

    private boolean isTetraminoOutOfFieldBounds(final Tetramino tetramino) {
        return tetramino.getBlocks().stream().anyMatch(this::isBlockOutOfFieldBounds);
    }

    private boolean isTetraminoCollidingWalls(final Tetramino tetramino) {
        return tetramino.getBlocks().stream().anyMatch(block -> this.field[block.getY()][block.getX()]);
    }

    private boolean isTetraminoTouchingGround(final Tetramino tetramino) {
        return tetramino.getBlocks().stream().anyMatch(block -> this.field[block.getY() + 1][block.getX()]);
    }

    private void uniteTetraminoWithField(final Tetramino tetramino) {
        tetramino.getBlocks().forEach(block -> this.field[block.getY()][block.getX()] = true);
        tetramino.adjustPosition();
    }

    private void tryToMakeAMove(Consumer<Tetramino> onSuccess, Consumer<Tetramino> onFail) {
        onSuccess.accept(this.activeTetramino);
        if (isTetraminoOutOfFieldBounds(this.activeTetramino) || isTetraminoCollidingWalls(this.activeTetramino)) {
            onFail.accept(this.activeTetramino);
            return;
        }
        if (isTetraminoTouchingGround(this.activeTetramino)) {
            onTouchGround();
        }
    }

    private void onTouchGround() {
        uniteTetraminoWithField(this.activeTetramino);
        final int numberOfRemoveLines = removeFilledLines();
        this.numberOfRemovedLines += numberOfRemoveLines;
        awardPlayer(numberOfRemoveLines);
        switchLevel();
        spawnNewRandomTetramino();
        if (isTetraminoTouchingGround(this.activeTetramino)) {
            this.isGameOver = true;
        }
    }

    private void spawnNewRandomTetramino() {
        this.activeTetramino = createTetramino(this.nextShape, TETRAMINO_START_POSITION_X,
                TETRAMINO_START_POSITION_Y);
        this.activeTetramino.adjustPosition();
        this.nextShape = Shape.values()[random.nextInt(Shape.values().length)];
        do {
            this.nextShape = Shape.values()[random.nextInt(Shape.values().length)];
        } while (this.nextShape == this.activeTetramino.getShape());
    }

    public void tick() {
        if (!isGameOver) {
            tryToMakeAMove(tetramino -> tetramino.move(Direction.DOWN), tetramino -> tetramino.move(Direction.UP));
        }
    }

    public void moveActiveTetraminoLeft() {
        if (!isGameOver)
            tryToMakeAMove(tetramino -> tetramino.move(Direction.LEFT), tetramino -> tetramino.move(Direction.RIGHT));
    }

    public void moveActiveTetraminoRight() {
        if (!isGameOver)
            tryToMakeAMove(tetramino -> tetramino.move(Direction.RIGHT), tetramino -> tetramino.move(Direction.LEFT));
    }

    private boolean isPositionOK(final Tetramino tetramino) {
        return tetramino.getBlocks().stream().allMatch(block ->
                block.getY() >= (this.activeTetramino.getShape().equals(Shape.I) ? 2 : 1));
    }

    public void rotateActiveTetraminoClockwise() {
        if (!isGameOver && isPositionOK(this.activeTetramino)) {
            tryToMakeAMove(Tetramino::rotateClockwise, Tetramino::rotateCounterclockwise);
        }
    }

    public void rotateActiveTetraminoCounterclockwise() {
        if (!isGameOver && isPositionOK(this.activeTetramino)) {
            tryToMakeAMove(Tetramino::rotateCounterclockwise, Tetramino::rotateClockwise);
        }
    }

    public void dropActiveTetraminoDown() {
        if (!isPositionOK(this.activeTetramino)) return;
        while (!isTetraminoTouchingGround(this.activeTetramino)) {
            this.activeTetramino.move(Direction.DOWN);
        }
        onTouchGround();
    }

    public void printField() {
        for (boolean[] aField : this.field) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                System.out.print(aField[x] ? "X" : "_");
            }
            System.out.println("\n");
        }
    }
}
