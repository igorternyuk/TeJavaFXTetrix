package tetrix.model;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by igor on 14.03.18.
 */
public class Model {
    public static final int FIELD_WIDTH = 10;
    public static final int FIELD_HEIGHT = 20;
    private static final int TETRAMINO_START_POSITION_X = FIELD_WIDTH / 2;
    private static final int TETRAMINO_START_POSITION_Y = -2;
    private static final int NEXT_TETRAMINO_START_POSITION_Y = -15;
    private static final int[] SCORE = {300, 500, 700, 1500};
    private final boolean[][] field = new boolean[FIELD_HEIGHT + 1][FIELD_WIDTH];
    private final Random random = new Random();
    private Tetramino activeTetramino, nextTetramino;
    private int score, numberOfRemovedLines, level;
    private boolean isGameOver = false;

    public Model() {
        this.activeTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, TETRAMINO_START_POSITION_Y);
        this.nextTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, NEXT_TETRAMINO_START_POSITION_Y);
        for (int y = 0; y < FIELD_HEIGHT; ++y) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                this.field[y][x] = false;
            }
        }
        //Bottom of the well
        for (int x = 0; x < FIELD_WIDTH; ++x) {
            this.field[FIELD_HEIGHT][x] = true;
        }
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

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void prepareNewGame() {
        this.score = 0;
        this.numberOfRemovedLines = 0;
        this.level = 1;
        clearField();
        this.activeTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, TETRAMINO_START_POSITION_Y);
        this.nextTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, NEXT_TETRAMINO_START_POSITION_Y);
    }

    private void clearField() {
        for (int y = 0; y < FIELD_HEIGHT; ++y) {
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                this.field[y][x] = false;
            }
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
                for (int x = 0; x < FIELD_WIDTH; ++x) {
                    this.field[y][x] = this.field[y - 1][x];
                }
            }
            for (int x = 0; x < FIELD_WIDTH; ++x) {
                this.field[top][x] = false;
            }
            --top;
        }

        return removeLinesCounter;
    }

    private void awardPlayer(final int numberOfRemovedLines) {
        this.score += SCORE[numberOfRemovedLines];
    }

    private void switchLevel() {
        if (this.score > 0 && this.score % 1000 == 0) {
            ++this.level;
        }
    }

    private Tetramino createRandomTetramino(final int x, final int y) {
        return Tetramino.createTetramino(Shape.values()[random.nextInt(Tetramino.NUMBER_OF_SHAPES)], x, y);
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
        this.activeTetramino = this.nextTetramino;
        this.activeTetramino.move(0, TETRAMINO_START_POSITION_Y - NEXT_TETRAMINO_START_POSITION_Y);
        this.nextTetramino = createRandomTetramino(TETRAMINO_START_POSITION_X, NEXT_TETRAMINO_START_POSITION_Y);
        if (isTetraminoTouchingGround(this.activeTetramino)) {
            this.isGameOver = true;
        }
    }

    public void tick() {
        if (!isGameOver) {
            tryToMakeAMove(tetramino -> tetramino.move(Direction.DOWN), tetramino -> tetramino.move(Direction.UP));
        }
    }

    public void moveActiveTetraminoLeft() {
        tryToMakeAMove(tetramino -> tetramino.move(Direction.LEFT), tetramino -> tetramino.move(Direction.RIGHT));
    }

    public void moveActiveTetraminoRight() {
        tryToMakeAMove(tetramino -> tetramino.move(Direction.RIGHT), tetramino -> tetramino.move(Direction.LEFT));
    }

    public void rotateActiveTetraminoClockwise() {
        tryToMakeAMove(Tetramino::rotateClockwise, Tetramino::rotateCounterclockwise);
    }

    public void rotateActiveTetraminoCounterclockwise() {
        tryToMakeAMove(Tetramino::rotateCounterclockwise, Tetramino::rotateClockwise);
    }

    public void dropActiveTetraminoDown() {
        while (!isTetraminoTouchingGround(this.activeTetramino)) {
            tick();
        }
    }
}
