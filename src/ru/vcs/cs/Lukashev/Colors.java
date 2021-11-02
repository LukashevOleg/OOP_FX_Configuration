package ru.vcs.cs.Lukashev;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Colors {
    private final Color red = Color.valueOf("#f00");
    private final Color green = Color.valueOf("#00ff09");
    private final Color blue = Color.valueOf("#0084ff");
    private final Color magenta = Color.valueOf("#800080");
    private final Color orange = Color.valueOf("#f7963b");
    private final Color cyan = Color.valueOf("#3bf7f7");
    private final Color pink = Color.valueOf("#ffccfd");

    private final Color DEFAULT_COLOR = new Color(0, 0, 0, 0);

    public Color getDEFAULT_COLOR() {
        return DEFAULT_COLOR;
    }

    private final Color[] arrColors =
            {
                    red,
                    green,
                    blue,
                    magenta,
                    orange,
                    cyan,
                    pink//7
            };


    public Color[] getArrColors() {
        return arrColors;
    }
}
