package org.example;

public class Settings {
    private ThemeTypes theme;
    private Fonts font;

    public Fonts getFont() {
        return font;
    }

    public Fonts setFont(Fonts font) {
        this.font = font;
        return font;
    }

    public ThemeTypes getTheme() {
        return theme;
    }

    public ThemeTypes setTheme(ThemeTypes theme) {
        this.theme = theme;
        return theme;
    }
}
