package tetrix.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igor on 14.03.18.
 */
public class Model {
    public static final int FIELD_WIDTH = 20;
    public static final int FIELD_HEIGHT = 10;

    private boolean[][] field = new boolean[FIELD_HEIGHT + 1][FIELD_WIDTH];

    private Tetramino activeTetramino, nextTetramino;

    public Model() {

    }
}
