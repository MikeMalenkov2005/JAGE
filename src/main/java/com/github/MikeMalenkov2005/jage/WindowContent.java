package com.github.MikeMalenkov2005.jage;

public interface WindowContent {
    void draw(Window window);

    void press(Window window, double x, double y, String input, int mods);

    void hold(Window window, double x, double y, String input, int mods);

    void release(Window window, double x, double y, String input, int mods);

    void print(Window window, double x, double y, String str);

    void scroll(Window window, double x, double y, double xScroll, double yScroll);

    void move(Window window, double x, double y, double dx, double dy);
}
