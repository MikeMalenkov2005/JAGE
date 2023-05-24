package com.github.MikeMalenkov2005.jage.enums;

import static org.lwjgl.opengl.GL46.*;

public enum WrapMode {
    CLAMP_TO_BORDER(GL_CLAMP_TO_BORDER),
    CLAMP_TO_EDGE(GL_CLAMP_TO_EDGE),
    REPEAT(GL_REPEAT),
    MIRRORED_REPEAT(GL_MIRRORED_REPEAT);

    public final int id;

    WrapMode(int id) {
        this.id = id;
    }
}
