package tetrix.model;


import javafx.scene.paint.Color;

/**
 * Created by igor on 14.03.18.
 */
public enum Shape {
    L {
        @Override
        public Color getColor() {
            return Color.AQUA;
        }
    }, J {
        @Override
        public Color getColor() {
            return Color.RED;
        }
    }, I {
        @Override
        public Color getColor() {
            return Color.GREEN.darker();
        }
    }, T {
        @Override
        public Color getColor() {
            return Color.ORANGE;
        }
    }, O {
        @Override
        public Color getColor() {
            return Color.CYAN.darker();
        }
    }, Z {
        @Override
        public Color getColor() {
            return Color.YELLOW;
        }
    }, S {
        @Override
        public Color getColor() {
            return getColor().MAGENTA;
        }
    };

    public abstract Color getColor();
}
