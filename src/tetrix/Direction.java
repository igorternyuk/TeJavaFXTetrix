package tetrix;

/**
 * Created by igor on 14.03.18.
 */
public enum Direction {
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0),
    UP(0, -1);
    private int dx, dy;

    Direction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction nextClockwise() {
        int nextIndex = this.ordinal() + 1;
        if (nextIndex >= Direction.values().length) {
            nextIndex = 0;
        }
        return Direction.values()[nextIndex];
    }

    public Direction nextCounterclockwise() {
        int nextIndex = this.ordinal() - 1;
        if (nextIndex < 0) {
            nextIndex = Direction.values().length - 1;
        }
        return Direction.values()[nextIndex];
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
