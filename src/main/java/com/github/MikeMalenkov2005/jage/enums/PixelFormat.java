package com.github.MikeMalenkov2005.jage.enums;

import static org.lwjgl.opengl.GL46.*;

public enum PixelFormat {
    RGBA(GL_RGBA),
    RGB(GL_RGB),
    BGRA(GL_BGRA),
    BGR(GL_BGR),
    RG(GL_RG),
    RED(GL_RED),
    DEPTH_COMPONENT(GL_DEPTH_COMPONENT),
    STENCIL_INDEX(GL_STENCIL_INDEX),
    DEPTH_STENCIL(GL_DEPTH_STENCIL);

    public final int id;

    PixelFormat(int id) {
        this.id = id;
    }
}
