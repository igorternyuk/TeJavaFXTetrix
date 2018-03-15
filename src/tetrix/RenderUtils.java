package tetrix;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Created by igor on 15.03.18.
 */
public class RenderUtils {
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
