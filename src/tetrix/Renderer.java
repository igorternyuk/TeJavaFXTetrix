package tetrix;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tetrix.model.Model;


/**
 * Created by igor on 15.03.18.
 */
public class Renderer {
    private static final String GAME_PAUSED_MESSAGE = "GAME PAUSED";
    private static final String GAME_OVER_MESSAGE = "GAME OVER!!!";
    static final int TILE_SIZE = 30;
    private static final Font smallFont = new Font("Ubuntu Mono", 18);
    private static final Font largeFont = new Font("Ubuntu Mono", 40);

    public static void renderField(GraphicsContext gc, final Model model) {
        gc.setStroke(Color.GREEN.darker());
        for (int y = 0; y < Model.FIELD_HEIGHT; ++y) {
            for (int x = 0; x < Model.FIELD_WIDTH; ++x) {
                gc.strokeOval(x * TILE_SIZE, y * TILE_SIZE, 2, 2);
                if (model.getTileAt(x, y)) {
                    Renderer.renderBlock(gc, Color.BISQUE, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE);
                }
            }
        }
        gc.strokeLine(Model.FIELD_WIDTH * TILE_SIZE, 0, Model.FIELD_WIDTH * TILE_SIZE,
                Model.FIELD_HEIGHT * TILE_SIZE);
    }

    public static void renderActiveTetramino(GraphicsContext gc, final Model model) {
        model.getActiveTetramino().getBlocks().forEach(block ->
                Renderer.renderBlock(gc, model.getActiveTetramino().getColor(), block.getX() * TILE_SIZE,
                        block.getY() * TILE_SIZE, TILE_SIZE));
    }

    public static void renderSidePanel(GraphicsContext gc, final Model model) {
        final int leftBound = Model.FIELD_WIDTH * TILE_SIZE + 20;
        //Render next piece on the side panel
        final int x = leftBound + 12;
        final int y = 60;
        Model.getPrototype(model.getNextShape()).copy().getBlocks().forEach(block ->
                Renderer.renderBlock(gc, model.getNextShape().getColor(),
                        x + block.getRelativeX() * 3 * TILE_SIZE / 5,
                        y + block.getRelativeY() * 3 * TILE_SIZE / 5,
                        3 * TILE_SIZE / 5));
        gc.setFill(Color.GREEN.darker().darker());
        gc.setFont(smallFont);
        gc.fillText("NEXT:\n", leftBound, 20);
        gc.fillText("SCORE:\n" + model.getScore(), leftBound, 170);
        gc.fillText("LINES:\n" + model.getNumberOfRemovedLines(), leftBound, 220);
        gc.fillText("LEVEL:\n" + model.getLevel(), leftBound, 260);

    }

    public static void renderGameOverMessage(GraphicsContext gc) {
        gc.setFont(largeFont);
        gc.setFill(Color.RED);
        gc.fillText(GAME_OVER_MESSAGE, 50, 250);
    }

    public static void renderPauseMessage(GraphicsContext gc) {
        gc.setFont(largeFont);
        gc.setFill(Color.GREEN);
        gc.fillText(GAME_PAUSED_MESSAGE, 50, 250);
    }

    public static void renderBlock(GraphicsContext gc, final Color color, final int x, final int y, final int tileSize) {
        gc.setFill(color);
        gc.fillRect(x, y, tileSize, tileSize);
        gc.setStroke(color.brighter().brighter());
        gc.strokeLine(x + 1, y + 1, x + tileSize - 1,
                y + 1);
        gc.strokeLine(x + 1, y + 1, x + 1,
                y + tileSize - 1);
        gc.setStroke(color.darker().darker());
        gc.strokeLine(x + tileSize - 1, y + 1,
                x + tileSize - 1, y + tileSize - 1);
        gc.strokeLine(x + 1, y + tileSize - 1,
                x + tileSize - 1, y + tileSize - 1);
    }
}
