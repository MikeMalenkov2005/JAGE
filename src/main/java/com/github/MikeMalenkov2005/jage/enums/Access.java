package com.github.MikeMalenkov2005.jage.enums;

import static org.lwjgl.opengl.GL46.*;

public enum Access {
    READ_ONLY(GL_READ_ONLY),
    WRITE_ONLY(GL_WRITE_ONLY),
    READ_WRITE(GL_READ_WRITE);

    public final int id;

    Access(int id) {
        this.id = id;
    }
}
