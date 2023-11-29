package org.infantaelena.ies.psp.UD2.practicas.hinchadoresGlobos;

public class Globo {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    Globo(Color color) {
        setColor(color);
    }
}
