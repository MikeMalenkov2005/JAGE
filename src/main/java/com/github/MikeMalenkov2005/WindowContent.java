package com.github.MikeMalenkov2005;

public interface WindowContent {
    void draw(Window window);

    void keyPress(Window window, double x, double y, int key, int mods);

    void keyHold(Window window, double x, double y, int key, int mods);

    void keyRelease(Window window, double x, double y, int key, int mods);

    void mousePress(Window window, double x, double y, int button, int mods);

    void mouseHold(Window window, double x, double y, int button, int mods);

    void mouseRelease(Window window, double x, double y, int button, int mods);

    void mouseScroll(Window window, double x, double y, double xScroll, double yScroll);

    void mouseMove(Window window, double x, double y, double dx, double dy);
}
